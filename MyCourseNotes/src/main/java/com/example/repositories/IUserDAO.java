package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.User;

public interface IUserDAO extends JpaRepository<User, Integer>{
	
	User findByUserName(String userName);
	User findByUserId(int userId);
}
