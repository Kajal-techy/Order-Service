Order Service
----------------
This is a spring boot microservice which has several **ORDER, SHIPMENT** related CRUD REST APIs.
  * This microservice registers and is discoverable via **Discover-Service (Eureka Server)**.
  * It uses strucutered Controller-Service-Dao/Repo architecture, designed for interfaces.
  * It uses **MySQL** as persistence layer and **Hibernate** as ORM.
  * Has Shipment and Order table related via many-to-one relationship achieved via Hibernate.
  * Has notion of NormalOrder and SpecialOrder which is achieved via **Table Per Hierarchy** Hibernate. SpecialOrder differs from NormalOrder as the former has 7 days shipment whereas SpecialOrder has 2 days shipment.
  * **AOP** is used for logging across application
  * Uses **Feign Client** , which uses ribbon + eureka to call another microservice
