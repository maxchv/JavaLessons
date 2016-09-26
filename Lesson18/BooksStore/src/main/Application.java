package main;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.Author;
import model.AuthorInfo;
import model.Book;

public class Application {
	public static void main(String[] args) {
		EntityManager em = Persistence.createEntityManagerFactory("BooksStore").createEntityManager();
		
		Book book = new Book();
		Author author = new Author();
		
		book.setTitle("first book");
		book.setDescription("important description");
		
		author.addBook(book);
		author.setFirstName("Author");
		
		AuthorInfo info = new AuthorInfo();
		info.setAuthor(author);
		info.setEmail("mail@mail.com");
		info.setUrl("http://url.to.site");
		
		author.setAuthorInfo(info);
		
		book.addAuthor(author);
		
		em.getTransaction().begin();
		em.persist(info);
		em.persist(author);
		em.persist(book);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		Book b = em.find(Book.class, 1);
		em.getTransaction().commit();
		
		for(Author a : b.getAuthors()) {
			System.out.println(a.getFirstName());
			System.out.println(a.getAuthorInfo().getEmail());
		}
		//System.out.println(b.getAuthors().stream().forEach(a->sysouta.getAuthorInfo()));
		em.close();
	}
	
}
