package hotel;

import java.io.IOException;
import restaurante.RestauranteController;
import easyaccept.EasyAccept;
import exceptions.SistemaInvalidoException;

public class HotelFacade {
	private RecepcaoController recepcao;
	private RestauranteController restaurante;

	public HotelFacade() throws IOException {
		this.recepcao = new RecepcaoController();
		this.restaurante = new RestauranteController();
	}

	public void iniciaSistema() {

	}

	public String cadastraHospede(String nome, String email, String dataDeNascimento) throws Exception {
		try {
			return recepcao.cadastraHospede(nome, email, dataDeNascimento);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}

	}

	public void removeHospede(String id) throws Exception {
		try {
			recepcao.removeHospede(id);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}
	}

	public String getInfoHospede(String id, String atributo) throws Exception {
		try {
			return recepcao.getInfoHospede(id, atributo);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}
	}

	public String getInfoHospedagem(String id, String atributo) throws Exception {
		try {
			return recepcao.getInfoHospedagem(id, atributo);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}
	}

	public void atualizaCadastro(String id, String atributo, String valor) throws Exception {
		try {
			recepcao.atualizaCadastro(id, atributo, valor);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}
	}

	public void realizaCheckin(String email, int qtdeDias, String idQuarto, String tipoQuarto) throws Exception {
		try {
			recepcao.realizaCheckin(email, qtdeDias, idQuarto, tipoQuarto);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}
	}

	public String realizaCheckout(String email, String quarto) throws Exception {
		try {
			return recepcao.realizaCheckout(email, quarto);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}
	}

	public String consultaTransacoes(String atributo) {
		return recepcao.consultaTransacoes(atributo);
	}

	public String consultaTransacoes(String atributo, int indice) throws Exception {
		try {
			return recepcao.consultaTransacoes(atributo, indice);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}
	}

	public void cadastraPrato(String nome, double preco, String descricao) throws Exception {
		try {
			restaurante.cadastraPrato(nome, preco, descricao);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}
	}

	public int totalAtivas() {
		return recepcao.totalAtivas();
	}

	public void cadastraRefeicao(String nome, String descricao, String componentes) throws Exception {
		try {
			restaurante.cadastraRefeicao(nome, descricao, componentes);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}
	}

	public String consultaRestaurante(String nome, String atributo) throws Exception {
		try {
			return restaurante.consultaRestaurante(nome, atributo);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}
	}

	public void fechaSistema() {

	}

	public static void main(String[] args) {
		args = new String[] { "hotel.HotelFacade", "acceptance_test/testes_uc1.txt",
				"acceptance_test/testes_uc1_exception.txt", "acceptance_test/testes_uc2.txt",
				"acceptance_test/testes_uc2_exception.txt", "acceptance_test/testes_uc3.txt",
				"acceptance_test/testes_uc3_exception.txt", "acceptance_test/testes_uc4.txt",
				"acceptance_test/testes_uc4_exception.txt" };
		EasyAccept.main(args);
	}

}