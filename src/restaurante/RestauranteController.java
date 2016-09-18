package restaurante;

import java.util.ArrayList;

public class RestauranteController {
	private FactoryItemDoCardapio factoryItemDoCardapio;
	private Cardapio cardapio;
	
	public RestauranteController(){
		this.cardapio = new Cardapio();
		this.factoryItemDoCardapio = new FactoryItemDoCardapio();
	}
	
	public void cadastraPrato(String nome, double preco, String descricao) throws Exception {
		Prato prato = factoryItemDoCardapio.criaPrato(nome, preco, descricao);
		cardapio.addPrato(prato);
	}

	public void cadastraRefeicao(String nome, String descricao, String componentes) throws Exception {
		if (componentes.trim().isEmpty()){
			throw new Exception("Erro no cadastro de refeicao. Componente(s) esta(o) vazio(s).");
		}
		Refeicao refeicao = factoryItemDoCardapio.criaRefeicao(nome, descricao);
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
		cardapio.addRefeicao(refeicao);		
	}
	
	public String consultaRestaurante(String nome, String atributo) throws Exception {
		if (nome.trim().isEmpty()){
			throw new Exception("Erro na consulta do restaurante. Nome do prato esto vazio.");
		}
		if (cardapio.contemPrato(nome)){
			return cardapio.consultaCardapioPrato(nome, atributo);
		} else if (cardapio.contemRefeicao(nome)){
			return cardapio.consultaCardapioRefeicao(nome, atributo);
		}
		return null;
	}
	
	private ArrayList<Prato> arrayPratos(String[] componentes){
		ArrayList<Prato> arrayDePratos = new ArrayList<Prato>();
		for(int i = 0; i < componentes.length; i += 1){
			arrayDePratos.add(buscaPrato(componentes[i]));
		}
		return arrayDePratos;
	}
	
	private boolean validaPratos(ArrayList<Prato> pratos){
		for (Prato prato : pratos){
			if (!cardapio.getPratos().contains(prato)){
				return false;
			}
		}
		return true;
	}
	
	private Prato buscaPrato(String nome){
		for(Prato prato : cardapio.getPratos()){
			if(prato.getNome().equals(nome)){
				return prato;
			}
		}
		return null;
	}
}
