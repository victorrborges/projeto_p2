package cartao;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Vip implements TipoDeCartaoIF {
	
	private static final double RECOMPENSA = 0.5;
	private static final double DESCONTO = 0.85;
	private static final double CREDITO = 0.7;
	
	@Override
	public int addPontos(double valorGasto) {
		return (int)(valorGasto * RECOMPENSA);
	}

	@Override
	public double aplicaDesconto(double valorCobranca) {
		double extra = 0.0;
		
		if(valorCobranca >= 100){
			int centenas = (int) (valorCobranca/100);
			extra = 10 * centenas;
		} 
		
		double precoDescontado = Math.round((valorCobranca * DESCONTO - extra) * 100);
		
		return precoDescontado / 100;
	}

	@Override
	public double credito(int pontos) {
		int valorExtra = pontos / 10;
		double creditoExtra = valorExtra * 0.5;
		return (creditoExtra + (pontos * CREDITO));
	}
	

}
