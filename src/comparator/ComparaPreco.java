package comparator;

import java.util.Comparator;

import restaurante.Refeicao;

public class ComparaPreco implements Comparator<Refeicao> {

	@Override
	public int compare(Refeicao o1, Refeicao o2) {
		if (o1.calculaPreco() > o2.calculaPreco()) {
			return 1;
		} else if (o1.calculaPreco() < o2.calculaPreco()) {
			return -1;
		} else {
			return 0;
		}
	}

}
