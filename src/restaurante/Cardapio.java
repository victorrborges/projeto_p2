package restaurante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comparator.ComparaNome;
import comparator.ComparaPreco;
import exceptions.PratoInvalidoException;
import exceptions.RefeicaoInvalidaException;
import exceptions.RestauranteInvalidoException;

public class Cardapio {
	private List<Refeicao> refeicoes;
	private ComparaNome ordenaNome;
	private ComparaPreco ordenaPreco;
	private String ordenacaoAtual;

	public Cardapio() {
		this.refeicoes = new ArrayList<Refeicao>();
		this.ordenaNome = new ComparaNome();
		this.ordenaPreco = new ComparaPreco();
		this.ordenacaoAtual = "";
	}

	/**
	 * Consulta as refeicoes do menu
	 * 
	 * @return Retorna os nomes das refeicoes
	 */
	public String consultaMenu() {
		String saida = "";
		for (Refeicao refeicao : refeicoes) {
			saida += refeicao.getNome() + ";";
		}

		return saida.substring(0, saida.length() - 1);
	}

	public List<Refeicao> getRefeicoes() {
		return refeicoes;
	}

	public void setRefeicoes(List<Refeicao> refeicoes) {
		this.refeicoes = refeicoes;
	}

	/**
	 * Ordena o menu de acordo com o atributo.
	 * 
	 * @param atributo
	 *            Ordena pelo nome se o atributo for "Nome" ou preco se o
	 *            atributo for "Preco"
	 */
	public void ordenaMenu(String atributo) {
		if (atributo.equalsIgnoreCase("Nome")) {
			Collections.sort(refeicoes, ordenaNome);
			ordenacaoAtual = "nome";
		} else if (atributo.equalsIgnoreCase("Preco")) {
			Collections.sort(refeicoes, ordenaPreco);
			ordenacaoAtual = "preco";
		}
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
		RefeicaoCompleta refeicao = new RefeicaoCompleta(nome, descricao);
		String[] arrayComponentes = componentes.split(";");
		if (arrayComponentes.length < 3 || arrayComponentes.length > 4) {
			throw new RefeicaoInvalidaException(
					"Erro no cadastro de refeicao completa. Uma refeicao completa deve possuir no minimo 3 e no maximo 4 pratos.");
		}
		ArrayList<Prato> arrayPratos = arrayPratos(arrayComponentes);
		if (!validaPrato(arrayPratos)) {
			throw new RefeicaoInvalidaException(
					"Erro no cadastro de refeicao. So eh possivel cadastrar refeicoes com pratos ja cadastrados.");
		}

		for (Prato item : arrayPratos) {
			refeicao.addPrato(item);
		}
		refeicoes.add(refeicao);
		this.ordenaMenu(ordenacaoAtual);
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
		refeicoes.add(prato);
		this.ordenaMenu(ordenacaoAtual);
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
			String stringPreco = String.format("%.2f", refeicao.calculaPreco());
			stringPreco = stringPreco.replace(".", ",");
			return "R$" + stringPreco;
		}
		if (atributo.equalsIgnoreCase("descricao")) {
			return refeicao.toString();
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

	public double consultaPreco(String nome) {
		return buscaRefeicao(nome).calculaPreco();
	}

	/**
	 * Busca um Prato no set de Pratos.
	 * 
	 * @param nome
	 *            Nome do prato.
	 * 
	 * @return Retorna um Prato, se o prato foi achado e null caso contrario.
	 */
	private Prato buscaPrato(String nome) {
		for (Refeicao refeicao : refeicoes) {
			if (nome.equals(refeicao.getNome())) {
				Prato prato = (Prato) refeicao;
				return prato;
			}
		}
		return null;
	}

	/**
	 * Busca uma refeicao no menu
	 * 
	 * @param nome
	 *            Nome da refeicao
	 * 
	 * @return Retorna uma Refeicao
	 */
	private Refeicao buscaRefeicao(String nome) {
		for (Refeicao refeicao : refeicoes) {
			if (nome.equals(refeicao.getNome())) {
				return refeicao;
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
	private boolean validaPrato(ArrayList<Prato> pratos) {
		for (Prato prato : pratos) {
			if (!this.refeicoes.contains(prato)) {
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
	public String toString(){
		String saida = "Menu do Restaurante: "+ refeicoes.size()+" itens no cardapio\n";
		int cont = 1;
		for (Refeicao refeicao : refeicoes) {
			saida += "==> Item "+cont+":\n";
			saida += refeicao.toString();
			cont++;
		}return saida;
	}

}
