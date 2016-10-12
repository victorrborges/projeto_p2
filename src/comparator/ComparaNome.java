package comparator;

import java.io.Serializable;
import java.util.Comparator;

import restaurante.Refeicao;

public class ComparaNome implements Comparator<Refeicao>,Serializable{
	
	@Override
	public int compare(Refeicao arg0, Refeicao arg1) {
		return arg0.getNome().compareTo(arg1.getNome());
	}
	
}
