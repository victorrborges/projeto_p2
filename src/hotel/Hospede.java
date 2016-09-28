package hotel;

import java.util.ArrayList;
import java.util.List;

public class Hospede {
	private String nome;
	private String email;
	private String dataDeNascimento;
	private List<Estadia> estadias;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataDeNascimento == null) ? 0 : dataDeNascimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	
	public void adicionaEstadia(String id, String diaria, int qtdeDias){
		Estadia estadia = new Estadia(id, diaria, qtdeDias);
		this.estadias.add(estadia);
	}
	public boolean verificaQuarto(String quarto){
		for(Estadia estadia : estadias){
			if(estadia.getQuarto().getId().equals(quarto)){
				return true;
			}
		}return false;
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

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Hospede)) {
			return false;
		}
		Hospede hospede = (Hospede) obj;

		if (hospede.getAno().equals(getAno()) && hospede.getNome().equals(getNome())
				&& hospede.getEmail().equals(getEmail())) {
			return true;
		} else {
			return false;
		}
	}

}
