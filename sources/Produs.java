
public class Produs {
	private String denumire;
	private String categorie;
	private String taraOrigine;
	private double pret;
	
	public Produs(String denumire, String categorie, String taraOrigine, double pret) {
		this.denumire = denumire;
		this.categorie = categorie;
		this.taraOrigine = taraOrigine;
		this.pret = pret;
	}
	
	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}
	
	public String getDenumire() {
		return this.denumire;
	}
		
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	public String getCategorie() {
		return this.categorie;
	}
	
	public void setPret(double pret) {
		this.pret = pret;
	}
	
	public double getPret() {
		return this.pret;
	}
	
	public void setTaraOrigine(String taraOrigine) {
		this.taraOrigine = taraOrigine;
	}
	
	public String getTaraOrigine() {
		return this.taraOrigine;
	}
	
	public String toString() {
		String result = new String();
		result = result + "[Denumire: " + getDenumire() + ", ";
		result = result + "categorie: " + getCategorie() + ", ";
		result = result + "tara origine: " + getTaraOrigine() + ", ";
		result = result + "pret: " + getPret() + "]";
		return result;
	}
}
