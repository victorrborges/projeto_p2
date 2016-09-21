package restaurante;

public class Prato {
	private String nome;
	private double preco;
	private String descricao;

	/**
	 * 
	 * @param nome
	 *            Nome do prato
	 * @param preco
	 *            Preco do prato
	 * @param descricao
	 *            Descricao do prato
	 */
	public Prato(String nome, double preco, String descricao) {
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

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
