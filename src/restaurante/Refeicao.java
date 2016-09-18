package restaurante;

import java.util.ArrayList;

public class Refeicao {
	private String nome;
	private String descricao;
	private ArrayList<Prato> pratos;
	private static final double DESCONTO = 0.9;
	
	public Refeicao(String nome, String descricao) throws Exception {
		if (nome == null || nome.trim().isEmpty()){
			throw new Exception("Erro no cadastro de refeicao. Nome da refeicao esta vazio.");
		}
		if (descricao.trim().isEmpty()){
			throw new Exception("Erro no cadastro de refeicao. Descricao da refeicao esta vazia.");
		}
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

	public double getPreco(){
		double precoTotal = 0.0;
		for(Prato prato : pratos){
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
	
	public void addPrato(Prato prato){
		this.pratos.add(prato);
	}
	public String toString(){
		String toString = this.getDescricao() + " Serao servidos: ";
		int indice = 0;
		for (Prato prato : pratos){
			indice += 1;
			toString += "(" + indice + ") " + prato.getNome() + ", ";
		}
		toString = toString + ".";
		toString = toString.replace(", .", ".");
		return toString;
		
	}
}
