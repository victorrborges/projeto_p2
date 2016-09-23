package restaurante;

import exceptions.RestauranteInvalidoException;

public class RestauranteController {
	private Cardapio cardapio;

	public RestauranteController() {
		this.cardapio = new Cardapio();
	}

	public void iniciaSistema() {
	}

	/**
	 * Responsavel por cadastrar um prato simples no cardapio.
	 * 
	 * @param nome
	 *            Nome do prato que sera cadastrado.
	 * @param preco
	 *            Preco do prato que sera cadastrado.
	 * @param descricao
	 *            Descricao do prato que sera cadastrado.
	 * @throws RestauranteInvalidoException
	 */
	public void cadastraPrato(String nome, double preco, String descricao) throws RestauranteInvalidoException {
		cardapio.cadastraPrato(nome, preco, descricao);
	}

	/**
	 * Responsavel por cadastrar uma Refeicao no cardapio. Uma refeicao
	 * representa um conjunto de pratos.
	 * 
	 * @param nome
	 *            Nome da refeicao.
	 * @param descricao
	 *            Descricao da refeicao.
	 * @param componentes
	 *            Pratos que compoem a refeicao.
	 * @throws RestauranteInvalidoException
	 */
	public void cadastraRefeicao(String nome, String descricao, String componentes)
			throws RestauranteInvalidoException {
		cardapio.cadastraRefeicao(nome, descricao, componentes);
	}

	/**
	 * Responsavel por buscar no Cardapio um Prato ou uma Refeicao.
	 * 
	 * @param nome
	 *            Nome de uma Refeicao ou de um Prato.
	 * @param atributo
	 *            Consulta o preco, ou a descricao, de um Prato ou Refeicao.
	 * @return Retorna uma String, que pode ser a Descricao ou o Preco no
	 *         formato: "R$00,00"
	 * @throws RestauranteInvalidoException
	 */
	public String consultaRestaurante(String nome, String atributo) throws RestauranteInvalidoException {
		if (nome.trim().isEmpty()) {
			throw new RestauranteInvalidoException("Erro na consulta do restaurante. Nome do prato esto vazio.");
		}
		if (cardapio.contemPrato(nome)) {
			return cardapio.consultaCardapioPrato(nome, atributo);
		} else if (cardapio.contemRefeicao(nome)) {
			return cardapio.consultaCardapioRefeicao(nome, atributo);
		}
		return null;
	}

	public void fechaSistema() {

	}

}
