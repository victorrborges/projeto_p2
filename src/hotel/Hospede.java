package hotel;

public class Hospede {
	private String nome;
	private String email;
	private String datadenascimento;
	
	public Hospede(String nome, String email,String datadenascimento){
		this.nome = nome;
		this.email = email;
		this.datadenascimento = datadenascimento;
		;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datadenascimento == null) ? 0 : datadenascimento.hashCode());
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
		if (datadenascimento == null) {
			if (other.datadenascimento != null)
				return false;
		} else if (!datadenascimento.equals(other.datadenascimento))
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
		return datadenascimento;
	}

	public void setAno(String dataDeNascimento) {
		this.datadenascimento = dataDeNascimento;
	}
	
}
