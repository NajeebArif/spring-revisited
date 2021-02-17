# Spring Revisited*

A very simple application to learn various aspects of a Microservice architecture using Spring Boot

## Stuffs which can be found here:
1) Spring boot based microservices services
2) Spring cloud config (centralized config server)
3) Spring cloud eureka (service discovery and Client side load balancing)
4) Client side resiliency patterns like Circuit Breakers, Fallbacks and Bulkheads using Hystrix
5) Application Gateway using Spring cloud Zuul

## Please feel free to review and share your thoughts(just clone or fork it and play with them as they are simple maven projects.)

Stuffs inside:

1) Spring Boot Based microservice:- How easy it is to implement anything (Web-Security-JPA-DI-anything)

2) Configurations: Centralized(codeless) config Server which is contacted by each Microservice to get the config data. Note for the sake of Simplicity Property/yaml files have been used but these are easily replaceable from local files to files on github/bitbucket (Good for tracking purpose what config changes went in.). Did i forget to mention that these configs can be dynamic for respective environments?

3) Service Discovery and Client side load balancing(We as a developer love this approach i.e. having the maximum control, but recently Kubernetes+Istio has grown popular for it)

4)MOST IMP(in any app): Resiliency patterns: like Circuit Breakers, Fallbacks and Bulkheads. These patterns could be used in anything(Java EE, ADF, SPA(using OJET or Angular),... ), not just Microservices. Resiliency patterns, like TDD/BDD, should be incorporated as a practice.

5) Security: OAuth 2, JWT using a centralized Auth server.

6) App Gateway.

7) A/B routing, which compliments CI/CD. Here we have two version of Department microservice: DepartmentsService (old) and DepartmentServiceV02 (new probably in Beta phase). Now as the A/B routing has been implemented into the service small % of DepSer will automatically use newer version and the others will still be using the older version. Using this, newer changes can be rapidly pushed to Prod with almost Zero Downtime. What % of requests will be routed to the newer api is configurable and can be increased gradually, eventually everyone lands up using V02 of the api.

8) Communication between different services is accomplished via Kafka message broker (for events and commands) and REST api.

9) Redis is used to provide distributed Caching mechanism.

#### *The project is not dockerized yet but it will be soon enough.


