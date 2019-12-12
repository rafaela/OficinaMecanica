package br.com.mecanica.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class OrdemServico {
	@Id
	@SequenceGenerator(name = "os-id", sequenceName = "os_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "os-id")
	private Long id;
	
	@OneToOne
	private Veiculo veiculo;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataOrcamento;
	@Temporal(TemporalType.DATE)
	private Calendar dataServico;
	private Double valor;
	private String status = Status.CRIADA.getSituacao();
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy="ordemServico")
	private List<Item> itens = new ArrayList<>();
	private String servico;
	private Boolean pagamento;
	
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public Calendar getDataOrcamento() {
		return dataOrcamento;
	}
	
	public void setDataOrcamento(Calendar dataOrcamento) {
		this.dataOrcamento = dataOrcamento;
	}
	
	public Calendar getDataServico() {
		return dataServico;
	}
	
	public void setDataServico(Calendar dataServico) {
		this.dataServico = dataServico;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Item> getItens() {
		return itens;
	}
	
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public Boolean getPagamento() {
		return pagamento;
	}

	public void setPagamento(Boolean pagamento) {
		this.pagamento = pagamento;
	}
	
}
