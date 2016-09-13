package hotel;

public class Hospede {
	private String nome;
	private String email;
	private int ano;
	
	public Hospede(String nome, String email,int ano){
		this.nome = nome;
		this.email = email;
		this.ano = ano;
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

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
	
}
