package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.User;
import com.example.repositories.IUserDAO;

@Service
public class UserService {
	
	//@Autowired
	private IUserDAO uDao;
	
	public UserService(IUserDAO uDao) {
		this.uDao = uDao;
	}
	
	public List<User> getAllUsers(){
		return uDao.findAll();
	}
	
	public User findById(Long userId){
		return uDao.findByUserId(userId);
	}
	
	public User createUser(User user) {
		return uDao.save(user);
	}
	
	public User findUserByEmail(String email) {
		return uDao.findUserByEmail(email);
	}
	
//	public boolean userExistsByEmail(String email) {
//    	return uDao.userExistsByEmail(email);
//    }
   
	public void deleteUser(Long userId) {
		uDao.deleteById(userId);
	}
	

}
