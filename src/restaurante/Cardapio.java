package restaurante;

import java.util.ArrayList;
import java.util.HashSet;

import exceptions.PratoInvalidoException;
import exceptions.RefeicaoInvalidaException;
import exceptions.RestauranteInvalidoException;

public class Cardapio {
	private HashSet<Prato> pratos;
	private HashSet<Refeicao> refeicoes;

	public Cardapio() {
		this.pratos = new HashSet<Prato>();
		this.refeicoes = new HashSet<Refeicao>();
	}

	/**
	 * Pega o conjunto de Pratos.
	 * 
	 * @return Retorna um Set de Pratos.
	 */
	public HashSet<Prato> getPratos() {
		return pratos;
	}

	/**
	 * Pega o conjunto de Refeicoes.
	 * 
	 * @return Retorna um Set de Refeicoes.
	 */
	public HashSet<Refeicao> getRefeicoes() {
		return refeicoes;
	}

	/**
	 * Adiciona um prato no set pratos.
	 * 
	 * @param prato
	 *            Recebe um Prato.
	 * 
	 */
	public void addPrato(Prato prato) {
		this.pratos.add(prato);
	}

	/**
	 * Remove um prato do set pratos.
	 * 
	 * @param prato
	 *            Recebe um Prato .
	 */
	public void removePrato(Prato prato) {
		this.pratos.remove(prato);
	}

	/**
	 * Adiciona uma refeicao no set de refeicoes.
	 * 
	 * @param refeicao
	 *            Recebe uma Refeicao.
	 */
	public void addRefeicao(Refeicao refeicao) {
		this.refeicoes.add(refeicao);
	}

	/**
	 * Remove uma refeicao no set de refeicoes.
	 * 
	 * @param refeicao
	 *            Recebe uma Refeicao.
	 */
	public void removeRefeicao(Refeicao refeicao) {
		this.refeicoes.remove(refeicao);
	}

	/**
	 * Consulta informacoes de um prato no set de Pratos.
	 * 
	 * @param nome
	 *            O nome do prato.
	 * @param atributo
	 *            O tipo de consulta, se eh o preco ou a descricao.
	 * 
	 * @return Retorna uma String que representa a Descricao do prato ou o seu
	 *         preco, no formato: "R$0,00".
	 */
	public String consultaCardapioPrato(String nome, String atributo) {
		Prato prato = this.buscaPrato(nome);
		if (atributo.equalsIgnoreCase("preco")) {
			String stringPreco = String.format("%.2f", prato.getPreco());
			stringPreco = stringPreco.replace(".", ",");
			return "R$" + stringPreco;
		}
		if (atributo.equalsIgnoreCase("descricao")) {
			return prato.getDescricao();
		}
		return null;
	}

	/**
	 * Busca um Prato no set de Pratos.
	 * 
	 * @param nome
	 *            Nome do prato.
	 * 
	 * @return Retorna um Prato, se o prato foi achado e null caso contrario.
	 */
	public Prato buscaPrato(String nome) {
		for (Prato prato : pratos) {
			if (nome.equals(prato.getNome())) {
				return prato;
			}
		}
		return null;
	}

