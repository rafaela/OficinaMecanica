package br.com.mecanica.mb;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.mecanica.dao.DAO;
import br.com.mecanica.modelo.Cliente;
import br.com.mecanica.modelo.Veiculo;


@ViewScoped
@ManagedBean
public class VeiculoBean {
	private Veiculo veiculo = new Veiculo();
	private List<Veiculo> veiculos;
	private Long idCliente;
	
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public void grava() {
		DAO<Veiculo> dao = new DAO<Veiculo>(Veiculo.class);
		Cliente cliente = new DAO<>(Cliente.class).buscaPorId(idCliente);
		
		if(cliente == null) return;
		
		veiculo.setCliente(cliente);
		
		if(veiculo.getId() == null) {
			dao.adicionar(veiculo);
		}else {
			dao.atualizar(veiculo);
		}
		
		this.veiculo = new Veiculo();
		this.veiculos = dao.listaTodos();
	}
	
	public void remove(Veiculo veiculo) {
		DAO<Veiculo> dao = new DAO<Veiculo>(Veiculo.class);
		dao.remover(veiculo);
		
		this.veiculo = new Veiculo();
		veiculos = dao.listaTodos();
	}
	
	public List<Veiculo> getVeiculos() {
		veiculos = new DAO<Veiculo>(Veiculo.class).listaTodos();
		
		return veiculos;
	}
	
	public Veiculo getVeiculo() {
		return this.veiculo;
	}

	public void cancela() {
		this.veiculo = new Veiculo();
	}
}
