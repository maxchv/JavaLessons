package ua.dp.itstep.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import ua.dp.itstep.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	private List<Product> products = new ArrayList<Product>();
	
	public ProductServiceImpl() {

		products.add(new Product(1, "First Product", "Good product", new BigDecimal(100)));
		products.add(new Product(2, "Second Product", "Fine product", new BigDecimal(50)));
	
	}
	
	@Override
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public Product addProduct(Product product) {
		products.add(product);
		System.out.println("saved: " + product);
		int maxId = products.get(0).getId();
		for(Product p: products) {
			if(p.getId() > maxId) {
				maxId = p.getId();
			}
		}
		product.setId(maxId + 1);
		
		return product;
	}

	@Override
	public Product getProductById(int id) {
		Product product = null;
		for(Product p: products) {
			if(id == p.getId()) {
				product = p;
				break;
			}
		}
		return product;
	}

	@Override
	public void deleteProduct(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

}
