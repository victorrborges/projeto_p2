package restaurante;

import easyaccept.EasyAccept;

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

	public void cadastraRefeicao(String nome, String descricao, String componentes) throws Exception {
		cardapio.cadastraRefeicao(nome, descricao, componentes);	
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
	
	public void fechaSistema() {
	}
	
	public static void main(String[] args) {
		args = new String[] { "restaurante.RestauranteController", "acceptance_test/testes_uc4.txt",
				"acceptance_test/testes_uc4_exception.txt"};
		System.out.println();
		EasyAccept.main(args);
	}
}
