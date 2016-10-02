package ua.dp.itstep.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3112268794487544729L;
	
	
	@Id @GeneratedValue 
	private Long id;	
	private String title;
	private String text;
	
	public Post() {
		
	}
	
	public Post(String title, String text) {
		this.title = title;
		this.text = text;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Long getId() {
		return id;
	}
}
