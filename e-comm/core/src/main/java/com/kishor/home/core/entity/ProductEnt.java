package com.kishor.home.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames={"name", "brand"})
})
public class ProductEnt {
	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	private String brand;
	private String manufacturer;
}
