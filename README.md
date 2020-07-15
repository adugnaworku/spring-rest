# Demo Rest APIs

This application is a tech stack demonestrator with a very limited CRUD operation for managing a hypothetical laptop inventory
## Prerequisites
* Java 1.8 
* Gradle 5.5.1+

# Quick Start
1.Clone the repository to you local env
 ```shell
 git clone https://github.com/adugnaworku/spring-rest.git
 ```

2.Run the application with the following command. The application will start at http://localhost:8080

 ```shell
 gradle bootRun
 ```
3.You can access the Apis from swagger or Postman. The following is Swager-ui url.
    
```shell
http://localhost:8080/swagger-ui.html#/
```

4.Swagger ui can be accessed with out login but executing the API requires a JWT authentication. To login use the `login` end
point from `auth-controller`. Use an already existing credential `username : user2 , password: 123123`. You can
also create your own login `signup` end point of `auth-controller`.

5.Use the JWT token from the login endpoint, add the token to the authorization header of 
any request in `laptop-controller` end points by clicking a lock key icon.
    
6.Actuator end-point is found under `/actuator`. Authentication not required to access actuator.

# Remaing

1. Method `@PreAuthorize` resource authorization
2. `flyway` data migration
3. Redis cache
4. I18n
  






