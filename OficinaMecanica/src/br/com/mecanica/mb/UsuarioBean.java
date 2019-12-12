package br.com.mecanica.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mecanica.dao.DAO;
import br.com.mecanica.dao.UsuarioDAO;
import br.com.mecanica.modelo.Usuario;

@SessionScoped
@ManagedBean
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios;
	
	public String loga() {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario user = new Usuario();
		user = dao.existeUsuario(usuario);
		if(user != null) {
			usuario = user;
			return "index?faces-redirect=true";
		}else {
			usuario = new Usuario();
			return "login?faces-redirect=true";
		}
	}
	
	public void grava() {
		DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
		if(usuario.getId() == null) 
			dao.adicionar(usuario);
		else
			dao.atualizar(usuario);
		usuario = new Usuario();
		this.usuarios = dao.listaTodos();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
		usuarios = dao.listaTodos();
		if(usuarios == null)
			usuarios = new ArrayList<Usuario>();
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public boolean isLogado() {
		return usuario.getUsuario() != null;
	}
	
	public String logout() {
		usuario = new Usuario();
		return "login?faces-redirect=true";
	}
	
	public void cancela() {
		usuario = new  Usuario();
	}
	
	
}
