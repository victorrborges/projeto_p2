package hotel;

public abstract class Quarto {
	private String id;
	private double diaria;

	public Quarto(String id, double diaria){
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
		return diaria;
	}

	public void setDiaria(double diaria) {
		this.diaria = diaria;
	}
	
	
}

