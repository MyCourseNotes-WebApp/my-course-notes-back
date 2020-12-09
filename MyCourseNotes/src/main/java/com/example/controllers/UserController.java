package com.example.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.User;
import com.example.repositories.IUserDAO;

@CrossOrigin
@RestController
@RequestMapping(value="/user")
public class UserController {
	
	private IUserDAO uDao;
	
	@Autowired
	public UserController(IUserDAO uDao) {
		super();
		this.uDao = uDao;
	}
	//GET: http://localhost:8086/mcn/user
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		return ResponseEntity.status(HttpStatus.OK).body(uDao.findAll());
	}
	//GET: http://localhost:8086/mcn/user/1
	@GetMapping(value="/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int userId){
		Optional<User> o = uDao.findById(userId);
		if(o.isPresent()) {
			User u = o.get();			
			return ResponseEntity.status(HttpStatus.OK).body(u);
			
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}		
	}
	//GET: http://localhost:8086/mcn/user/user=admin
	@GetMapping(value="/user={name}")
	public ResponseEntity<User> getUserByName(@PathVariable("name") String userName){
		User u = uDao.findByUserName(userName);
		if(u == null) {		
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();			
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(u);
		}
	}
	//POST: http://localhost:8086/mcn/user 
	//JSON Body: { "userName": "new name xxx"}
	@PostMapping
	public ResponseEntity<List<User>> newUser(@RequestBody User user){
		uDao.save(user);
		return ResponseEntity.status(HttpStatus.OK).body(uDao.findAll());
	}
	//PUT: http://localhost:8086/mcn/user/1
	//JSON Body: { "userName": "updated name xxx"}
	@PutMapping(value="/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int userId, @RequestBody User user){
		User u = uDao.findByUserId(userId);
		u.setUserName(user.getUserName());
		final User updatedUser = uDao.save(u);
		return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
	}
	//DELETE: http://localhost:8086/mcn/user/3
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable("id") int userId){
		
//		User u = uDao.findByUserId(userId);
//		uDao.delete(u);
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("deleted", Boolean.TRUE);
//		return response;	
		
		Optional<User> o = uDao.findById(userId);
		Map<String, Boolean> response = new HashMap<>();
		
		if(o.isPresent()) {
			User u = o.get();	
			uDao.delete(u);
			response.put("deleted", Boolean.TRUE);		
		}else {
			response.put("could not delete, something wrong.", Boolean.FALSE);	
		}
		return response;
	}
	
	
}
