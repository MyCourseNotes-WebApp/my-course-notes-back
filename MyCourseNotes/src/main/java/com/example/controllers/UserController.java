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
import com.example.services.UserService;

@CrossOrigin
@RestController
@RequestMapping(value="/user")
public class UserController {
	
	//@Autowired
	//private IUserDAO uDao;
	
	@Autowired
	UserService us; 
	
	// Bcrypt encryption for user password
    //BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
	
	/**
	 * 
	 * @return
	 *  //GET: http://localhost:8086/mcn/user/all
	 */
	@GetMapping(value="/all")
    public List<User> getAllUsers() {
    	return us.getAllUsers();
    }
	/**
	 * 
	 * @param user
	 * @return user
	 * //POST: http://localhost:8086/mcn/user/login
	 * pram body:
	 *  {

        "userName": "test",
        "password": "test",
        "email": "test@mcn.com"
       }
	 */
    @PostMapping(value="/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
    	User current = this.us.findUserByEmail(user.getEmail().toLowerCase());
    	//if user email null, then status 204 
    	if(current == null) {
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	}
    	// if user email, user name, password wrong, then status 204
    	if(!(user.getEmail().equals(current.getEmail()) 
    			&& user.getUserName().equals(current.getUserName()) 
    			&& user.getPassword().equals(current.getPassword()))) {
    		
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	}
    	//otherwise, return user model
    	return ResponseEntity.status(HttpStatus.OK).body(current);	

    }
    
    /**
     * 
     * @param userId
     * @return user
     * //POST: http://localhost:8086/mcn/user/1
     */
    @PostMapping(value="/{id}")
    public User findUserById(@PathVariable("id") Long userId) {
    	User u = this.us.findById(userId);
    	return u;
    }
	
	/**
	 * 
	 * @param user
	 * @return
	 * //POST: http://localhost:8086/mcn/user/new
	 * {
	    "userName": "test",
	    "password": "test",
	    "email": "test@mcn.com"
      }
	 */
	@PostMapping(value="/new")
    public User createUser(@RequestBody User user) {
    	
		user.setUserName(user.getUserName());
    	
		// Will encrypt user password for database security
    	//user.setPassword(encrypt.encode(user.getPassword()));
		user.setPassword(user.getPassword());
    	
    	// sets the email to lowercase to store in the database
    	user.setEmail(user.getEmail().toLowerCase());
    	
        return this.us.createUser(user);
    }
   
	/**
	 * 
	 * @param userId
	 * @param user
	 * @return updated user 
	 *  //PUT: http://localhost:8086/mcn/user/5
    /*
     * {
      
        "userName": "Im updated test",
        "password": "test",
        "email": "imupdatetest@mcn.com"
      }
     */
    @PutMapping(value="/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, @RequestBody User user){
		User u = this.us.findById(userId);
		if(u == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();	
		}
		u.setUserName(user.getUserName());
		u.setEmail(user.getEmail().toLowerCase());
		u.setPassword(user.getPassword());
		final User updatedUser = this.us.createUser(u);
		return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
	}
     
    /**
     * 
     * @param userId
     * @return object of boolean
     *  //DELETE: http://localhost:8086/mcn/user/4
     */
    @DeleteMapping("/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable("id") Long userId){
		
		Optional<User> o = Optional.ofNullable(us.findById(userId));
		Map<String, Boolean> response = new HashMap<>();
		
		if(o.isPresent()) {
			User u = o.get();				
			us.deleteUser(u.getUserId());
			response.put("deleted", Boolean.TRUE);		
		}else {
			response.put("could not delete, something went wrong.", Boolean.FALSE);	
		}
		return response;
	}
}
	
/*	
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
	public ResponseEntity<User> getUserById(@PathVariable("id") Long userId){
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
	//JSON Body: { "userName": "new name xxx", 
	//             "password": "password",
	//             "email": "admin@test.com"
	//             }
	@PostMapping
	public ResponseEntity<List<User>> createUser(@RequestBody User user){
		uDao.save(user);
		return ResponseEntity.status(HttpStatus.OK).body(uDao.findAll());
	}
	//PUT: http://localhost:8086/mcn/user/1
	//JSON Body: { "userName": "updated name xxx"
	//             "password": "password",
	//             "email": "admin@test.com"
	//            }
	@PutMapping(value="/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, @RequestBody User user){
		User u = uDao.findByUserId(userId);
		u.setUserName(user.getUserName());
		final User updatedUser = uDao.save(u);
		return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
	}
	//DELETE: http://localhost:8086/mcn/user/3
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable("id") Long userId){
		
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
	
	//    
//    @PutMapping(value="/{id}")
//    public User updateUser(@PathVariable("id") Long userId,@RequestBody User user) {
//        User u = this.us.findById(userId);
//        u.setEmail(user.getEmail().toLowerCase());
//        u.setUserName(user.getUserName());
//        //password
//        
//        this.us.createUser(u);
//    }
    
*/	
	

