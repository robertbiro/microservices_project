@Transaction:
it’s common to perform multiple database operations within a single transaction.
to indicate that the annotated code should be executed within a transaction. When Spring encounters 
the @Transactional annotation, it automatically creates a transaction around 
the annotated code and manages the transaction lifecycle.
=> ensure that multiple database operations are executed atomically,
=> if you’re performing multiple database operations as part of a single business transaction, 
    you should apply @Transactional to the service method that coordinates those operations.
https://medium.com/javarevisited/transactional-annotation-in-spring-framework-d571e91bf6bb
-------------------------------------------------------------------------------------------------

mvn clean verify

-------------------------------------------------------------------------------------------------
WebClient():
If you have Spring WebFlux on your classpath, you can also choose to use WebClient to call remote REST services.
Compared to RestTemplate, this client has a more functional feel and is fully reactive. 
You can create your own client instance with the builder, WebClient.create().
https://docs.spring.io/spring-boot/docs/2.0.3.RELEASE/reference/html/boot-features-webclient.html

----------------------------------------------------------------------------------------------------
EnableEurekaClient import doesn't exist: https://stackoverflow.com/questions/68285299/enableeurekaclient-import-doesnt-exist