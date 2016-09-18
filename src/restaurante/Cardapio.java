package restaurante;

import java.util.HashSet;

public class Cardapio {
	private HashSet<Prato> pratos;
	private HashSet<Refeicao> refeicoes;
	
	public Cardapio(){
		this.pratos = new HashSet<Prato>();
		this.refeicoes = new HashSet<Refeicao>();
	}
	
	public HashSet<Prato> getPratos() {
		return pratos;
	}
	
	public HashSet<Refeicao> getRefeicoes() {
		return refeicoes;
	}

	public void addPrato(Prato prato){
		this.pratos.add(prato);
	}
	
	public void removePrato(Prato prato){
		this.pratos.remove(prato);
	}
	
	public void addRefeicao(Refeicao refeicao){
		this.refeicoes.add(refeicao);
	}
	
	public void removeRefeicao(Refeicao refeicao){
		this.refeicoes.remove(refeicao);
	}

	public String consultaCardapioPrato(String nome, String atributo){
		Prato prato = this.buscaPrato(nome);		
		if(atributo.equalsIgnoreCase("preco")){
			String stringPreco = String.format("%.2f", prato.getPreco());
			stringPreco = stringPreco.replace(".", ",");
			return "R$" + stringPreco;
		}
		if(atributo.equalsIgnoreCase("descricao")){
			return prato.getDescricao();
		}
		return null;
	}
	
	public Prato buscaPrato(String nome){
		for(Prato prato : pratos){
			if(nome.equals(prato.getNome())){
				return prato;
			}
		}
		return null;
	}
	
	public String consultaCardapioRefeicao(String nome, String atributo){
		Refeicao refeicao = this.buscaRefeicao(nome);		
		if(atributo.equalsIgnoreCase("preco")){
			String stringPreco = String.format("%.2f", refeicao.getPreco());
			stringPreco = stringPreco.replace(".", ",");
			return "R$" + stringPreco;
		}
		if(atributo.equalsIgnoreCase("descricao")){
			return refeicao.toString();
		}
		return null;
	}
	
	public Refeicao buscaRefeicao(String nome){
		for(Refeicao refeicao : refeicoes){
			if(nome.equals(refeicao.getNome())){
				return refeicao;
			}
		}
		return null;
	}
	
	public boolean contemPrato(String nome){
		if (this.buscaPrato(nome) != null){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean contemRefeicao(String nome){
		if (this.buscaRefeicao(nome) != null){
			return true;
		} else {
			return false;
		}
	}

}
