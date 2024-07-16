package com.pos.iduka.repository;

import com.pos.iduka.model.db.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
