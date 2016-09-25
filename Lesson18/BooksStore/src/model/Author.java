package model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Author
 *
 */
@Entity
@Table(name="authors")
public class Author implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Integer id;
	private String firstName;
	private static final long serialVersionUID = 1L;
	
	@OneToOne(mappedBy="author")
	private AuthorInfo authorInfo;
	
	public AuthorInfo getAuthorInfo() {
		return authorInfo;
	}
	public void setAuthorInfo(AuthorInfo authorInfo) {
		this.authorInfo = authorInfo;
	}

	@ManyToMany
	private Set<Book> books = new HashSet<>();

	public Set<Book> getBooks() {
		return books;
	}
	
	public void addBook(Book book) {
		books.add(book);
	}
	
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	public Author() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer Id) {
		this.id = Id;
	}   
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String FirstName) {
		this.firstName = FirstName;
	}
	@Override
	public String toString() {
		return "Author [id=" + id + ", firstName=" + firstName + ", authorInfo=" + authorInfo + ", book=" + books.toArray() + "]";
	}
	
	
}
