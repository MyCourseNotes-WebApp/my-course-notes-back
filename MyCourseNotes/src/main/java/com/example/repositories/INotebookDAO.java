package com.example.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.models.Notebook;
import com.example.models.User;

@Repository
public interface INotebookDAO extends JpaRepository<Notebook, Long>, CrudRepository<Notebook, Long>{
	
	public List<Notebook> findAllNotebooksByUser(User user);
	
	
	
	//find all notebooks that belong to specific user
	//List<Notebook> findAllNotebookByUserId(int userId);
	//List<Notebook> findByUserId(Long userId);
	
	//find specific notebook that belong to specific user
	//Notebook findNotebookByUserIdNotebookId(int userId, int notebookId);
	
	//Optional<Notebook> findByIdAndUserId(Long notebookId, Long userId);

}
