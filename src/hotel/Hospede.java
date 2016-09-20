package hotel;

public class Hospede {
	private String nome;
	private String email;
	private String dataDeNascimento;

	public Hospede(String nome, String email, String dataDeNascimento) {

		this.nome = nome;
		this.email = email;
		this.dataDeNascimento = dataDeNascimento;
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
		result = prime
				* result
				+ ((dataDeNascimento == null) ? 0 : dataDeNascimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Hospede)) {
			return false;
		}
		Hospede hospede = (Hospede) obj;

		if (hospede.getAno().equals(getAno())
				&& hospede.getNome().equals(getNome())
				&& hospede.getEmail().equals(getEmail())) {
			return true;
		} else {
			return false;
		}
	}

}
