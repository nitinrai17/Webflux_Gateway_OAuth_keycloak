package com.nitin.webflux.user.UserInfo.controller;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.time.Duration;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitin.webflux.user.UserInfo.model.Usage;
import com.nitin.webflux.user.UserInfo.model.User;
import com.nitin.webflux.user.UserInfo.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class UserController {

	Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service;

	@GetMapping("/user")
	public Flux<User> getAllUser() {
		log.info("UserController.getAllUser()");
		return service.getAllUser();
	}

	@GetMapping("/user/{id}")
	public Mono<User> getUser(@PathVariable("id") Long id) {
		log.info("UserController.getUser({})", id);
		return service.getUser(id);
	}

	@PostMapping("/user")
	public Mono<User> addUser(@RequestBody User user) {
		log.info("UserController.addUser({})", user);
		return service.addUser(user);
	}

	@PutMapping("/user/{id}")
	public Mono<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
		log.info("UserController.updateUser({},{})", id, user);
		return service.updateUser(id, user);
	}

	@DeleteMapping("/user/{id}")
	public Mono<Void> deleteById(@PathVariable("id") Long id) {
		log.info("UserController.deleteById({})", id);
		return service.deleteById(id);
	}
	
	@GetMapping(value ="/usage", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Usage> getResourceUsage() {
		log.info("UserController.getResourceUsage()");
	
		Random random = new Random();
		
		return Flux.interval(Duration.ofSeconds(2))
			.map( it -> new Usage(random.nextInt(101), 
					random.nextInt(101),
					new Date()));
	}

}
