package ch.guggisberg.stefan.groupfitness.services;

import java.util.List;
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

	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T>  findListResultNamedQuery(String name, Map <String, Object> params) {
		Query query = getEntityManager().createNamedQuery(name);	
		if (params!=null) {
			for (String paramName : params.keySet()) {
				query.setParameter(paramName, params.get(paramName));
			}
		}
		return (List<T>) query.getResultList();
	}
	@SuppressWarnings("hiding")
	public <T> List<T>  findListResultNamedQuery(String name) {
		return findListResultNamedQuery(name, null);

	}

	//	@SuppressWarnings({ "hiding", "unchecked" })
	//	public <T> List<T>  findListResultNativeNamedQuery(String name, Map <String, Object> params, Class<T> resultClass) {
	//		Query query = getEntityManager().createNativeQuery(name, resultClass);	
	//		for (String paramName : params.keySet()) {
	//			query.setParameter(paramName, params.get(paramName));
	//		}
	//		return (List<T>) query.getResultList();
	//	}

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
