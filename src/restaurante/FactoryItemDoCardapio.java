package restaurante;

public class FactoryItemDoCardapio {

	public FactoryItemDoCardapio(){
		
	}
	
	public Prato criaPrato(String nome, double preco, String descricao) throws Exception {
		return new Prato(nome, preco, descricao);
	}

	public Refeicao criaRefeicao(String nome, String descricao) throws Exception {
		return new Refeicao(nome, descricao);
	}
}
