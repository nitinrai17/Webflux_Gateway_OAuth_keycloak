## User Information Rest Api
In this project, we have expose the user related rest api using the spring webflux, which is non-blocking.</br>
Webflux is also used as Server-send Event (SSE) in which allows a client (browser) to listen (real-time) to a stream of events on a long-lived standard HTTP connection rather than polling a server or establishing a websocket.</br>
SSE are uni-directional, which means it's a one-way connection from server pushed.(e.g Monitoring systems, IoT data, currency rates, clickstreams etc).</br>
WebSockets(TCP) are bi-directional, which means both the client and server can push updates.(e.g chat application).

### User Info  Rest api 
 
|  Http Method  | URL           | Descrition            |
| ------------- |:-------------:| --------------------: |
| get           | /api/user     | get all the user info |
| get           | /api/user/1   | get first user info   |
| post          | /api/user     | create user info      |
| put           | /api/user/1   | update the user info  |
| delete        | /api/user/1   | delete first user info|
| `get`         | `/api/usage`    | `dummy Cpu and memory usage`|

### Postman screen shot
#### keycloak JWT token
*Request Body contains*:- <br/>
 * grant_type:password
 * scope:openid
 * client_id:test_client
 * client_secret:33a80087-2cff-4ed7-b8b4-6a7299e96b42
 * username:rai
 * password:password

<br/>
<img src="../images/get_Token.gif">
<br/>
<br/>

*get all the user info*
<br/>
<img src="../images/get_all_user_info.gif">
<br/>

<br/>

*get user info*
<br/>
<img src="../images/get_one_user_info.gif">
<br/>

<br/>

*create user info*
<br/>
<img src="../images/create_user_info.gif">
<br/>

<br/>

*update the user info*
<br/>
<img src="../images/update_user_info.gif">
<br/>

<br/>

*delete user info*
<br/>
<img src="../images/delete_user_info.gif">
<br/>

<br/>

*UnAuthorized Token*
<br/>
<img src="../images/Unauthorized_token.gif">
<br/>

<br/>

## OAuth2KeycloakLib jar location 
https://nitinraidev.jfrog.io/ui/native/nitin-gradle-dev/com/nitin/oauth2/lib/OAuth2KeycloakLib/0.0.1-SNAPSHOT/
<br/>
https://github.com/nitinrai17/OAuth2KeycloakLib

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
 docker build . -t nitinrai17/userinfo
`

### To see the network on which keycloak server running
`
docker network  ls
`

###  run command 
`
docker run --name userinfo --network webflux_gateway_oauth_default -e KEYCLOAK_URL='http://keycloak:8080/' -p 8083:8083 nitinrai17/userinfo:latest
`