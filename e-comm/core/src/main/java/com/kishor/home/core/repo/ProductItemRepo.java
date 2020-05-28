package com.kishor.home.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kishor.home.core.entity.ProductItemEnt;

public interface ProductItemRepo extends JpaRepository<ProductItemEnt, Integer> {

}
