package hotel;

import java.util.ArrayList;
import java.util.List;
import cartao.CartaoFidelidade;

public class Hospede {
	private String nome;
	private String email;
	private String dataDeNascimento;
	private List<Estadia> estadias;
	private CartaoFidelidade cartao;

	/**
	 * Classe Hospede
	 * 
	 * @param nome
	 *            Nome do Hospede
	 * @param email
	 *            Email do Hospede
	 * @param dataDeNascimento
	 *            Data de Nascimento do Hospede
	 */
	public Hospede(String nome, String email, String dataDeNascimento) {
		this.nome = nome;
		this.email = email;
		this.dataDeNascimento = dataDeNascimento;
		this.estadias = new ArrayList<Estadia>();
		this.cartao = new CartaoFidelidade();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAno() {
		return dataDeNascimento;
	}

	public void setAno(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public void adicionaEstadia(String id, String diaria, int qtdeDias) {
		Estadia estadia = new Estadia(id, diaria, qtdeDias);
		this.estadias.add(estadia);
	}

	public boolean verificaQuarto(String quarto) {
		for (Estadia estadia : estadias) {
			if (estadia.getQuarto().getId().equals(quarto)) {
				return true;
			}
		}
		return false;
	}

	public Estadia buscaEstadia(String estadia) {
		for (Estadia estadi : estadias) {
			if (estadi.getQuarto().getId().equals(estadia)) {
				return estadi;
			}
		}
		return null;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public List<Estadia> getEstadias() {
		return estadias;
	}

	public void setEstadias(List<Estadia> estadias) {
		this.estadias = estadias;
	}

	public CartaoFidelidade getCartao() {
		return cartao;
	}
	public String toString(){
		String saida= "Email: "+getEmail()+"\nNome: "  + getNome()+"\nData de nascimento: "+getDataDeNascimento();
		return saida+"\n";
	}

}
