package cartao;

public class Premium implements TipoDeCartaoIF {
	
	private static final double RECOMPENSA = 0.3; 
	private static final double DESCONTO = 0.9;
	private static final double CREDITO = 0.3;
	
	@Override
	public int addPontos(double valorGasto) {
		int valorExtra = 0;
		int pontosExtra = 0;
		if (valorGasto < 100){
			return (int) (valorGasto * RECOMPENSA);
		} else {
			valorExtra =  (int)(valorGasto - 100);
			pontosExtra = valorExtra / 100;
			return (pontosExtra * 10) + (int)(valorGasto * RECOMPENSA);
		}
	}

	@Override
	public double aplicaDesconto(double valorCobranca) {
		return Math.ceil(valorCobranca * DESCONTO);	
	}

	@Override
	public double credito(int pontos) {
		int valorExtra = pontos / 10;
		double creditoExtra = valorExtra * 0.2;
		return (creditoExtra + (pontos * CREDITO));
	}

}
