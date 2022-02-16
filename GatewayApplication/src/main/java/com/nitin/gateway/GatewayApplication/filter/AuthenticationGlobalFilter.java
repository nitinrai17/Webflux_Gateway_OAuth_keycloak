package com.nitin.gateway.GatewayApplication.filter;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.nitin.oauth2.OAuth2Keycloak.security.AccessToken;
import com.nitin.oauth2.OAuth2Keycloak.validator.JwtTokenValidator;
import com.nitin.oauth2.OAuth2Keycloak.validator.KeycloakJwkProvider;

import io.netty.util.internal.StringUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RefreshScope
@Component
public class AuthenticationGlobalFilter extends AbstractGatewayFilterFactory<AuthenticationGlobalFilter.Config> {
	
	@Value("${keycloak.jwk}")
	private String jwkProviderUrl;
	
	Logger log = LoggerFactory.getLogger(AuthenticationGlobalFilter.class);
	
	public AuthenticationGlobalFilter() {
		super(Config.class);
	}
	
	@Override
	public GatewayFilter apply(Config config) {
		return filter();
	}
	
	
	public GatewayFilter filter() {
		log.info("Gateway ...");
		return (exchange,chain) ->{
			if(null!= jwkProviderUrl ) { 
				try {
					log.debug("AuthenticationGlobalFilter.filter() jwkProviderUrl={}",jwkProviderUrl);
					JwtTokenValidator  tokenValidator = new JwtTokenValidator(new KeycloakJwkProvider(jwkProviderUrl));
					ServerHttpRequest request = exchange.getRequest();
					ServerHttpResponse response = exchange.getResponse();
					String token = extractAuthorizationHeaderAsString(request,response);
					if(null!= token && !StringUtil.isNullOrEmpty(token)) {
						AccessToken accessToken =tokenValidator.validateAuthorizationHeader(token);
						log.debug("Username = {}", accessToken.getUsername());
						//here write code if you need to forward any header to down service , e.g. request.mutate(). .. build()
						return chain.filter(exchange);
					}else {
						return onError(exchange,"There is not Authorization header in a request",HttpStatus.UNAUTHORIZED);
					}
				} catch (Exception e) {
					log.debug(e.getMessage());
					return onError(exchange,e.getMessage(),HttpStatus.UNAUTHORIZED); 
				}	
			}else {
				return onError(exchange,"There is not keycloak jwk url ",HttpStatus.UNAUTHORIZED);
			}
		};
	}
	
	public static class Config{
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	private String extractAuthorizationHeaderAsString(ServerHttpRequest request,ServerHttpResponse response ) {
			if (request.getHeaders().containsKey("Authorization")) {
				return request.getHeaders().getOrEmpty("Authorization").get(0);
			}
			return "";
	}
	
	private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        byte[] bytes = err.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Flux.just(buffer));
    }
}
