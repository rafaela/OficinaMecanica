package br.com.mecanica.modelo;

public enum Status {
	CRIADA("Criada"),
	APROVADA("Aprovada"),
	CONCLUIDA("Concluída"),
	PAGA("Paga");
	
	private String situacao;


	private Status(String situacao) {
		this.situacao = situacao;
	}
	
	public String getSituacao() {
		return situacao;
	}
	
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
}
