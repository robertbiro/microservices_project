package com.ps.orderservice.orderservice.service;

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

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    @Autowired
    private final OrderRepository orderRepository;

    //private final WebClient webClient;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems =  orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        /*Boolean result =  webClient.get()
                .uri("http://localhost:8082/api/inventory")
                //to be able to retrieve the response
                //the type of response is the the type of the "Get" method above
                        .retrieve()
                        .bodyToMono(Boolean.class)
                        //webclient make an asynchronous request as default but we make a synchronous request ->
                        .block();
        //if the result is true -> the product is in stock -> save order
        if (result) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }

        //call inventory service and place order if product is in stock */
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
