package com.mkyong.dao;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;

import org.hibernate.FlushMode;

public abstract class AbstractDAO<T> {
	Class<T> classe;
	@PersistenceContext
	EntityManager entityManager;
	
	AbstractDAO (Class<T> classe){
		this.classe = classe;
	}
	
	public void save (T entity){
		FlushModeType mode = entityManager.getFlushMode();
		System.out.println("-------FlushModeType="+mode.toString());
		entityManager.persist(entity);
	}
	 public T find(long id) {
	        return entityManager.find(classe, id);
}
}
