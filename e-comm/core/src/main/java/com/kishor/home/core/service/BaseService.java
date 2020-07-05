package com.kishor.home.core.service;

public interface BaseService<E, D> {

	D getDTO(E e);

	E getEntity(D d);

}