Order service, inventory service, notification service are going to interact with each other

order <--> inventory: Sync Communication
order <--> notification: Async Communication (Kafka)
 
-------------------------------------------------------------------------------
@Document:  this annotation is the Mongo equivalent of @Entity in JPA.
            https://www.baeldung.com/spring-data-annotations