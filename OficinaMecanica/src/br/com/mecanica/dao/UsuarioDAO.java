package br.com.mecanica.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.mecanica.jpa.JPAUtil;
import br.com.mecanica.modelo.Usuario;


public class UsuarioDAO {
	
	public Usuario existeUsuario(Usuario usuario) {
		EntityManager manager = JPAUtil.getEntityManeger();
		manager.getTransaction().begin();
		Query query = manager.createQuery
				("from Usuario u where u.usuario = :pUsuario "
				+ "and u.senha = :pSenha");
		query.setParameter("pUsuario", usuario.getUsuario());
		query.setParameter("pSenha", usuario.getSenha());
		Usuario user = (Usuario) query.getSingleResult();
		manager.getTransaction().commit();
		return user;
	}

}
