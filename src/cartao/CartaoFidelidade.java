package cartao;

public class CartaoFidelidade {
	private int pontos;
	private TipoDeCartaoIF tipoDeCartao;

	public CartaoFidelidade() {
		this.pontos = 0;
		this.tipoDeCartao = new Padrao();

	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public TipoDeCartaoIF getTipoDeCartao() {
		return tipoDeCartao;
	}

	public void setTipoDeCartao(TipoDeCartaoIF tipoDeCartao) {
		this.tipoDeCartao = tipoDeCartao;
	}

	/**
	 * Metodo para verificação do tipo do cartão na qual usamos strategy para
	 * mudança;
	 * 
	 * @return void
	 */
	public void verificaTipo() {
		if (this.getPontos() < 350) {
			this.setTipoDeCartao(new Padrao());
		} else if (this.getPontos() >= 350 && this.getPontos() <= 1000) {
			this.setTipoDeCartao(new Premium());
		} else if (this.getPontos() > 1000) {
			this.setTipoDeCartao(new Vip());
		}
	}

	/**
	 * Metodo para adição no cartão do hospede.
	 * 
	 * @return void
	 * @param valorGasto
	 */
	public void addPontos(double valorGasto) {
		this.setPontos(this.getPontos() + tipoDeCartao.addPontos(valorGasto));
		this.verificaTipo();
	}

	public double aplicaDesconto(double valorCobranca) {
		return tipoDeCartao.aplicaDesconto(valorCobranca);

	}

	/**
	 * Metodo para converter os pontos para o catão, caso a quantidade de pontos
	 * for menor ou igual ao do cartão ele remove os pontos e retorna a quantia
	 * de acordo com o tipo do cartão.
	 * 
	 * @param pontos
	 * @return credito em formatacao de string
	 * @throws Exception
	 */
	public String convertePontos(int pontos) throws Exception {
		if (pontos > this.getPontos()) {
			throw new Exception("Quantidade de pontos indisponivel");
		}
		double credito = tipoDeCartao.credito(pontos);
		this.setPontos(this.getPontos() - pontos);
		this.verificaTipo();
		String creditoRetornado = String.format("%.2f", credito);
		creditoRetornado = creditoRetornado.replace(".", ",");
		return "R$" + creditoRetornado;
	}

}
