
public class ProdusComandat {
	private Produs produs;
	private double taxa;
	private int cantitate;
	
	public ProdusComandat(Produs produs, double taxa, int cantitate) {
		this.produs = produs;
		this.taxa = taxa;
		this.cantitate = cantitate;
	}
	
	public void setProdus(Produs produs) {
		this.produs = produs;
	}
	
	public Produs getProdus() {
		return this.produs;
	}
	
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}
	
	public double getTaxa() {
		return this.taxa;
	}
	
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	
	public int getCantitate() {
		return this.cantitate;
	}
	
	public String toString() {
		String result = new String();
		result = result + "[Produs: " + getProdus() + ", ";
		result = result + "taxa: " + getTaxa() + ", ";		
		result = result + "cantitate: " + getCantitate() + "]";
		return result;
	}
}
