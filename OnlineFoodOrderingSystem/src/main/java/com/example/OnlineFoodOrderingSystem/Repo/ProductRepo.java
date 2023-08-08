package com.example.OnlineFoodOrderingSystem.Repo;

import com.example.OnlineFoodOrderingSystem.Entiry.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductRepo extends JpaRepository<Product,Integer> {
    List<Product> findByCategory(String category);
}
