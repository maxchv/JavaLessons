package ua.dp.itstep.domain;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Book implements Serializable {
	private static final long serialVersionUID = -830898451546695452L;
	private long id;
	private String isbn;
	private String title;
	private Category category;
	private String author;
	BigDecimal price;
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Book() {
	
	}
	public Book(long id, String isbn, String title, 
			Category category, String author, BigDecimal price) {
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.category = category;
		this.author = author;
		this.price = price;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Override
	public String toString() {
		return "#" + id + " Title: " + title + " Category: " + category.getName()
		        + "Author: " + author;
	}
	@Override
	public int hashCode() {
		System.out.println("hash");
		return (int) id;
	}
	@Override
	public boolean equals(Object obj) {
		System.out.println("equals");
		Book book = (Book) obj;
		return id == book.id;
	}
}
