package com.example.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Notebook;
import com.example.services.NotebookService;

@CrossOrigin
@RestController
@RequestMapping(value="/notebook")
public class NotebookController {
	
	@Autowired
	NotebookService ns;

	/**
	 * 
	 * @param notebook
	 * @return all notebooks that belong to a specific user
	 * POST: http://localhost:8086/mcn/notebook/user=3/all
	 * //if user id not found then return empty list
	 */
	@PostMapping(value = "/user={id}/all")
	public List<Notebook> findNotebookByUserId (@PathVariable("id") Long userId) {	

		List<Notebook> list = ns.findNotebooksByUser(userId);
		for(int i = 0; i < list.size(); i++) {
			list.get(i).setUserId(userId);
		}
		return list;
	}
	
	/**
	 * 
	 * @param notebookId
	 * @return notebook
	 * if can't find notebookId then return null
	 *POST: http://localhost:8086/mcn/notebook/6
	 */
	@PostMapping(value = "/{id}")
	public Optional<Notebook> findNotebookByNotebookId(@PathVariable("id") Long notebookId) {
		Optional<Notebook> o = ns.findNotebookById(notebookId);
		Notebook n = null;
		if(o.isPresent()) {
			 n = o.get();
		}
		return Optional.ofNullable(n);
	}
		
	/**
	 * 
	 * @param notebook
	 * @return notebook
	 * 
	 *POST: //http://localhost:8086/mcn/notebook/new
		BODY:  {
	    "userId": 3,
	    "title": "java",
	    "color": "green",
	    "startDate": "2020-01-01",
	    "editedDate": ""
	     }
	 */
	@PostMapping(value="/new")
	public Notebook createNotebook (@RequestBody Notebook n) {
	
		n.setColor(n.getColor());
		n.setTitle(n.getTitle());
		n.setStartDate(n.getStartDate());
		n.setEditedDate(n.getEditedDate());
		return this.ns.insertNotebook(n);			
	}
	

}



////@Autowired
//private INotebookDAO nDao;
//
////@Autowired
//private IUserDAO uDao;
//
//@Autowired
//public NotebookController(INotebookDAO nDao, IUserDAO uDao) {
//	super();
//	this.nDao = nDao;
//	this.uDao = uDao;
//}
//
////GET: http://localhost:8086/mcn/notebook
//@GetMapping
//public ResponseEntity<List<Notebook>> getAllNotebooks(){
//	return ResponseEntity.status(HttpStatus.OK).body(nDao.findAll());
//}

//@GetMapping("/{userId}")
//public ResponseEntity<List<Notebook>> getAllNotebooksByUserId(@PathVariable (value = "userId") Long userId) {
//    //return nDao.findByUserId(userId);
//	return ResponseEntity.status(HttpStatus.OK).body(nDao.findByUserId(userId));
//    		
//}
//@PostMapping("/{userId}/notebooks")
//public Notebook createNotebook(@PathVariable (value = "userId") Long userId,
//                              @RequestBody Notebook notebook) {
//    return uDao.findById(userId).map(user -> {
//    	notebook.setUser(user);
//    	return nDao.save(notebook);
//    }).orElseThrow(() -> new RuntimeException("UserId " + userId + " not found"));
// 		
//}

//private INotebookDAO nDao;
//private IUserDAO uDao;
//
//@Autowired
//public NotebookController(INotebookDAO nDao, IUserDAO uDao) {
//	super();
//	this.nDao = nDao;
//	this.uDao = uDao;
//}
////GET: http://localhost:8086/mcn/notebook
//@GetMapping
//public ResponseEntity<List<Notebook>> getAllNotebooks(){
//	return ResponseEntity.status(HttpStatus.OK).body(nDao.findAll());
//}
//
////GET: http://localhost:8086/mcn/notebook/user=9
//@GetMapping(value="/user={id}")
//public ResponseEntity<List<Notebook>> getAllNotebookByUserId(@PathVariable("id") int userId){
//	Optional<User> o = uDao.findById(userId);
//	
//	if(o.isPresent()) {
//		User u = o.get();
//		return ResponseEntity.status(HttpStatus.OK).body(nDao.
//				
//				
//				nDao.findAllNotebookByUserId(u.getUserId()));
//	}else {
//		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//	}			
//}
//
////GET: http://localhost:8086/mcn/notebook/1
//@GetMapping(value="/{id}")
//public ResponseEntity<Notebook> geNotebookById(@PathVariable("id") int notebookId){
//	Optional<Notebook> o = nDao.findById(notebookId);
//	if(o.isPresent()) {
//		Notebook n = o.get();			
//		return ResponseEntity.status(HttpStatus.OK).body(n);
//		
//	}else {
//		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//	}		
//}
//
//@PostMapping
//public ResponseEntity<List<Notebook>> newNotebook(@RequestBody Notebook notebook){
//	nDao.save(notebook);
//	return ResponseEntity.status(HttpStatus.OK).body(nDao.findAll());
//}
//
