package hotel;

import java.util.List;
import java.io.*;
import exceptions.HospedeInvalidoException;
import exceptions.RecepcaoInvalidaException;
import exceptions.SistemaInvalidoException;
import restaurante.RestauranteController;

import java.util.HashMap;

import validacao.ValidaHospede;

public class HotelController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, Hospede> cadastros;
	private ValidaHospede valida;
	private Registrador registrador;
	private RestauranteController restaurante;

	/**
	 * Responsavel pela logica de funcionamente
	 */
	public HotelController() {
		this.cadastros = new HashMap<String, Hospede>();
		this.registrador = new Registrador();
		valida = new ValidaHospede();
		this.restaurante = new RestauranteController();
	}

	/**
	 * Cadastra um hospede, caso seu nome, email e data de nascimento passados
	 * sejam validos
	 *
	 * @param nome
	 *            Nome do hospede
	 * @param email
	 *            Email do hospede
	 * @param dataDeNascimento
	 *            Data de nascimento do hospede
	 * @return email Retorna email do hospede.
	 * @throws SistemaInvalidoException
	 */

	public String cadastraHospede(String nome, String email, String dataDeNascimento) throws SistemaInvalidoException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new HospedeInvalidoException("Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
		}
		if (!valida.validaNome(nome)) {
			throw new HospedeInvalidoException("Erro no cadastro de Hospede. Nome do(a) hospede esta invalido.");
		}
		if (email == null || email.trim().isEmpty()) {
			throw new HospedeInvalidoException("Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.");
		}
		if (!valida.validaEmail(email)) {
			throw new HospedeInvalidoException("Erro no cadastro de Hospede. Email do(a) hospede esta invalido.");
		}
		if (dataDeNascimento == null || dataDeNascimento.trim().isEmpty()) {
			throw new HospedeInvalidoException(
					"Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");
		}
		if (!valida.validaData(dataDeNascimento)) {
			throw new HospedeInvalidoException("Erro no cadastro de Hospede. Formato de data invalido.");
		}
		if (!valida.validaIdade(dataDeNascimento)) {
			throw new HospedeInvalidoException(
					"Erro no cadastro de Hospede. A idade do(a) hospede deve ser maior que 18 anos.");
		}
		Hospede hospede = new Hospede(nome, email, dataDeNascimento);
		this.cadastros.put(email, hospede);
		return hospede.getEmail();
	}

	/**
	 * Remove um hospede do hotel
	 * 
	 * @param id
	 *            Email do hospede
	 * @throws SistemaInvalidoException
	 */
	public void removeHospede(String id) throws SistemaInvalidoException {
		if (!valida.validaEmail(id)) {
			throw new RecepcaoInvalidaException("Erro na remocao do Hospede. Formato de email invalido.");
		}
		cadastros.remove(id);
	}

	public String getInfoHospede(String id, String atributo) throws SistemaInvalidoException {
		if (!cadastros.containsKey(id)) {
			throw new RecepcaoInvalidaException(
					"Erro na consulta de hospede. Hospede de email " + id + " nao foi cadastrado(a).");
		}

		if (atributo.equalsIgnoreCase("nome")) {
			return cadastros.get(id).getNome();
		} else if (atributo.equalsIgnoreCase("Data de Nascimento")) {
			return cadastros.get(id).getAno();
		} else if (atributo.equalsIgnoreCase("Pontos")) {
			return String.format("%d", cadastros.get(id).getCartao().getPontos());
		}
		return cadastros.get(id).getEmail();
	}

	/**
	 * Recebe o email do hospede e um atributo, retornando informacao do hospede
	 * 
	 * @param id
	 *            Email do hospede
	 * @param atributo
	 *            Pode ser Hospedanges Ativas, Quarto ou Total
	 * @return Retorna o numero de hospedagens ativas, os quartos que estao
	 *         relacionados ao hospede ou o total em relacao ao preco das
	 *         estadias do hospede
	 * @throws SistemaInvalidoException
	 */
	public String getInfoHospedagem(String id, String atributo) throws SistemaInvalidoException {

		if (id == null || id.trim().isEmpty()) {
			throw new RecepcaoInvalidaException(
					"Erro ao checar hospedagem ativa. Email do(a) hospede nao pode ser vazio.");
		}
		if (!valida.validaEmail(id)) {
			throw new RecepcaoInvalidaException("Erro ao checar hospedagem ativa. Email do(a) hospede esta invalido.");
		}
		Hospede hospede = buscaHospede(id);

		if (hospede == null) {
			throw new RecepcaoInvalidaException(
					"Erro na consulta de hospede. Hospede de email " + id + " nao foi cadastrado(a).");
		}

		List<Estadia> estadias = cadastros.get(id).getEstadias();

		if (estadias.size() == 0) {
			throw new RecepcaoInvalidaException(
					"Erro na consulta de hospedagem. Hospede " + hospede.getNome() + " nao esta hospedado(a).");
		}
		if (atributo.equalsIgnoreCase("Hospedagens Ativas")) {
			return String.valueOf(estadias.size());
		} else if (atributo.equalsIgnoreCase("Quarto")) {
			String quartos = "";
			int contador = 0;
			for (Estadia est : estadias) {
				quartos += est.getQuarto().getId();
				if (contador < estadias.size() - 1) {
					quartos += ",";
				}
				contador++;
			}
			return quartos;
		} else {
			return getTotal(estadias);
		}
	}

	/**
	 * Retorna o preco total de acordo com as estadias de um hospede
	 * 
	 * @param estadias
	 *            List de estadias
	 * @return Retorna o preco acumulado de todas estadias.
	 */
	public String getTotal(List<Estadia> estadias) {
		double precoTotal = 0;
		for (Estadia est : estadias) {
			precoTotal += est.getGastos();
		}

		return String.format("R$%.2f", precoTotal);
	}

	/**
	 * Conta a quantidade de hospedagens ativas
	 * 
	 * @return Retorna o numero de hospedagens ativas.
	 */
	public int totalAtivas() {
		return cadastros.keySet().size();
	}

	/**
	 * Atualiza o nome, email ou data de nascimento do hospede
	 * 
	 * @param id
	 *            Email do hospede
	 * @param atributo
	 *            Nome, email ou data de nascimento do hospede
	 * @param valor
	 *            Sera o novo nome, email ou data de nascimento do hospede, de
	 *            acordo com o atributo
	 * @throws SistemaInvalidoException
	 */
	public void atualizaCadastro(String id, String atributo, String valor) throws SistemaInvalidoException {
		if (atributo.equalsIgnoreCase("nome")) {
			if (valor == null || valor.trim().isEmpty()) {
				throw new RecepcaoInvalidaException(
						"Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
			}
			if (!valida.validaNome(valor)) {
				throw new RecepcaoInvalidaException(
						"Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede esta invalido.");
			}
			cadastros.get(id).setNome(valor);
		} else if (atributo.equalsIgnoreCase("email")) {
			if (valor == null || valor.trim().isEmpty()) {
				throw new RecepcaoInvalidaException(
						"Erro na atualizacao do cadastro de Hospede. Email do(a) hospede nao pode ser vazio.");
			}
			if (!valida.validaEmail(valor)) {
				throw new RecepcaoInvalidaException(
						"Erro na atualizacao do cadastro de Hospede. Email do(a) hospede esta invalido.");
			}
			Hospede hospede = cadastros.get(id);
			cadastros.remove(id);
			hospede.setEmail(valor);
			cadastros.put(valor, hospede);

		} else if (atributo.equalsIgnoreCase("data de nascimento")) {
			if (valor == null || valor.trim().isEmpty()) {
				throw new RecepcaoInvalidaException(
						"Erro na atualizacao do cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");
			}
			if (!valida.validaData(valor)) {
				throw new RecepcaoInvalidaException(
						"Erro na atualizacao do cadastro de Hospede. Formato de data invalido.");
			}
			if (!valida.validaIdade(valor)) {
				throw new RecepcaoInvalidaException(
						"Erro na atualizacao do cadastro de Hospede. A idade do(a) hospede deve ser maior que 18 anos.");
			}

			cadastros.get(id).setAno(valor);
		}
	}

	/**
	 * Associa um hospede a uma ou mais estadias
	 * 
	 * @param email
	 *            Email do hospede
	 * @param qtdeDias
	 *            Quantidade de dias da estadia
	 * @param idQuarto
	 *            Id do quarto
	 * @param tipoQuarto
	 *            Tipo do quarto
	 * @throws SistemaInvalidoException
	 */
	public void realizaCheckin(String email, int qtdeDias, String idQuarto, String tipoQuarto)
			throws SistemaInvalidoException {

		if (email == null || email.trim().isEmpty()) {
			throw new RecepcaoInvalidaException("Erro ao realizar checkin. Email do(a) hospede nao pode ser vazio.");
		}
		if (!valida.validaEmail(email)) {
			throw new RecepcaoInvalidaException("Erro ao realizar checkin. Email do(a) hospede esta invalido.");
		}
		if (!valida.validaIdQuarto(idQuarto)) {
			throw new RecepcaoInvalidaException(
					"Erro ao realizar checkin. ID do quarto invalido, use apenas numeros ou letras.");
		}
		if (qtdeDias <= 0) {
			throw new RecepcaoInvalidaException("Erro ao realizar checkin. Quantidade de dias esta invalida.");
		}
		if (getTipoQuarto(tipoQuarto) == null) {
			throw new RecepcaoInvalidaException("Erro ao realizar checkin. Tipo de quarto invalido.");
		}
		if (verificaQuarto(idQuarto)) {
			throw new RecepcaoInvalidaException("Erro ao realizar checkin. Quarto " + idQuarto + " ja esta ocupado.");
		}
		Hospede buscado = cadastros.get(email);
		if (buscado == null) {
			throw new RecepcaoInvalidaException(
					"Erro ao realizar checkin. Hospede de email " + email + " nao foi cadastrado(a).");
		}
		buscado.adicionaEstadia(idQuarto, tipoQuarto, qtdeDias);
	}

	/**
	 * Remove uma estadia do hospede e registra o historico de lucros do hotel
	 * 
	 * @param email
	 *            Email do hospede
	 * @param quarto
	 *            Id do quarto
	 * @return Retorna o preco da estadia
	 * @throws SistemaInvalidoException
	 */
	public String realizaCheckout(String email, String quarto) throws SistemaInvalidoException {
		if (email == null || email.trim().isEmpty()) {
			throw new RecepcaoInvalidaException("Erro ao realizar checkout. Email do(a) hospede nao pode ser vazio.");
		}
		if (!valida.validaEmail(email)) {
			throw new RecepcaoInvalidaException("Erro ao realizar checkout. Email do(a) hospede esta invalido.");
		}
		if (!valida.validaIdQuarto(quarto)) {
			throw new RecepcaoInvalidaException(
					"Erro ao realizar checkout. ID do quarto invalido, use apenas numeros ou letras.");
		}
		Estadia estadia = buscaEstadia(email, quarto);
		double precoTotal = estadia.getGastos();
		Hospede hospede = cadastros.get(email);
		double preco = hospede.getCartao().aplicaDesconto(precoTotal);
		registrador.registraGasto(hospede, preco, quarto);
		cadastros.get(email).getEstadias().remove(estadia);
		hospede.getCartao().addPontos(precoTotal);

		return String.format("R$%.2f", preco);

	}

	/**
	 * Consulta as transacoes realizadas
	 * 
	 * @param atributo
	 *            Quantidade, total ou nome
	 * @return Retorna a quantidade, o total em relacao aos checkouts realizados
	 *         ou o nome dos hospedes que fizeram checkout
	 */
	public String consultaTransacoes(String atributo) {
		return registrador.consultaTransacoes(atributo);
	}

	/**
	 * Consulta as transacoes realizacoes de acordo com o indice
	 * 
	 * @param atributo
	 *            Nome, total
	 * @param indice
	 *            Indice do checkout
	 * @return Retorna o nome do hospede ou o valor total de acordo com o indice
	 *         de checkout realizado
	 * @throws SistemaInvalidoException
	 */
	public String consultaTransacoes(String atributo, int indice) throws SistemaInvalidoException {
		return registrador.consultaTransacoes(atributo, indice);
	}

	/**
	 * Cadastra um prato no cardapio
	 * 
	 * @param nome
	 *            Nome do prato
	 * @param preco
	 *            Preco do prato
	 * @param descricao
	 *            Descricao do prato
	 * @throws Exception
	 */
	public void cadastraPrato(String nome, double preco, String descricao) throws Exception {
		restaurante.cadastraPrato(nome, preco, descricao);
	}

	/**
	 * Cadastra uma refeicao no cardapio
	 * 
	 * @param nome
	 *            Nome da refeicao
	 * @param descricao
	 *            Descricao da refeicao
	 * @param componentes
	 *            Pratos que compoem a refeicao
	 * @throws Exception
	 */
	public void cadastraRefeicao(String nome, String descricao, String componentes) throws Exception {
		restaurante.cadastraRefeicao(nome, descricao, componentes);
	}

	/**
	 * Consulta o restaurante de acordo com o atributo recebido como parametro
	 * 
	 * @param nome
	 *            Nome da refeicao ou prato simples
	 * @param atributo
	 *            Preco ou descricao
	 * @return Retorna uma String, que pode ser a Descricao ou o Preco
	 * @throws Exception
	 */
	public String consultaRestaurante(String nome, String atributo) throws Exception {
		return restaurante.consultaRestaurante(nome, atributo);
	}

	/**
	 * Ordena o menu de acordo com o atributo, nome ou preco
	 * 
	 * @param atributo
	 */
	public void ordenaMenu(String atributo) {
		restaurante.ordenaMenu(atributo);
	}

	public String consultaMenuRestaurante() {
		return restaurante.consultaMenuRestaurante();
	}

	/**
	 * Realiza um pedido no restaurante
	 * 
	 * @param email
	 *            Email do hospede
	 * @param nomeRefeicao
	 *            Nome da refeicao ou prato simples
	 * @return preco Preco da refeicao ou prato simples formatado
	 */
	public String realizaPedido(String email, String nomeRefeicao) {
		double precoTotal = restaurante.realizaPedido(nomeRefeicao);
		Hospede hospede = cadastros.get(email);
		double preco = hospede.getCartao().aplicaDesconto(precoTotal);
		registrador.registraGasto(hospede, preco, nomeRefeicao);
		hospede.getCartao().addPontos(precoTotal);
		return String.format("R$%.2f", preco);
	}

	/**
	 * Converte pontos em credito
	 * 
	 * @param id
	 *            Email do hospede
	 * @param qtdPontos
	 *            Quantidade de pontos
	 * @return credito
	 * @throws Exception
	 */
	public String convertePontos(String id, int qtdPontos) throws Exception {
		Hospede hospede = buscaHospede(id);
		return hospede.getCartao().convertePontos(qtdPontos);
	}

	/**
	 * Verifica se um quarto ja esta ocupado
	 * 
	 * @param quarto
	 *            Id quarto
	 * @return Retorna true caso o quarto esteja ocupado ou false caso
	 *         contrario.
	 */
	private boolean verificaQuarto(String quarto) {
		for (String hospedes : cadastros.keySet()) {
			if (cadastros.get(hospedes).verificaQuarto(quarto)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param tipo
	 *            String correspondente ao tipo de quarto
	 * @return Retorna um tipo de quarto
	 */
	private TipoQuarto getTipoQuarto(String tipo) {
		if (tipo.equalsIgnoreCase("Presidencial")) {
			return TipoQuarto.PRESIDENCIAL;
		} else if (tipo.equalsIgnoreCase("simples")) {
			return TipoQuarto.SIMPLES;
		} else if (tipo.equalsIgnoreCase("luxo")) {
			return TipoQuarto.LUXO;
		}
		return null;
	}

	/**
	 * Busca uma estadia de acordo com o hospede e o quarto
	 * 
	 * @param hospede
	 *            Objeto do tipo Hospede
	 * @param quarto
	 *            Id do quarto
	 * @return Retorna null caso nao encontre a estadia ou um objeto do tipo
	 *         Estadia caso contrario.
	 */
	private Estadia buscaEstadia(String hospede, String quarto) {
		List<Estadia> arrayDeQuartos = this.cadastros.get(hospede).getEstadias();
		for (Estadia estadia : arrayDeQuartos) {
			if (estadia.getQuarto().getId().equalsIgnoreCase(quarto)) {
				return estadia;
			}
		}
		return null;
	}

	/**
	 * Busca um hospede de acordo com seu email
	 * 
	 * @param id
	 *            Email do hospede
	 * @return Retorna um objeto do tipo Hospede caso ele seja encontrado ou
	 *         null caso contrario.
	 */
	private Hospede buscaHospede(String id) {
		if (cadastros.containsKey(id)) {
			return cadastros.get(id);
		}
		return null;
	}

	public void gravaArquivoHospede() throws IOException {
		String FIM_DE_LINHA = System.lineSeparator();
		BufferedWriter out = new BufferedWriter(new FileWriter("arquivos_sistema/relatorios/cad_hospedes.txt"));
		int cont = 1;
		String saida = "Cadastro de Hospedes: " + cadastros.size() + " hospedes registrados" + FIM_DE_LINHA;
		for (Hospede hospede : cadastros.values()) {
			String[] data = hospede.getDataDeNascimento().split("/");
			String dataFormatada = data[2] + "-" + data[1] + "-" + data[0];
			saida += "==>Hospede " + cont + ":" + FIM_DE_LINHA + "Email: " + hospede.getEmail() + FIM_DE_LINHA + "Nome: " + hospede.getNome()
					+ FIM_DE_LINHA + "Data de nascimento: " + dataFormatada + FIM_DE_LINHA + FIM_DE_LINHA;
			cont++;
		}
		out.write(saida);
		out.close();
	}

	public void geraArquivoResumo() throws IOException {
		String FIM_DE_LINHA = System.lineSeparator();
		gravaArquivoHospede();
		registrador.gravaArquivoRegistros();
		restaurante.gravaArquivoPratosRefeicoes();
		BufferedReader cadHospede = new BufferedReader(new FileReader("arquivos_sistema/relatorios/cad_hospedes.txt"));
		BufferedReader cadRestaurante = new BufferedReader(
				new FileReader("arquivos_sistema/relatorios/cad_restaurante.txt"));
		BufferedReader cadRegistro = new BufferedReader(
				new FileReader("arquivos_sistema/relatorios/cad_transacoes.txt"));
		BufferedWriter out = new BufferedWriter(new FileWriter("arquivos_sistema/relatorios/hotel_principal.txt"));
		BufferedReader[] in = { cadHospede, cadRestaurante, cadRegistro };
		String line = "";
		for (int i = 0; i < in.length; i++) {
			out.write("======================================================" + FIM_DE_LINHA);
			while ((line = in[i].readLine()) != null) {
				out.write(line + FIM_DE_LINHA);
			}

		}
		cadHospede.close();
		cadRestaurante.close();
		cadRegistro.close();
		out.close();
	}
}