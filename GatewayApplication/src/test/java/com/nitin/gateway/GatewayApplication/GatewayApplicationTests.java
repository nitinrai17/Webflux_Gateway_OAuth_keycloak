package com.nitin.gateway.GatewayApplication;

import java.time.Period;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GatewayApplicationTests {

	
	private WebTestClient client;
	
	@LocalServerPort
	private int port;
	
	@BeforeEach
	public void setUp() {
		client = WebTestClient.bindToServer().baseUrl("http://localhost:"+port).build();
	}
	
	
	@Test
	public void testUnAuthorizedWork() {
		client.get().uri("/api")
			.exchange()
			.expectStatus().isUnauthorized();
	}
	
	
	//@Test
	public void testPathRouteWork() {
		
	}
	
	//@Test
	public void testCircuitBreakerRouteWorks() {
		
	}

	//@Test
	public void testCircuitBreakerFallbackRouteWorks() {
		
	}
	

	//@Test
	public void testRateLimiterWorks() {
		
	}
	
	private static long getExpiration(){
        return new Date().toInstant()
                .plus(Period.ofDays(1))
                .toEpochMilli();
    }

}
