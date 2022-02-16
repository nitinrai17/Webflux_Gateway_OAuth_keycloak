package com.nitin.webflux.user.UserInfo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.nitin.webflux.user.UserInfo.model.User;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long>{

}
