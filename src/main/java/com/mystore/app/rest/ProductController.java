package com.mystore.app.rest;

import com.mystore.app.entity.Product;
import com.mystore.app.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("")
	public ResponseEntity<Object> addProduct(@RequestBody @Valid Product product) {
		Product p = productService.addProduct(product);
		return new ResponseEntity<>(p, HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String sortDir) {
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(sortDir), sortBy);

		Page<Product> productsPage = productService.getAllProducts(pageable);

		return ResponseEntity.ok(productsPage);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
		Product p = productService.getProduct(id);
		if (p != null) {
			return new ResponseEntity<>(p, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @Valid @RequestBody Product product) {
		Product p = productService.updateProduct(id, product);
		if (p != null) {
			return new ResponseEntity<>(p, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
		String message = productService.deleteProduct(id);
		return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
	}

	@GetMapping("/getByName/{name}")
	public ResponseEntity<Product> getProductByName(@PathVariable("name") String name) {
		Product p = productService.getProductByName(name);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@GetMapping("/getByCategory/{category}")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("category") String category) {
		List<Product> p = productService.getProductBycategory(category);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@GetMapping("/getByPrice/{minPrice}/{maxPrice}")
	public ResponseEntity<List<Product>> getProductByPrice(@PathVariable("minPrice") double minPrice,
			@PathVariable("maxPrice") double maxPrice) {
		List<Product> p = productService.getProductByPrice(minPrice, maxPrice);
		return new ResponseEntity<>(p, HttpStatus.OK);

	}

	@GetMapping("/getByQuantity/{minRange}/{maxRange}")
	public ResponseEntity<List<Product>> getProductByQuantity(@PathVariable("minRange") int minRange,
			@PathVariable("maxRange") int maxRange) {
		List<Product> p = productService.getProductByQuantity(minRange, maxRange);
		return new ResponseEntity<>(p, HttpStatus.OK);

	}

}
