# oauth2-spring-boot-mongo
Sample app to demonstrate OAuth2 implementation using Spring boot with Mongo backed data store.

In package
-----------
1. Spring Boot 1.5.7
2. Spring OAuth2 implementation.
3. Seprate auth and resource server.
4. Mongo as Token store.
5. Mongo backed user store to authenticate against DB instead of in memory.
6. Implementation of grants
  - authorization_code
  - password
  - client_credentails
  - refresh_token
7. JWT token store

Still in progress
-----------------
1. Approval flow

Description
-----------

Source has two below mentioned Spring boot seprate runnable Maven projects
  - auth-server     : authentication server which authenticates and authorize user or service to perform certain action.
  - resource-server : server on which the resource resides (in this sample, 'foo').

How to run
-----------

1. Make sure you have a running Mongo instance. You can change references in application.yml of auth-server.
2. Start auth-server using com.rites.sample.oauth2.authserver.AuthServer
3. Start resource server using com.rites.sample.oauth2.resourceserver.ResourceServer
4. Try accessing for generating token with client_credentials as grant type

  POST http://localhost:8081/auth-service/oauth/token?grant_type=client_credentials
  Headers - Authorization :  Basic d2ViLWNsaWVudDp3ZWItY2xpZW50LXNlY3JldA==

5. Try accessing for generating token with password as grant type
  GET http://localhost:8081/auth-service/oauth/token?grant_type=password&username=user&password=user
  Headers - Authorization :  Basic d2ViLWNsaWVudDp3ZWItY2xpZW50LXNlY3JldA==

6. Once the token is obtained you can access the resource using 
  GET http://localhost:8082/resource-service/foo
  Headers - Authorization : Bearer <token>

7. If you would like to use JWT as a token store then run using -Dspring.profiles.active=jwttoken


Simmilarly You can try executing other OAuth2 grant types.
<br/> Note : Pass 'init' as program arg to setup initial data.

  
