package com.kishor.home.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class ProductItemEnt {
	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	private String description;

	@ManyToOne
	@JoinColumn
	private ProductEnt product;
}
