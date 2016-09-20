package hotel;

import java.io.IOException;
import java.util.List;

import restaurante.RestauranteController;
import easyaccept.EasyAccept;
import exceptions.SistemaInvalidoException;

public class HotelFacade {
	private HotelController recepcao;
	private RestauranteController restaurante;

	public HotelFacade() throws IOException {
		this.recepcao = new HotelController();
		this.restaurante = new RestauranteController();
	}

	public void iniciaSistema() {

	}

	public String cadastraHospede(String nome, String email,
			String dataDeNascimento) throws SistemaInvalidoException {
		return recepcao.cadastraHospede(nome, email, dataDeNascimento);
	}

	public void removeHospede(String id) throws Exception {
		recepcao.removeHospede(id);
	}

	public String getInfoHospede(String id, String atributo) throws Exception {
		return recepcao.getInfoHospede(id, atributo);
	}

	public String getInfoHospedagem(String id, String atributo)
			throws Exception {
		return recepcao.getInfoHospedagem(id, atributo);
	}

	public String getTotal(List<Estadia> estadias) {
		return recepcao.getTotal(estadias);
	}

	public int totalAtivas() {
		return recepcao.totalAtivas();
	}

	public void atualizaCadastro(String id, String atributo, String valor)
			throws Exception {
		recepcao.atualizaCadastro(id, atributo, valor);
	}

	public void realizaCheckin(String email, int qtdeDias, String idQuarto,
			String tipoQuarto) throws Exception {
		recepcao.realizaCheckin(email, qtdeDias, idQuarto, tipoQuarto);
	}

	public String realizaCheckout(String email, String quarto) throws Exception {
		return recepcao.realizaCheckout(email, quarto);
	}

	public String consultaTransacoes(String atributo) {
		return recepcao.consultaTransacoes(atributo);
	}

	public String consultaTransacoes(String atributo, int indice)
			throws Exception {
		return recepcao.consultaTransacoes(atributo, indice);
	}

	public void cadastraPrato(String nome, double preco, String descricao)
			throws Exception {
		restaurante.cadastraPrato(nome, preco, descricao);
	}

	public void cadastraRefeicao(String nome, String descricao,
			String componentes) throws Exception {
		restaurante.cadastraRefeicao(nome, descricao, componentes);
	}

	public String consultaRestaurante(String nome, String atributo)
			throws Exception {
		return restaurante.consultaRestaurante(nome, atributo);
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
				"acceptance_test/testes_uc4_exception.txt" };
		EasyAccept.main(args);
	}

}