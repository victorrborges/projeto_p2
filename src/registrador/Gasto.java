package registrador;

import java.io.Serializable;

public class Gasto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeHospede;
	private String nomeProduto;
	private String preco;

	public Gasto(String nomeHospede, String nomeProduto, String preco) {
		this.nomeHospede = nomeHospede;
		this.nomeProduto = nomeProduto;
		this.preco = preco;
	}

	public String getNomeHospede() {
		return nomeHospede;
	}

	public void setNomeHospede(String nome) {
		this.nomeHospede = nome;
	}
	
	public String getNomeProduto() {
		return this.nomeProduto;
	}
	
	public void setNomeProduto(String nome) {
		this.nomeProduto = nome;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

}
