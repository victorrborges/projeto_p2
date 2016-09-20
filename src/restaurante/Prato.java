package restaurante;

import exceptions.PratoInvalidoException;

;

public class Prato {
	private String nome;
	private double preco;
	private String descricao;

	public Prato(String nome, double preco, String descricao)
			throws PratoInvalidoException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new PratoInvalidoException(
					"Erro no cadastro do prato. Nome do prato esta vazio.");
		}
		if (preco < 0) {
			throw new PratoInvalidoException(
					"Erro no cadastro do prato. Preco do prato eh invalido.");
		}
		if (descricao.trim().isEmpty()) {
			throw new PratoInvalidoException(
					"Erro no cadastro do prato. Descricao do prato esta vazia.");
		}
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
