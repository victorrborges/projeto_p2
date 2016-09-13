package restaurante;

import java.util.ArrayList;

public class RefeicaoCompleta extends Refeicao {
	private ArrayList<Prato> pratos;

	public RefeicaoCompleta(String nome, double preco, String descricao, ArrayList<Prato> pratos) {
		super(nome, preco, descricao);
		this.pratos = new ArrayList<Prato>();
	}

	public ArrayList<Prato> getPratos() {
		return pratos;
	}

	public void setPratos(ArrayList<Prato> pratos) {
		this.pratos = pratos;
	}
	
	public boolean addPrato(Prato prato){
		if (!this.pratos.contains(prato)){
			return this.pratos.add(prato);
		}
		return false;
	}

}
