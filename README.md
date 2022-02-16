# Webflux User Information Rest Api + GateWay + OAuth + Keycloak  
In this project, we have gateway to route the request to specific microservice with provide the JWT token from the Keycloak server. We are using keycloak server for providing the jwt token , which is validate in gateway and rest api also. </br>

## Overview Diagram 

<img src="Images/Overview_diagram2.png">

<br/>

### Port details for all containers  
 
|  Containers Name  | Port          | Descrition            |
| -------------     |:-------------:| --------------------: |
| postgres          | 5432          | Postgres DB           |
| keycloak          | 8080          | Keycloak Server       |
| `gatewayapplication`| `8008`          | `Gateway Api`           |
| `userinfo`          | `8083`          | `User info (Microservice)`|

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
<img src="Images/get_Token.gif">
<br/>
<br/>

*get all the user info*
<br/>
<img src="Images/get_all_user_info.gif">
<br/>

<br/>

*get user info*
<br/>
<img src="Images/get_one_user_info.gif">
<br/>

<br/>

*create user info*
<br/>
<img src="Images/create_user_info.gif">
<br/>

<br/>

*update the user info*
<br/>
<img src="Images/update_user_info.gif">
<br/>

<br/>

*delete user info*
<br/>
<img src="Images/delete_user_info.gif">
<br/>

<br/>

*UnAuthorized Token*
<br/>
<img src="Images/Unauthorized_token.gif">
<br/>

<br/>

## OAuth2KeycloakLib jar location 
https://nitinraidev.jfrog.io/ui/native/nitin-gradle-dev/com/nitin/oauth2/lib/OAuth2KeycloakLib/0.0.1-SNAPSHOT/
<br/>
https://github.com/nitinrai17/OAuth2KeycloakLib

<br/>


## Docker Command 
you need to setup jfrog cloud artifactory and provide the credential in the gradle.properties file.(https://jfrog.com/start-free/) <br/>
<br/>

###  Build and Run command 
`
 docker-compose up  --build 
`
<br/>

## Keycloak UI
`
	http://localhost:8080
	
### Keycloak login screen
username: admin <br/>
password: admin <br/>
<br/>
<img src="images/keycloak_login.gif">	
<br/><br/>

### Keycloak create realm 
<br/>
<img src="images/create_realm.gif">	
<br/><br/>

### Keycloak create client
<br/>
<img src="images/create_client.gif">	
<br/><br/>

### Keycloak create role and user
<br/>
<img src="images/create_role_user.gif">	
<br/><br/>

