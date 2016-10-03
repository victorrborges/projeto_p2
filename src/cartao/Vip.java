package cartao;

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
		return valorCobranca * DESCONTO; 
		
	}

	@Override
	public double credito(int pontos) {
		int valorExtra = pontos / 10;
		double creditoExtra = valorExtra * 0.5;
		return (creditoExtra + (pontos * CREDITO));
	}
	

}
