package br.com.mecanica.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Cliente {
	@Id
	@SequenceGenerator(name = "cliente-id", sequenceName = "cliente_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente-id")
	private Long id;
	private String nome;
	private String telefone;
	/*
	 * @OneToMany(cascade = CascadeType.PERSIST) private List<Veiculo> veiculos =
	 * new ArrayList<>();
	 */
	private String endereco;
	private String email;
	private String cpf;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/*
	 * public List<Veiculo> getVeiculos() { return veiculos; }
	 * 
	 * public void setVeiculos(List<Veiculo> veiculos) { this.veiculos = veiculos; }
	 */
	
}
