package restaurante;

import java.util.ArrayList;

public class Refeicao {
	private String nome;
	private String descricao;
	private ArrayList<Prato> pratos;
	private static final double DESCONTO = 0.9;

	public Refeicao(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
		this.pratos = new ArrayList<Prato>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		double precoTotal = 0.0;
		for (Prato prato : pratos) {
			precoTotal += prato.getPreco();
		}
		return precoTotal * DESCONTO;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ArrayList<Prato> getPratos() {
		return pratos;
	}

	public void setPratos(ArrayList<Prato> pratos) {
		this.pratos = pratos;
	}

	public void addPrato(Prato prato) {
		this.pratos.add(prato);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((pratos == null) ? 0 : pratos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Refeicao)) {
			return false;
		}
		Refeicao refeicao = (Refeicao) obj;
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

}
