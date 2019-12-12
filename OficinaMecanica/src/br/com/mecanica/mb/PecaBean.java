package br.com.mecanica.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.mecanica.dao.DAO;
import br.com.mecanica.modelo.Peca;

@ManagedBean
public class PecaBean {
	
	private Peca peca = new Peca();
	private List<Peca> pecas;
	
	public void grava() {
		DAO<Peca> dao = new DAO<Peca>(Peca.class);
		if (peca.getId() == null) {
			dao.adicionar(peca);
		}else {
			dao.atualizar(peca);
		}
		peca = new Peca();
		this.pecas = dao.listaTodos();
	}
	
	public void cancela () {
		this.peca = new Peca();
	}
	
	public void remover(Peca peca) {
		DAO<Peca> dao = new DAO<Peca>(Peca.class);
		dao.remover(peca);
		this.pecas = dao.listaTodos();
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public void setPecas(List<Peca> pecas) {
		this.pecas = pecas;
	}

	public List<Peca> getPecas() {
		DAO<Peca> dao = new DAO<Peca>(Peca.class);
		pecas = dao.listaTodos();
		if(pecas == null)
			pecas = new ArrayList<Peca>();
		return pecas;
	}
	
	public String retirar(Peca Peca) {
		DAO<Peca> dao = new DAO<Peca>(Peca.class);
		Peca p = new Peca();
		p = dao.buscaPorId(peca.getId());
		p.setQuantidade(p.getQuantidade() - peca.getQuantidade());
		dao.atualizar(p);
		peca = new Peca();
		return "os?faces-redirect=true";
	}
}
