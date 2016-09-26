package ua.dp.itstep.service;

import java.util.List;

import ua.dp.itstep.model.Product;

public interface ProductService {
	public  List<Product> getProducts();
	
	public Product addProduct(Product product);
	
	public Product getProductById(int id);
	
	public void deleteProduct(Product product);
	
	public Product updateProduct(Product product);
}
