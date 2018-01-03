package ch.guggisberg.stefan.groupfitness.services;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class BaseCrud<T>  {

	@PersistenceContext
	protected EntityManager entityManager;

	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> T findSingleResultNamedQuery(String name, Map <String, Object> params) {
		Query query = getEntityManager().createNamedQuery(name);	
		for (String paramName : params.keySet()) {
			query.setParameter(paramName, params.get(paramName));
		}
		return (T) query.getSingleResult();
	}
	
	public T persist(T entity) {
		entityManager.persist(entity);
		entityManager.flush();
		return entity;
	}

	public T find(Class<T> type, Object id) {
		return entityManager.find(type, id);
	}

	public T update(T entity) {
		return entityManager.merge(entity);
	}

	public boolean delete(Class<T> type, Object id) {
		T entity = entityManager.find(type, id);
		if (entity == null) {
			return false;
		}
		entityManager.remove(entity);
		return true;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
