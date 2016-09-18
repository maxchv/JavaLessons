package ua.dp.itstep.service;

import java.util.List;

import ua.dp.itstep.domain.Book;
import ua.dp.itstep.domain.Category;

public interface BookService {
	List<Category> getAllCategories();
	Category getCategory(int id);
	List<Book> getAllBooks();
	Book save(Book book);
	Book update(Book book);
	Book getBook(long id);
	long getNextId();
}
