package com.example.OnlineFoodOrderingSystem.Repo;

import com.example.OnlineFoodOrderingSystem.Entiry.OrdersTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrderRepo extends JpaRepository<OrdersTbl,Integer> {
}
