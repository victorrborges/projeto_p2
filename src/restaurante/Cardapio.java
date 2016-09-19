package restaurante;

import java.util.ArrayList;
import java.util.HashSet;
import exceptions.PratoInvalidoException;
import exceptions.RefeicaoInvalidaException;

public class Cardapio {
	private HashSet<PratoSimples> pratos;
	private HashSet<Refeicao> refeicoes;
	
	public Cardapio(){
		this.pratos = new HashSet<PratoSimples>();
		this.refeicoes = new HashSet<Refeicao>();
	}
	
	public HashSet<PratoSimples> getPratos() {
		return pratos;
	}
	
	public HashSet<Refeicao> getRefeicoes() {
		return refeicoes;
	}

	public void addPrato(PratoSimples prato){
		this.pratos.add(prato);
	}
	
	public void removePrato(PratoSimples prato){
		this.pratos.remove(prato);
	}
	
	public void addRefeicao(Refeicao refeicao){
		this.refeicoes.add(refeicao);
	}
	
	public void removeRefeicao(Refeicao refeicao){
		this.refeicoes.remove(refeicao);
	}

	public String consultaCardapioPrato(String nome, String atributo){
		PratoSimples prato = this.buscaPrato(nome);		
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
	
	public PratoSimples buscaPrato(String nome){
		for(PratoSimples prato : pratos){
			if(nome.equals(prato.getNome())){
				return prato;
			}
		}
		return null;
	}
	private boolean validaPratos(ArrayList<Prato> pratos){
		for (Prato prato : pratos){
			if (!getPratos().contains(prato)){
				return false;
			}
		}
		return true;
	}

	private ArrayList<Prato> arrayPratos(String[] componentes){
		ArrayList<Prato> arrayDePratos = new ArrayList<Prato>();
		for(int i = 0; i < componentes.length; i += 1){
			arrayDePratos.add(buscaPrato(componentes[i]));
		}
		return arrayDePratos;
	}
	public void cadastraRefeicao(String nome, String descricao, String componentes) throws Exception {
		if (componentes.trim().isEmpty()){
			throw new Exception("Erro no cadastro de refeicao. Componente(s) esta(o) vazio(s).");
		}
		Refeicao refeicao = new Refeicao(nome, descricao);
		String[] arrayComponentes = componentes.split(";");
		if (arrayComponentes.length < 3 || arrayComponentes.length > 4){
			throw new Exception("Erro no cadastro de refeicao completa. Uma refeicao completa deve possuir no minimo 3 e no maximo 4 pratos.");
		}
		ArrayList<Prato> arrayPratos = arrayPratos(arrayComponentes);
		if (!validaPratos(arrayPratos)){
			throw new Exception("Erro no cadastro de refeicao. So eh possivel cadastrar refeicoes com pratos ja cadastrados.");
		}
		for(Prato item : arrayPratos){
			refeicao.addPrato(item);
		}
		refeicoes.add(refeicao);	
	}
	public void cadastraPrato(String nome, double preco, String descricao) throws PratoInvalidoException {
		PratoSimples prato = new PratoSimples(nome, preco, descricao);
		pratos.add(prato);
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
