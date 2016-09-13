package restaurante;

import java.util.HashSet;
import java.util.Set;

public class Cardapio {
	private Set<Refeicao> refeicoes;
	
	public Cardapio(){
		this.refeicoes = new HashSet<Refeicao>();
	}

	public boolean addRefeicao(Refeicao refeicao){
		return this.refeicoes.add(refeicao);
	}
	
	public boolean removeRefeicao(Refeicao refeicao){
		return this.removeRefeicao(refeicao);
	}
	
	public double getPreco(Refeicao refeicao){
		for (Refeicao refeicaoCardapio : refeicoes){
			if (refeicao.equals(refeicaoCardapio)){
				return refeicaoCardapio.getPreco();
			}
		}
		return 0.0;
	}
	
	public boolean contemRefeicao(Refeicao refeicao){
		return this.contemRefeicao(refeicao);
	}
}
