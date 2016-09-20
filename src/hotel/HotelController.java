package hotel;

import java.util.List;

import exceptions.EstadiaInvalidaException;
import exceptions.HospedeInvalidoException;
import exceptions.RecepcaoInvalidaException;
import exceptions.SistemaInvalidoException;

import java.util.ArrayList;
import java.util.HashMap;

import validacao.ValidaHospede;

public class HotelController {
	private HashMap<Hospede, List<Estadia>> cadastros;
	private List<Hospede> historicoHospedes;
	private List<String> historicoDeGastos;
	private double total;
	private String saindo = "";
	private ValidaHospede valida;

	public HotelController() {
		this.cadastros = new HashMap<Hospede, List<Estadia>>();
		this.historicoHospedes = new ArrayList<>();
		this.historicoDeGastos = new ArrayList<>();
		valida = new ValidaHospede();
	}

	public void iniciaSistema() {

	}

	public void fechaSistema() {

	}

	public String cadastraHospede(String nome, String email,
			String dataDeNascimento) throws SistemaInvalidoException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new HospedeInvalidoException(
					"Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
		}
		if (!valida.validaNome(nome)) {
			throw new HospedeInvalidoException(
					"Erro no cadastro de Hospede. Nome do(a) hospede esta invalido.");
		}
		if (email == null || email.trim().isEmpty()) {
			throw new HospedeInvalidoException(
					"Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.");
		}
		if (!valida.validaEmail(email)) {
			throw new HospedeInvalidoException(
					"Erro no cadastro de Hospede. Email do(a) hospede esta invalido.");
		}
		if (dataDeNascimento == null || dataDeNascimento.trim().isEmpty()) {
			throw new HospedeInvalidoException(
					"Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");
		}
		if (!valida.validaData(dataDeNascimento)) {
			throw new HospedeInvalidoException(
					"Erro no cadastro de Hospede. Formato de data invalido.");
		}
		if (!valida.validaIdade(dataDeNascimento)) {
			throw new HospedeInvalidoException(
					"Erro no cadastro de Hospede. A idade do(a) hospede deve ser maior que 18 anos.");
		}
		Hospede hospede = new Hospede(nome, email, dataDeNascimento);
		List<Estadia> estadias = new ArrayList<Estadia>();
		this.cadastros.put(hospede, estadias);
		return email;
	}

	public void removeHospede(String id) throws SistemaInvalidoException {
		if (!valida.validaEmail(id)) {
			throw new RecepcaoInvalidaException(
					"Erro na remocao do Hospede. Formato de email invalido.");
		}
		cadastros.remove(buscaHospede(id));
	}

	public String getInfoHospede(String id, String atributo)
			throws SistemaInvalidoException {
		Hospede hospede = buscaHospede(id);
		if (hospede == null) {
			throw new RecepcaoInvalidaException(
					"Erro na consulta de hospede. Hospede de email " + id
							+ " nao foi cadastrado(a).");
		}

		if (atributo.equalsIgnoreCase("nome")) {
			return hospede.getNome();
		} else if (atributo.equalsIgnoreCase("Data de Nascimento")) {
			return hospede.getAno();
		}
		return hospede.getEmail();
	}

	public String getInfoHospedagem(String id, String atributo)
			throws SistemaInvalidoException {

		if (id == null || id.trim().isEmpty()) {
			throw new RecepcaoInvalidaException(
					"Erro ao checar hospedagem ativa. Email do(a) hospede nao pode ser vazio.");
		}
		if (!valida.validaEmail(id)) {
			throw new RecepcaoInvalidaException(
					"Erro ao checar hospedagem ativa. Email do(a) hospede esta invalido.");
		}
		Hospede hospede = buscaHospede(id);

		if (hospede == null) {
			throw new RecepcaoInvalidaException(
					"Erro na consulta de hospede. Hospede de email " + id
							+ " nao foi cadastrado(a).");
		}

		List<Estadia> estadias = cadastros.get(hospede);

		if (estadias.size() == 0) {
			throw new RecepcaoInvalidaException(
					"Erro na consulta de hospedagem. Hospede "
							+ hospede.getNome() + " nao esta hospedado(a).");
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

	public String getTotal(List<Estadia> estadias) {
		double precoTotal = 0;
		for (Estadia est : estadias) {
			precoTotal += est.getGastos();
		}

		return String.format("R$%.2f", precoTotal);
	}

	private boolean verificaQuarto(String quarto) {
		for (Hospede hospede : cadastros.keySet()) {
			for (Estadia est : cadastros.get(hospede)) {
				if (est.getQuarto().getId().equals(quarto)) {
					return true;
				}
			}
		}
		return false;
	}

	public int totalAtivas() {
		int cont = 0;
		for (Hospede hospede : cadastros.keySet()) {
			if (cadastros.get(hospede).size() == 0) {
				cont += 1;
			}

		}
		return cont;
	}

	public void atualizaCadastro(String id, String atributo, String valor)
			throws SistemaInvalidoException {
		Hospede hospede = buscaHospede(id);
		List<Estadia> list = cadastros.get(hospede);
		if (atributo.equalsIgnoreCase("nome")) {
			if (valor == null || valor.trim().isEmpty()) {
				throw new RecepcaoInvalidaException(
						"Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
			}
			if (!valida.validaNome(valor)) {
				throw new RecepcaoInvalidaException(
						"Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede esta invalido.");
			}
			hospede.setNome(valor);
		} else if (atributo.equalsIgnoreCase("email")) {
			if (valor == null || valor.trim().isEmpty()) {
				throw new RecepcaoInvalidaException(
						"Erro na atualizacao do cadastro de Hospede. Email do(a) hospede nao pode ser vazio.");
			}
			if (!valida.validaEmail(valor)) {
				throw new RecepcaoInvalidaException(
						"Erro na atualizacao do cadastro de Hospede. Email do(a) hospede esta invalido.");

			}
			hospede.setEmail(valor);

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

			hospede.setAno(valor);
		}
		cadastros.put(hospede, list);
	}

	public void realizaCheckin(String email, int qtdeDias, String idQuarto,
			String tipoQuarto) throws SistemaInvalidoException, Exception {

		if (email == null || email.trim().isEmpty()) {
			throw new RecepcaoInvalidaException(
					"Erro ao realizar checkin. Email do(a) hospede nao pode ser vazio.");
		}
		if (!valida.validaEmail(email)) {
			throw new RecepcaoInvalidaException(
					"Erro ao realizar checkin. Email do(a) hospede esta invalido.");
		}
		if (!valida.validaIdQuarto(idQuarto)) {
			throw new RecepcaoInvalidaException(
					"Erro ao realizar checkin. ID do quarto invalido, use apenas numeros ou letras.");
		}
		if (qtdeDias <= 0) {
			throw new RecepcaoInvalidaException(
					"Erro ao realizar checkin. Quantidade de dias esta invalida.");
		}
		if (getTipoQuarto(tipoQuarto) == null) {
			throw new RecepcaoInvalidaException(
					"Erro ao realizar checkin. Tipo de quarto invalido.");
		}
		if (verificaQuarto(idQuarto)) {
			throw new RecepcaoInvalidaException(
					"Erro ao realizar checkin. Quarto " + idQuarto
							+ " ja esta ocupado.");
		}
		Hospede buscado = buscaHospede(email);
		if (buscado == null) {
			throw new RecepcaoInvalidaException(
					"Erro ao realizar checkin. Hospede de email " + email
							+ " nao foi cadastrado(a).");
		}

		Estadia estadia = new Estadia(idQuarto, getTipoQuarto(tipoQuarto),
				qtdeDias);
		cadastros.get(buscado).add(estadia);
	}

	public String realizaCheckout(String email, String quarto)
			throws SistemaInvalidoException {
		if (email == null || email.trim().isEmpty()) {
			throw new RecepcaoInvalidaException(
					"Erro ao realizar checkout. Email do(a) hospede nao pode ser vazio.");
		}
		if (!valida.validaEmail(email)) {
			throw new RecepcaoInvalidaException(
					"Erro ao realizar checkout. Email do(a) hospede esta invalido.");
		}
		if (!valida.validaIdQuarto(quarto)) {
			throw new RecepcaoInvalidaException(
					"Erro ao realizar checkout. ID do quarto invalido, use apenas numeros ou letras.");
		}
		Hospede hospede = this.buscaHospede(email);
		Estadia estadia = this.buscaEstadia(hospede, quarto);
		double precoTotal = estadia.getGastos();
		historicoHospedes.add(hospede);
		historicoDeGastos.add(String.format("R$%.2f", precoTotal));
		total += precoTotal;
		saindo += String.format("%s;", hospede.getNome());
		cadastros.get(hospede).remove(estadia);
		return String.format("R$%.2f", precoTotal);

	}

	public String consultaTransacoes(String atributo) {
		if (atributo.equalsIgnoreCase("Quantidade")) {
			return String.format("%d", historicoHospedes.size());
		} else if (atributo.equals("Total")) {
			return String.format("R$%.2f", total);
		} else {
			return saindo.substring(0, saindo.length() - 1);
		}

	}

	public String consultaTransacoes(String atributo, int indice)
			throws RecepcaoInvalidaException {
		if (indice < 0 || indice >= historicoHospedes.size()) {
			throw new RecepcaoInvalidaException(
					"Erro na consulta de transacoes. Indice invalido.");
		}

		if (atributo.equalsIgnoreCase("Nome")) {
			return historicoHospedes.get(indice).getNome();
		} else {
			return historicoDeGastos.get(indice);
		}
	}

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

	private Estadia buscaEstadia(Hospede hospede, String quarto) {
		List<Estadia> arrayDeQuartos = this.cadastros.get(hospede);
		for (Estadia estadia : arrayDeQuartos) {
			if (estadia.getQuarto().getId().equalsIgnoreCase(quarto)) {
				return estadia;
			}
		}
		return null;
	}

	private Hospede buscaHospede(String id) {
		for (Hospede hospede : cadastros.keySet()) {
			if (hospede.getEmail().equalsIgnoreCase(id)) {
				return hospede;
			}
		}
		return null;
	}

}