package com.example.models;

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
@Table(name="notes")
public class Note {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="note_id")
	private Long noteId;
	
	@Column(name="text")
	private String text;
	
	@Column(name="link")
	private Boolean link;
	
    ////We create one transient field for notebookId.
	//It will take input from front-end and 
	//do the rest of the process which help to maintain relationship with notebooks table. 
	private transient Long notebookId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "notebook_id")
	@JsonBackReference
	private Notebook notebook;

	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Note(Long noteId, String text, Boolean link, Long notebookId, Notebook notebook) {
		super();
		this.noteId = noteId;
		this.text = text;
		this.link = link;
		this.notebookId = notebookId;
		this.notebook = notebook;
	}

	public Note(String text, Boolean link, Long notebookId, Notebook notebook) {
		super();
		this.text = text;
		this.link = link;
		this.notebookId = notebookId;
		this.notebook = notebook;
	}

	public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getLink() {
		return link;
	}

	public void setLink(Boolean link) {
		this.link = link;
	}

	public Long getNotebookId() {
		return notebookId;
	}

	public void setNotebookId(Long notebookId) {
		this.notebookId = notebookId;
	}

	public Notebook getNotebook() {
		return notebook;
	}

	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((noteId == null) ? 0 : noteId.hashCode());
		result = prime * result + ((notebook == null) ? 0 : notebook.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Note other = (Note) obj;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (noteId == null) {
			if (other.noteId != null)
				return false;
		} else if (!noteId.equals(other.noteId))
			return false;
		if (notebook == null) {
			if (other.notebook != null)
				return false;
		} else if (!notebook.equals(other.notebook))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", text=" + text + ", link=" + link + ", notebook=" + notebook + "]";
	}
	
	

}
