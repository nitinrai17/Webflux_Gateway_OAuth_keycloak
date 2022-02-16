package com.nitin.webflux.user.UserInfo.service;

import java.lang.management.ManagementFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitin.webflux.user.UserInfo.model.User;
import com.nitin.webflux.user.UserInfo.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
	
	Logger log= LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository repository;
	
	public Flux<User>  getAllUser(){
		log.info("UserService.getAllUser()");
		return repository.findAll();
	}
	
	public Mono<User> getUser(Long id ){
		log.info("UserService.getUser({})",id);
		return repository.findById(id);
	}
	
	public Mono<User> addUser(User user){
		log.info("UserService.addUser({})",user);
		return repository.save(user);
	}
	
	public Mono<User> updateUser(Long id, User user) {
		log.info("UserService.updateUser({},{})",id,user);
		return repository.findById(id)
				.flatMap(user1 -> {
					user.setId(id);
					return repository.save(user);
				}).switchIfEmpty(Mono.empty());
	}
	
	public Mono<Void> deleteById(Long id){
		log.info("UserService.deleteById({})",id);
		return repository.findById(id)
				.flatMap( user-> 
					repository.deleteById(user.getId()));
	}
	
}
