package com.mystore.app.repositories;

import com.mystore.app.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//@EnableJpaRepositories
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.name = ?1")
    Product findByProductName(String name);
    
    @Query("SELECT p FROM Product p WHERE p.category = ?1")
    List<Product> findProductByCat(String category);
    
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findByPriceRange(@Param("minPrice") double minPrice , @Param("maxPrice") double maxPrice);
    
    
    @Query("SELECT p FROM Product p WHERE p.stockQuantity BETWEEN :minRange AND :maxRange")
    List<Product> findProductByQuantity(@Param("minRange") int minRange,@Param("maxRange") int maxRange);
    

}
