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
	
	/**
	 * 
	 * @return Retorna nome do Hospede
	 */
	public String getNomeHospede() {
		return nomeHospede;
	}
	
	/**
	 * Muda nome do Hospede
	 * @param nome Novo nome
	 */
	public void setNomeHospede(String nome) {
		this.nomeHospede = nome;
	}
	
	/**
	 * 
	 * @return Retorna nome do produto (id do quarto ou nome da refeicao)
	 */
	public String getNomeProduto() {
		return this.nomeProduto;
	}
	
	public void setNomeProduto(String nome) {
		this.nomeProduto = nome;
	}
	
	/**
	 * 
	 * @return Retorna preco da compra.
	 */
	public String getPreco() {
		return preco;
	}
	
	public void setPreco(String preco) {
		this.preco = preco;
	}

}
