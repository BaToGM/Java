package paa.parking.persistence;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityExistsException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class JpaDAO<T, K> implements DAO<T, K> {
	protected EntityManager em;
	protected Class<T> clazz;

	public JpaDAO(EntityManager em, Class<T> entityClass) {
		this.clazz = entityClass;
		this.em = em;
	}

	@Override
	public T find(K id) {
		// Complete este método
		return em.find(clazz, id);
	}

	@Override
	public T create(T t) {
		// Complete este método
		try {
			em.persist(t);
			em.flush();
			em.refresh(t);
			return t;
			} catch (EntityExistsException ex) {
			throw new DAOException("La entidad ya existe", ex);
			}

	}

	@Override
	public T update(T t) {
		// Complete este método
		return (T) em.merge(t);
	}

	@Override
	public void delete(T t) {
		// Complete este método
		 t = em.merge(t); em.remove(t);
	}

	@Override
	public List<T> findAll() {
		
		// Necesitará hacer consultas a la base datos mediante una TypedQuery, bien
		// empleando una sentencia JPQL o una CriteriaQuery 
		// Por ej.: "select t from " + clazz.getName() + " t"
		Query q = em.createQuery("select t from " + clazz.getName() +" t");
		List<T> lista = q.getResultList();
		return lista;
		
	}
}
