package hotel;

import exceptions.HospedeInvalidoException;

public class Hospede {
	private String nome;
	private String email;
	private String dataDeNascimento;

	public Hospede(String nome, String email, String dataDeNascimento) throws HospedeInvalidoException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new HospedeInvalidoException("Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
		}
		if (!validaNome(nome)) {
			throw new HospedeInvalidoException("Erro no cadastro de Hospede. Nome do(a) hospede esta invalido.");
		}
		if (email == null || email.trim().isEmpty()) {
			throw new HospedeInvalidoException("Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.");
		}
		if(!validaEmail(email)){
			throw new HospedeInvalidoException("Erro no cadastro de Hospede. Email do(a) hospede esta invalido.");
		}
		if (dataDeNascimento == null || dataDeNascimento.trim().isEmpty()) {
			throw new HospedeInvalidoException(
					"Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");
		}
		if (!validaData(dataDeNascimento)) {
			throw new HospedeInvalidoException("Erro no cadastro de Hospede. Formato de data invalido.");
		}

		this.nome = nome;
		this.email = email;
		this.dataDeNascimento = dataDeNascimento;
	}

	public boolean validaData(String dataDeNascimento) {
		return dataDeNascimento.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[0-2])/\\d{4}");
	}

	public boolean validaNome(String nome) {
		return nome.trim().matches("[ a-zA-Z]+");
	}

	public boolean validaEmail(String email) {
		if (email.matches("[ a-zA-Z]+@[ a-zA-Z]+\\.[ a-zA-Z]+") || email.matches("[ a-zA-Z]+@[ a-zA-Z]+\\.[ a-zA-Z]+\\.[ a-zA-Z]+")) {
				return true;
		}return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataDeNascimento == null) ? 0 : dataDeNascimento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hospede other = (Hospede) obj;
		if (dataDeNascimento == null) {
			if (other.dataDeNascimento != null)
				return false;
		} else if (!dataDeNascimento.equals(other.dataDeNascimento))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
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
	
	private boolean validaIdade(String data){
		
		return true;
	}

}
