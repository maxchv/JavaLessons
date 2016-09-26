package ua.dp.itstep.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ua.dp.itstep.domain.Book;
import ua.dp.itstep.domain.Category;

@Service
public class BookServiceImpl implements BookService {

	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
	
	private List<Category> categories;
	private List<Book> books;

	public BookServiceImpl() {
		categories = new ArrayList<>();
		categories.add(new Category(1, "Computer"));
		categories.add(new Category(2, "Travel"));
		categories.add(new Category(3, "Health"));

		books = new ArrayList<>();
		books.add(new Book(1L, "9781771970273", "Servlet & JSP; A Tutorial (2nd Edition)", 
				categories.get(0), "Budi Kurnaiawan", new BigDecimal("54.99")));
		books.add(new Book(2L, "9781771970297", "C#: A Beginner's Tutorial (2nd Edition)", 
				categories.get(0), "Jayden Ky", new BigDecimal("39.99")));
	}

	@Override
	public List<Category> getAllCategories() {
		return categories;
	}

	@Override
	public Category getCategory(int id) {
		 return categories.stream()
				          .filter(c -> c.getId() == id)
				          .findFirst()
				          .orElse(null);
	}

	@Override
	public List<Book> getAllBooks() {		
		return books;
	}

	@Override
	public Book save(Book book) {
		book.setId(getNextId());
		books.add(book);
		return book;
	}

	@Override
	public Book update(Book book) {
		Integer id = IntStream.range(0, books.size())
		         .filter(index -> books.get(index).equals(book))
		         .findFirst()
		         .getAsInt();
		if(id != null) {
			 books.set(id, book);
			 return book;
		}
		return null;
	}

	@Override
	public Book getBook(long id) {
		 return books.stream()
					 .filter(b -> b.getId() == id)
					 .findFirst()
					 .orElse(null);
	}

	 @Override
	 public long getNextId() {
		 return books.stream()
					 .map(b -> b.getId())
					 .max(Long::compareTo)
					 .orElse(0L);
	 }	
}
