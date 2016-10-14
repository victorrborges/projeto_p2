package restaurante;

import java.util.ArrayList;

public class RefeicaoCompleta extends Refeicao {

	private ArrayList<Prato> pratos;
	private static final double DESCONTO = 0.9;

	/**
	 * Classe refeicao, possui um nome, uma descricao propria e uma sequencia de
	 * pratos
	 * 
	 * @param nome
	 *            Nome da refeicao
	 * @param descricao
	 *            Descricao da refeicao
	 */
	public RefeicaoCompleta(String nome, String descricao) {
		super(nome, 0, descricao);
		this.pratos = new ArrayList<Prato>();
	}

	/**
	 * Retorna o preco da refeicao com o desconto
	 * 
	 * @return
	 */

	public ArrayList<Prato> getPratos() {
		return pratos;
	}

	public void setPratos(ArrayList<Prato> pratos) {
		this.pratos = pratos;
	}

	/**
	 * Adiciona um prato na lista de pratos da refeicao.
	 * 
	 * @param prato
	 *            Um Prato.
	 */
	public void addPrato(Prato prato) {
		this.pratos.add(prato);
	}

	public double calculaPreco() {
		double precoTotal = 0.0;
		for (Prato prato : pratos) {
			precoTotal += prato.getPreco();
		}
		return precoTotal * DESCONTO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((super.getDescricao() == null) ? 0 : super.getDescricao().hashCode());
		result = prime * result + ((pratos == null) ? 0 : pratos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof RefeicaoCompleta)) {
			return false;
		}
		RefeicaoCompleta refeicao = (RefeicaoCompleta) obj;
		if (refeicao.getDescricao().equals(getDescricao()) && refeicao.getPratos().equals(getPratos())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		String toString = this.getDescricao() + " Serao servidos: ";
		int indice = 0;
		for (Prato prato : pratos) {
			indice += 1;
			toString += "(" + indice + ") " + prato.getNome() + ", ";
		}
		toString = toString + ".";
		toString = toString.replace(", .", ".");
		return toString;

	}

	public String pratosRefeicao() {
		String saida = "";
		for (Prato prato : pratos) {
			saida += prato.getNome() + ", ";
		}
		return saida.substring(0, saida.length() - 2) + "\n";
	}

}
