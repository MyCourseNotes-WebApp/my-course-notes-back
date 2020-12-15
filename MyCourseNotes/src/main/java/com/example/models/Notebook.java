package com.example.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Component
@Entity
@Table(name="notebooks")
public class Notebook {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="notebook_id")
	private Long notebookId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="edited_date")
	private Date editedDate;
	
	@Column(name="color")
	private String color;
	
	////We create one transient field for userId.
	//It will take input from front-end and 
	//do the rest of the process which help to maintain relationship with mcn_users table. 
	private transient Long userId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;

	public Notebook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notebook(Long notebookId, String title, Date startDate, Date editedDate, String color, Long userId,
			User user) {
		super();
		this.notebookId = notebookId;
		this.title = title;
		this.startDate = startDate;
		this.editedDate = editedDate;
		this.color = color;
		this.userId = userId;
		this.user = user;
	}

	public Notebook(String title, Date startDate, Date editedDate, String color, Long userId, User user) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.editedDate = editedDate;
		this.color = color;
		this.userId = userId;
		this.user = user;
	}

	public Long getNotebookId() {
		return notebookId;
	}

	public void setNotebookId(Long notebookId) {
		this.notebookId = notebookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEditedDate() {
		return editedDate;
	}

	public void setEditedDate(Date editedDate) {
		this.editedDate = editedDate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((editedDate == null) ? 0 : editedDate.hashCode());
		result = prime * result + ((notebookId == null) ? 0 : notebookId.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notebook other = (Notebook) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (editedDate == null) {
			if (other.editedDate != null)
				return false;
		} else if (!editedDate.equals(other.editedDate))
			return false;
		if (notebookId == null) {
			if (other.notebookId != null)
				return false;
		} else if (!notebookId.equals(other.notebookId))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Notebook [notebookId=" + notebookId + ", title=" + title + ", startDate=" + startDate + ", editedDate="
				+ editedDate + ", color=" + color + ", user=" + user + "]";
	}

	
	
}
