package model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Entity implementation class for Entity: Book
 *
 */
@Entity
@Table(name = "books")
public class Book implements Serializable {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(unique = true)
	private Integer id;
	private String title;
	private String description;
	private static final long serialVersionUID = 1L;

	@ManyToMany(mappedBy = "books")
	private Set<Author> authors;

	public Set<Author> getAuthors() {
		return authors;
	}

	public void addAuthor(Author author) {
		if (authors == null) {
			authors = new HashSet<>();
		}
		authors.add(author);
	}

	public Book() {
		super();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String Description) {
		this.description = Description;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", description=" + description + ", authors=" + authors + "]";
	}
}
