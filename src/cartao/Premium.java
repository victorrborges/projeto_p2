package cartao;

public class Premium implements TipoDeCartaoIF {
	
	private static final double RECOMPENSA = 0.3; 
	private static final double DESCONTO = 0.9;
	private static final double CREDITO = 0.3;
	
	@Override
	public int addPontos(double valorGasto) {
		double pontosExtra = 0;
		if (valorGasto < 100){
			return (int) (valorGasto * RECOMPENSA);
		} else {
			pontosExtra = valorGasto / 100.0;
			return (int) ((pontosExtra * 10) + (valorGasto * RECOMPENSA));
		}
	}

	@Override
	public double aplicaDesconto(double valorCobranca) {
		return valorCobranca * DESCONTO;	
	}

	@Override
	public double credito(int pontos) {
		int valorExtra = pontos / 10;
		double creditoExtra = valorExtra * 0.2;
		return (creditoExtra + (pontos * CREDITO));
	}

}