	/**
	 * Verifica se os pratos da lista de Pratos estao no cardapio.
	 * 
	 * @param pratos
	 *            Lista de pratos.
	 * @return Retorna true se todos estiverem no cardapio, false caso
	 *         contrario.
	 */
	private boolean validaPratos(ArrayList<Prato> pratos) {
		for (Prato prato : pratos) {
			if (!getPratos().contains(prato)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Cria uma lista de pratos a partir dos nomes de cada prato.
	 * 
	 * @param componentes
	 *            Um array de String, em que cada componente do array representa
	 *            o nome de um Prato.
	 * @return Retorna uma lista de Pratos.
	 */
	private ArrayList<Prato> arrayPratos(String[] componentes) {
		ArrayList<Prato> arrayDePratos = new ArrayList<Prato>();
		for (int i = 0; i < componentes.length; i += 1) {
			arrayDePratos.add(buscaPrato(componentes[i]));
		}
		return arrayDePratos;
	}

	/**
	 * Cadastra uma refeicao no cadarpio.
	 * 
	 * @param nome
	 *            Nome do prato.
	 * @param descricao
	 *            Descricao do prato.
	 * @param componentes
	 *            Pratos que a refeicao contem.
	 * @throws RestauranteInvalidoException
	 */
	public void cadastraRefeicao(String nome, String descricao, String componentes)
			throws RestauranteInvalidoException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new RefeicaoInvalidaException("Erro no cadastro de refeicao. Nome da refeicao esta vazio.");
		}
		if (descricao.trim().isEmpty()) {
			throw new RefeicaoInvalidaException("Erro no cadastro de refeicao. Descricao da refeicao esta vazia.");
		}
		if (componentes.trim().isEmpty()) {
			throw new RefeicaoInvalidaException("Erro no cadastro de refeicao. Componente(s) esta(o) vazio(s).");
		}
		Refeicao refeicao = new Refeicao(nome, descricao);
		String[] arrayComponentes = componentes.split(";");
		if (arrayComponentes.length < 3 || arrayComponentes.length > 4) {
			throw new RefeicaoInvalidaException(
					"Erro no cadastro de refeicao completa. Uma refeicao completa deve possuir no minimo 3 e no maximo 4 pratos.");
		}
		ArrayList<Prato> arrayPratos = arrayPratos(arrayComponentes);
		if (!validaPratos(arrayPratos)) {
			throw new RefeicaoInvalidaException(
					"Erro no cadastro de refeicao. So eh possivel cadastrar refeicoes com pratos ja cadastrados.");
		}
		for (Prato item : arrayPratos) {
			refeicao.addPrato(item);
		}
		refeicoes.add(refeicao);
	}

	/**
	 * Cadastra um Prato.
	 * 
	 * @param nome
	 *            Nome do prato.
	 * @param preco
	 *            Preco do prato.
	 * @param descricao
	 *            Descricao do prato.
	 * @throws RestauranteInvalidoException
	 */
	public void cadastraPrato(String nome, double preco, String descricao) throws RestauranteInvalidoException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new PratoInvalidoException("Erro no cadastro do prato. Nome do prato esta vazio.");
		}
		if (preco < 0) {
			throw new PratoInvalidoException("Erro no cadastro do prato. Preco do prato eh invalido.");
		}
		if (descricao.trim().isEmpty()) {
			throw new PratoInvalidoException("Erro no cadastro do prato. Descricao do prato esta vazia.");
		}
		Prato prato = new Prato(nome, preco, descricao);
		pratos.add(prato);
	}

	/**
	 * Consulta informacoes de uma refeicao no set de Refeicoes.
	 * 
	 * @param nome
	 *            Nome da refeicao.
	 * @param atributo
	 *            Informacao que quer saber, preco ou descricao.
	 * @return Retorna Preco se o atributo for preco, Descricao se o atributo
	 *         for descricao.
	 */
	public String consultaCardapioRefeicao(String nome, String atributo) {
		Refeicao refeicao = this.buscaRefeicao(nome);
		if (atributo.equalsIgnoreCase("preco")) {
			String stringPreco = String.format("%.2f", refeicao.getPreco());
			stringPreco = stringPreco.replace(".", ",");
			return "R$" + stringPreco;
		}
		if (atributo.equalsIgnoreCase("descricao")) {
			return refeicao.toString();
		}
		return null;
	}

	/**
	 * Busca uma refeicao pelo nome no set de Refeicoes.
	 * 
	 * @param nome
	 *            Nome da Refeicao.
	 * @return Retorna uma refeicao se houver uma com o nome passado, null caso
	 *         contrario.
	 */
	public Refeicao buscaRefeicao(String nome) {
		for (Refeicao refeicao : refeicoes) {
			if (nome.equals(refeicao.getNome())) {
				return refeicao;
			}
		}
		return null;
	}

	/**
	 * Verifica se o prato ja foi cadastrado.
	 * 
	 * @param nome
	 *            Nome do prato.
	 * @return Retorna true caso ja foi cadastrado, falso caso contrario.
	 */
	public boolean contemPrato(String nome) {
		if (this.buscaPrato(nome) != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Verifica se a refeicao ja foi cadastrada.
	 * 
	 * @param nome
	 *            Nome da refeicao.
	 * @return Retorna true caso ja foi cadastrado, falso caso contrario.
	 */
	public boolean contemRefeicao(String nome) {
		if (this.buscaRefeicao(nome) != null) {
			return true;
		} else {
			return false;
		}
	}

}
