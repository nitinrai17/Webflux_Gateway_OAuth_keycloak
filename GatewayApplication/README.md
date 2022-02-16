## Gateway Api
In this project, we have expose the Gateway rest api using the spring gateway, which is non-blocking.</br>



## OAuth2KeycloakValidateLib jar location 
https://nitinraidev.jfrog.io/ui/native/nitin-gradle-dev/com/nitin/oauth2/lib/OAuth2KeycloakValidateLib/0.0.1-SNAPSHOT/
<br/>


<br/>

## Gradle Command 
<br/>
you need to setup jfrog cloud artifactory and provide the credential in the gradle.properties file.(https://jfrog.com/start-free/) <br/>

### build command 
`
./gradlew clean build
`

### Run command 
`
./gradlew bootRun
`
<br/>

## Docker Command 
you need to setup jfrog cloud artifactory and provide the credential in the gradle.properties file.(https://jfrog.com/start-free/) <br/>
<br/>

###  build command 
`
 docker build . -t nitinrai17/gatewayapplication
`

### To see the network on which keycloak server running
`
docker network  ls
`

###  run command 
`
docker run --network webflux_gateway_oauth_default -e KEYCLOAK_URL='http://keycloak:8080/' -e SPRING_CLOUD_GATEWAY_ROUTES[0]_URI='http://userinfo:8083' -e  SPRING_CLOUD_GATEWAY_ROUTES[0]_ID='userinfo-route' -e SPRING_CLOUD_GATEWAY_ROUTES[0]_PREDICATES='Path=/api/**' -p 8008:8008 nitinrai17/gatewayapplication:latest
`