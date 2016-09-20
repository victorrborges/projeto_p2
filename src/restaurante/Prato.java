package restaurante;

public class Prato {
	private String nome;
	private double preco;
	private String descricao;

	public Prato(String nome, double preco, String descricao) {
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
	}
	
	/**
	 * Pega o nome do Prato.
	 * 
	 * @return Retorna uma String que representa o nome do Prato.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Muda o nome do Prato.
	 * 
	 * @param nome Novo nome.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Pega o Preco do prato.
	 * 
	 * @return Retorna um double que representa o preco do prato.
	 */
	public double getPreco() {
		return preco;
	}
	
	/**
	 * Muda o Preco do prato.
	 * 
	 * @param preco Novo preco.
	 */
	public void setPreco(double preco) {
		this.preco = preco;
	}

	/**
	 * Pega a Descricao do prato.
	 * 
	 * @return Retorna uma String que representa a descricao do prato.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Muda a descricao.
	 * 
	 * @param descricao Nova descricao.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Prato)) {
			return false;
		}
		Prato prato = (Prato) obj;
		if (prato.getDescricao().equals(getDescricao()) && prato.getPreco() == getPreco()) {
			return true;
		} else {
			return false;
		}
	}

}
