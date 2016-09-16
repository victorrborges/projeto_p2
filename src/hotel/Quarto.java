package hotel;

public class Quarto {
	private String id;
	private Quartos diaria;

	public Quarto(String id, Quartos diaria){
		this.id = id;
		this.diaria = diaria;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getDiaria() {
		return diaria.getPreco();
	}

	public void setDiaria(double diaria) {
		this.diaria.setPreco(diaria);
	}
	
	
}

