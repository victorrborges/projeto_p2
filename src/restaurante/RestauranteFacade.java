package restaurante;

import easyaccept.EasyAccept;

public class RestauranteFacade {
	private RestauranteController restauranteController;
	
	public void iniciaSistema() {
	}

	public RestauranteFacade(){
		this.restauranteController = new RestauranteController();
	}

	public void cadastraPrato(String nome, double preco, String descricao) throws Exception {
		restauranteController.cadastraPrato(nome, preco, descricao);
	}
	
	public void cadastraRefeicao(String nome, String descricao, String componentes) throws Exception {
		restauranteController.cadastraRefeicao(nome, descricao, componentes);
	}
	
	public String consultaRestaurante(String nome, String atributo) throws Exception {
		return restauranteController.consultaRestaurante(nome, atributo);
	}
	
	public void fechaSistema() {
	}

	public static void main(String[] args) {
		args = new String[] { "restaurante.RestauranteFacade", "acceptance_test/testes_uc4.txt",
				"acceptance_test/testes_uc4_exception.txt"};
		System.out.println();
		EasyAccept.main(args);
	}
}
