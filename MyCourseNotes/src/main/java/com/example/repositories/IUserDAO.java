package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.models.User;

@Repository
public interface IUserDAO extends JpaRepository<User, Long>, CrudRepository<User, Long>{
	
	public User findByUserName(String userName);
	public User findByUserId(Long userId);
	public User findUserByEmail(String email);
	//public boolean userExistsByEmail(String email);
	//public boolean deleteUser(Long userId);
	
	
	
}
