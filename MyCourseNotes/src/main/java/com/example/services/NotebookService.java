package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Notebook;
import com.example.repositories.INotebookDAO;
import com.example.repositories.IUserDAO;

@Service
public class NotebookService {
	IUserDAO uDao;
	INotebookDAO nDao;
	
	@Autowired
	public NotebookService(IUserDAO uDao, INotebookDAO nDao) {
		this.uDao = uDao;
		this.nDao = nDao;
	}
	
	//Method to find notebook(s) by an user
	public List<Notebook> findNotebooksByUser(long userId) {
		return nDao.findAllNotebooksByUser(uDao.findById(userId).get());
		
	}
	
	public Optional<Notebook> findNotebookById(long notebookId) {
//		Optional<Notebook> o = nDao.findById(notebookId);
//		o.get().setUserId(nDao.findById(notebookId).get().getUserId());
		
		return nDao.findById(notebookId);
	}
	
	public Notebook insertNotebook(Notebook n) {
		n.setUser(uDao.findById(n.getUserId()).get());
		return nDao.save(n);
	}
	
	
}
