package com.ps.orderservice.orderservice.service;

import com.ps.orderservice.orderservice.dto.InventoryResponse;
import com.ps.orderservice.orderservice.dto.OrderLineItemsDto;
import com.ps.orderservice.orderservice.dto.OrderRequest;
import com.ps.orderservice.orderservice.model.Order;
import com.ps.orderservice.orderservice.model.OrderLineItems;
import com.ps.orderservice.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final WebClient.Builder webClientBuilder;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems =  orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

       //retrieve all skuCode from the same order -> one order contain more skuCode
        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(orderLineItem -> orderLineItem.getSkuCode())
                .toList();

        InventoryResponse[] inventoryResponseArray =  webClientBuilder.build().get()
                //because I have all skuCode (above) I can give it as a param
                //webclient build an uri with query parameters, skuCode
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                //to be able to retrieve the response
                //the type of response is the type of the "Get" method above
                        .retrieve()
                        .bodyToMono(InventoryResponse[].class)
                        //read the data the response (type of the response)
                        //webclient make an asynchronous request as default but we should a synchronous request ->
                        .block();
        boolean allProductInStock =  Arrays.stream(inventoryResponseArray)
                .allMatch(inventoryResponse -> inventoryResponse.isInStock());
        //if the result is true -> the product is in stock -> save order
        if (allProductInStock) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }

        //call inventory service and place order if product is in stock
        order.setOrderLineItemsList(orderLineItems);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
