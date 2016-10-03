package hotel;

import easyaccept.EasyAccept;
import exceptions.SistemaInvalidoException;

public class HotelFacade {
	private HotelController hotel;

	/**
	 * Delega os metodos a RecepcaoController e ao RestauranteController
	 * 
	 */
	public HotelFacade() {
		this.hotel = new HotelController();
	}

	public void iniciaSistema() {

	}

	public String cadastraHospede(String nome, String email,
			String dataDeNascimento) throws Exception {
		try {
			return hotel.cadastraHospede(nome, email, dataDeNascimento);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}

	}

	public void removeHospede(String id) throws Exception {
		try {
			hotel.removeHospede(id);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}
	}

	public String getInfoHospede(String id, String atributo) throws Exception {
		try {
			return hotel.getInfoHospede(id, atributo);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}
	}

	public String getInfoHospedagem(String id, String atributo)
			throws Exception {
		try {
			return hotel.getInfoHospedagem(id, atributo);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}
	}

	public void atualizaCadastro(String id, String atributo, String valor)
			throws Exception {
		try {
			hotel.atualizaCadastro(id, atributo, valor);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}
	}

	public void realizaCheckin(String email, int qtdeDias, String idQuarto,
			String tipoQuarto) throws Exception {
		try {
			hotel.realizaCheckin(email, qtdeDias, idQuarto, tipoQuarto);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}
	}

	public String realizaCheckout(String email, String quarto) throws Exception {
		try {
			return hotel.realizaCheckout(email, quarto);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}
	}

	public int totalAtivas() {
		return hotel.totalAtivas();
	}

	public String consultaTransacoes(String atributo) {
		return hotel.consultaTransacoes(atributo);
	}

	public String consultaTransacoes(String atributo, int indice)
			throws Exception {
		try {
			return hotel.consultaTransacoes(atributo, indice);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}
	}

	public void cadastraPrato(String nome, double preco, String descricao)
			throws Exception {
		try {
			hotel.cadastraPrato(nome, preco, descricao);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}
	}

	public void cadastraRefeicao(String nome, String descricao,
			String componentes) throws Exception {
		try {
			hotel.cadastraRefeicao(nome, descricao, componentes);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}
	}

	public String consultaRestaurante(String nome, String atributo)
			throws Exception {
		try {
			return hotel.consultaRestaurante(nome, atributo);
		} catch (SistemaInvalidoException ex) {
			throw new SistemaInvalidoException(ex.getMessage());
		}
	}

	public void ordenaMenu(String atributo) {
		hotel.ordenaMenu(atributo);
	}

	public String consultaMenuRestaurante() {
		return hotel.consultaMenuRestaurante();
	}
	
	public String realizaPedido(String email, String refeicao) {
		return hotel.realizaPedido(email, refeicao);
	}

	public void fechaSistema() {

	}

	public static void main(String[] args) {
		args = new String[] { "hotel.HotelFacade",
				"acceptance_test/testes_uc1.txt",
				"acceptance_test/testes_uc1_exception.txt",
				"acceptance_test/testes_uc2.txt",
				"acceptance_test/testes_uc2_exception.txt",
				"acceptance_test/testes_uc3.txt",
				"acceptance_test/testes_uc3_exception.txt",
				"acceptance_test/testes_uc4.txt",
				"acceptance_test/testes_uc4_exception.txt",
				"acceptance_test/testes_uc5.txt", "acceptance_test/testes_uc6.txt" };
		EasyAccept.main(args);
	}

}