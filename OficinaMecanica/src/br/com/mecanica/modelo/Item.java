package br.com.mecanica.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


@Entity
public class Item {
	@Id
	@SequenceGenerator(name="item_id", sequenceName="item_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="item_id")
	private Long id;
	
	@ManyToOne
	private Peca peca;
	
	@ManyToOne
	private OrdemServico ordemServico;
	
	private Integer quantidade;
	
	public Peca getPeca() {
		return peca;
	}
	
	public void setPeca(Peca peca) {
		this.peca = peca;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return String.format("Item [id=%s, peca=%s, ordemServico=%s, quantidade=%s]", id, peca, ordemServico,
				quantidade);
	}
	
	

}
