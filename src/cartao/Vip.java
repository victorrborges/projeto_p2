package cartao;

import java.io.Serializable;

public class Vip implements TipoDeCartaoIF{
	
	private static final double RECOMPENSA = 0.5;
	private static final double DESCONTO = 0.85;
	private static final double CREDITO = 0.7;
	private static final double IMPRECISAO = 0.01;
	
	@Override
	public int addPontos(double valorGasto) {
		return (int) (valorGasto * RECOMPENSA);
	}

	@Override
	public double aplicaDesconto(double valorCobranca) {
		double extra = 0.0;
		
		
		if(valorCobranca >= 100){
			int centenas = (int) (valorCobranca/100);
			extra = 10 * centenas;
		} 
		
		double precoDescontado = (valorCobranca * DESCONTO);
		double parteDecimal = (precoDescontado - (int) (valorCobranca*DESCONTO));
		
		if (parteDecimal * 100 == (int) parteDecimal * 100)
			return precoDescontado-extra;
		else
			return precoDescontado-extra+IMPRECISAO;
	}

	@Override
	public double credito(int pontos) {
		int valorExtra = pontos / 10;
		double creditoExtra = valorExtra * 0.5;
		return (creditoExtra + (pontos * CREDITO));
	}
	

}
