package com.mystore.app.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product {

	@Id
	private Integer id;

	@NotNull(message = "Product name cannot be null.")
	private String name;
	@NotNull(message = "Category cannot be null.")
	private String category;

	@NotNull(message = "Price cannot be null.")
	@Min(value = 0, message = "Price must be a non-negative value.")
	private Double price;

	@NotNull(message = "Stock quantity cannot be null.")
	@Min(value = 0, message = "Stock quantity must be a non-negative value.")
	private Integer stockQuantity;

	public Product() {
	}

	public Product(Integer id, String name, String category, Double price, Integer stockQuantity) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
}
