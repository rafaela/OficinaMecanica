package br.com.mecanica.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.mecanica.dao.DAO;
import br.com.mecanica.modelo.Cliente;


//@ViewScoped
@ManagedBean
public class ClienteBean {
	private Cliente cliente = new Cliente();
	private List<Cliente> clientes;
	
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void grava() {
		DAO<Cliente> dao = new DAO<Cliente>(Cliente.class);
		
		if(cliente.getId() == null) {
			dao.adicionar(cliente);
		}else {
			dao.atualizar(cliente);
		}
		
		this.cliente = new Cliente();
		this.clientes = dao.listaTodos();
	}
	
	public void remove(Cliente cliente) {
		DAO<Cliente> dao = new DAO<Cliente>(Cliente.class);
		dao.remover(cliente);
		
		this.cliente = new Cliente();
		clientes = dao.listaTodos();
	}
	
	public List<Cliente> getClientes() {
		DAO<Cliente> dao = new DAO<Cliente>(Cliente.class);
		clientes = dao.listaTodos();
		if(clientes == null) clientes = new ArrayList<Cliente>();
		
		return clientes;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void cancela() {
		this.cliente = new Cliente();
	}
}