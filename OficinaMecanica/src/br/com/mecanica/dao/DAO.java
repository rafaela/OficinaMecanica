package br.com.mecanica.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.mecanica.jpa.JPAUtil;

public class DAO<T> {
	private final Class<T> classe;

	public DAO(Class<T> classe) {
		this.classe = classe;
	}
	
	public void adicionar(T t) {
		EntityManager em = JPAUtil.getEntityManeger();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remover(T t) {
		EntityManager em = JPAUtil.getEntityManeger();
		em.getTransaction().begin();
		em.remove(em.merge(t));
		em.getTransaction().commit();
		em.close();
	}
	
	public void atualizar(T t) {
		EntityManager em = JPAUtil.getEntityManeger();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
	}
	
	public T buscaPorId(Long id) {
		EntityManager em = JPAUtil.getEntityManeger();
		T t = em.find(classe, id);
		em.close();
		return t;
	}
	
	public List<T> listaTodos() {
		EntityManager em = JPAUtil.getEntityManeger();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> lista = em.createQuery(query).getResultList();
		em.close();
		return lista;
	}
	
}
