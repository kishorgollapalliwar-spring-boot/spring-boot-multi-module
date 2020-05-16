package com.kishor.home.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kishor.home.core.entity.ProductEnt;

public interface ProductRepo extends JpaRepository<ProductEnt, Integer> {

}
