package restaurante;
import exceptions.PratoInvalidoException;;

public class PratoSimples extends Prato{
	
	
	public PratoSimples(String nome, double preco,String descricao) throws PratoInvalidoException{
		super(nome, preco, descricao);
	}

}
