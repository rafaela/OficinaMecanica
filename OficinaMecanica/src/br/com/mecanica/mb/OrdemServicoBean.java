package br.com.mecanica.mb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.mecanica.dao.DAO;
import br.com.mecanica.modelo.Item;
import br.com.mecanica.modelo.OrdemServico;
import br.com.mecanica.modelo.Peca;
import br.com.mecanica.modelo.Status;
import br.com.mecanica.modelo.Veiculo;

@ViewScoped
@ManagedBean
public class OrdemServicoBean {
	private OrdemServico os = new OrdemServico();
	private List<OrdemServico> lista;
	private List<Item> itensOrdem = new ArrayList<Item>();
	private List<OrdemServico> ordensStatus = new ArrayList<OrdemServico>();
	private List<Peca> pecas;
	private Long idVeiculo;
	private Long idPeca;
	private Integer quantidadePeca;
	private String tipoConsulta;

	public List<Peca> getPecas() {
		DAO<Peca> dao = new DAO<Peca>(Peca.class);
		pecas = dao.listaTodos();
		if(pecas == null)
			pecas = new ArrayList<Peca>();
		return pecas;
	}
	
	public void setPecas(List<Peca> pecas) {
		this.pecas = pecas;
	}
	
	public List<Item> getPecasOrdem() {
		return itensOrdem;
	}

	public void setPecasOrdem(List<Item> pecasOrdem) {
		this.itensOrdem = pecasOrdem;
	}

	public void grava() {
		DAO<OrdemServico> dao = new DAO<OrdemServico>(OrdemServico.class);
		DAO<Peca> daoPeca = new DAO<Peca>(Peca.class);
		DAO<Veiculo> daoVeiculo = new DAO<Veiculo>(Veiculo.class);
		
		if(itensOrdem == null) itensOrdem = new ArrayList<Item>();
		
		os.setDataOrcamento(Calendar.getInstance());
		os.setStatus(Status.CRIADA.getSituacao());
		
		os.setVeiculo(daoVeiculo.buscaPorId(idVeiculo));
		if(os.getId() == null) {
			dao.adicionar(os);
		}
		
		List<OrdemServico> os = dao.listaTodos();
		if(os == null)
			os = new ArrayList<OrdemServico>();
		
		OrdemServico ultimaOrdem = os.get(os.size()-1); 
		for(Item item : itensOrdem) {
			item.setOrdemServico(ultimaOrdem);
			item.getPeca().setQuantidade(item.getPeca().getQuantidade()-item.getQuantidade());
			daoPeca.atualizar(item.getPeca());
		}
		
		
		DAO<Item> daoPecasServico = new DAO<Item>(Item.class);
		for (Item item : itensOrdem) {
			daoPecasServico.atualizar(item);
		}
		
		this.os = new OrdemServico();
		itensOrdem = new ArrayList<Item>();
		this.lista = dao.listaTodos();
	}
	
	public void adicionarPeca() {
		DAO<Peca> dao = new DAO<Peca>(Peca.class);
		Item item = new Item();
		
		item.setPeca(dao.buscaPorId(idPeca));
		if(item.getPeca() == null || (quantidadePeca > item.getPeca().getQuantidade())) 
			return;

		item.setQuantidade(quantidadePeca);
		itensOrdem.add(item);
		item = new Item();
		quantidadePeca = null;
		idPeca = null;
	}
	
	
	public void remove(OrdemServico ordem) {
		DAO<OrdemServico> dao = new DAO<OrdemServico>(OrdemServico.class);
		dao.remover(ordem);
		
		this.os = new OrdemServico();
		lista = dao.listaTodos();
	}
	
	public List<OrdemServico> getOrdensStatusCriada() {
		DAO<OrdemServico> dao = new DAO<OrdemServico>(OrdemServico.class);
		List<OrdemServico> os = dao.listaTodos();
		
		ordensStatus = new ArrayList<OrdemServico>();
		
		for (OrdemServico ordemServico : os) {
			if(ordemServico.getStatus().equalsIgnoreCase(Status.CRIADA.getSituacao())) {
				ordensStatus.add(ordemServico);
			}
		}
		
		return ordensStatus;
	}
	
	public List<OrdemServico> getOrdensStatusAprovada() {
		DAO<OrdemServico> dao = new DAO<OrdemServico>(OrdemServico.class);
		List<OrdemServico> os = dao.listaTodos();
		
		ordensStatus = new ArrayList<OrdemServico>();
		
		for (OrdemServico ordemServico : os) {
			if(ordemServico.getStatus().equalsIgnoreCase(Status.APROVADA.getSituacao())) {
				ordensStatus.add(ordemServico);
			}
		}
		
		return ordensStatus;
	}
	
	public List<OrdemServico> getOrdensStatusConcluida() {
		DAO<OrdemServico> dao = new DAO<OrdemServico>(OrdemServico.class);
		List<OrdemServico> os = dao.listaTodos();
		
		ordensStatus = new ArrayList<OrdemServico>();
		
		for (OrdemServico ordemServico : os) {
			if(ordemServico.getStatus().equalsIgnoreCase(Status.CONCLUIDA.getSituacao())) {
				ordensStatus.add(ordemServico);
			}
		}
		
		return ordensStatus;
	}

	public void setOrdensStatus(List<OrdemServico> ordensStatus) {
		this.ordensStatus = ordensStatus;
	}

	public void removePecaOrdem(Item Item) {
		itensOrdem.remove(Item);
	}
	
	public void aprovar(OrdemServico ordemServico) {
		DAO<OrdemServico> dao = new DAO<OrdemServico>(OrdemServico.class);
		ordemServico.setStatus(Status.APROVADA.getSituacao());
		dao.atualizar(ordemServico);
	}
	
	public void concluir(OrdemServico ordemServico) {
		DAO<OrdemServico> dao = new DAO<OrdemServico>(OrdemServico.class);
		ordemServico.setStatus(Status.CONCLUIDA.getSituacao());
		dao.atualizar(ordemServico);
	}
	
	public List<OrdemServico> getOrdemServicos() {
		lista = new DAO<OrdemServico>(OrdemServico.class).listaTodos();	 
		return lista;
	}
	
	public void cancelar() {
		this.os = new OrdemServico();
	}
	
	public OrdemServico getOs() {
		return os;
	}

	public void setOs(OrdemServico os) {
		this.os = os;
	}

	public List<OrdemServico> getLista() {
		DAO<OrdemServico> dao = new DAO<OrdemServico>(OrdemServico.class);
		lista = dao.listaTodos();
		if(lista == null)
			lista = new ArrayList<OrdemServico>();
		return lista;
	}

	public void setLista(List<OrdemServico> lista) {
		this.lista = lista;
	}

	public Long getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(Long idVeiculo) {
		this.idVeiculo = idVeiculo;
	}
	
	public Long getIdPeca() {
		return idPeca;
	}

	public void setIdPeca(Long idPeca) {
		this.idPeca = idPeca;
	}
	

	public Integer getQuantidadePeca() {
		return quantidadePeca;
	}

	public void setQuantidadePeca(Integer quantidadePeca) {
		this.quantidadePeca = quantidadePeca;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public List<Item> getItensOrdem() {
		return itensOrdem;
	}

	public void setItensOrdem(List<Item> itensOrdem) {
		this.itensOrdem = itensOrdem;
	}
	
	
}
