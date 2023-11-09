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
