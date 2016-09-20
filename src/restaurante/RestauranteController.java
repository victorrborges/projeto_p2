package restaurante;

import exceptions.RestauranteInvalidoException;

public class RestauranteController {
	private Cardapio cardapio;
	
	public RestauranteController(){
		this.cardapio = new Cardapio();
	}
	
	public void iniciaSistema() {
	}
	
	public void cadastraPrato(String nome, double preco, String descricao) throws Exception {
		cardapio.cadastraPrato(nome, preco, descricao);
	}

	public void cadastraRefeicao(String nome, String descricao, String componentes) throws RestauranteInvalidoException{
		cardapio.cadastraRefeicao(nome, descricao, componentes);	
	}
	
	public String consultaRestaurante(String nome, String atributo) throws Exception {
		if (nome.trim().isEmpty()){
			throw new RestauranteInvalidoException("Erro na consulta do restaurante. Nome do prato esto vazio.");
		}
		if (cardapio.contemPrato(nome)){
			return cardapio.consultaCardapioPrato(nome, atributo);
		} else if (cardapio.contemRefeicao(nome)){
			return cardapio.consultaCardapioRefeicao(nome, atributo);
		}
		return null;
	}
	
	public void fechaSistema() {

	}

}
