package comparator;

import java.util.Comparator;

import restaurante.Refeicao;

public class ComparaNome implements Comparator<Refeicao> {
	
	@Override
	public int compare(Refeicao arg0, Refeicao arg1) {
		return arg0.getNome().compareTo(arg1.getNome());
	}
	
}
