package com.mystore.app.service;

import com.mystore.app.entity.Product;
import com.mystore.app.exception.ProductNotFoundException;
import com.mystore.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private Integer currentId = 1;

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        product.setId(currentId++);
        productRepository.save(product);
        return product;
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    public Product getProduct(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.get();
    }

    public Product updateProduct(Integer id, Product product) {
        String validateMessage = validatePrice(product.getPrice());
        if(!validateMessage.isEmpty()) {
        	throw new IllegalArgumentException(validateMessage);
        }
        Product p = productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product with Id"+id+"not found"));
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setCategory(product.getCategory());
        p.setStockQuantity(product.getStockQuantity());
        productRepository.save(p);
        return p;
    }
    
    private String validatePrice(double price) {
    	if(price < 100) {
    		return "Please don't add any product with price lesser than 100";
    	}
    	if(price > 50000) {
    		return "This platform doesn't allow high priced products. Prices must be <= 50000";
    	}
    	return "";
    }

    public String deleteProduct(Integer id) {
        Product p = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product with Id"+ id+"not found"));
        if (p == null) return "Product Not Found";
        productRepository.delete(p);
        return "Product Deleted Successfully";
    }

    public Product getProductByName(String name) {
    	Product p = productRepository.findByName(name);
    	return p;
    	
    }
    
    public List<Product> getProductBycategory(String category){
    	List<Product> p = productRepository.findProductByCat(category);
    	return p;
    }
    
    public List<Product> getProductByPrice(double minPrice,double maxPrice){
    	return productRepository.findByPriceRange(minPrice, maxPrice);
    	
    }
    
    public List<Product> getProductByQuantity(int minRange , int maxRange){
    	return productRepository.findProductByQuantity(minRange, maxRange);
    }


}
