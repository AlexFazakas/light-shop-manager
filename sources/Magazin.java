import java.text.DecimalFormat;
import java.util.Vector;

public abstract class Magazin implements IMagazin {
	
	private String nume;
//Am adaugat acest camp pentru a usura lucrul cu magazine:
	private String tip;
	private Vector<Factura> facturi = new Vector<>();
	private DecimalFormat df = new DecimalFormat("###.####");
	
	public Magazin(String nume, Vector<Factura> facturi) {
		this.setNume(nume);
		this.setFacturi(facturi);
	}
	
	public void setTip(String tip) {
		this.tip = tip;
	}
	
	public String getTip() {
		return tip;
	}
	
	public void setNume(String nume) {
		this.nume = nume;
	}
	
	public String getNume() {
		return this.nume;
	}
	
	public void setFacturi(Vector<Factura> facturi) {
		this.facturi.addAll(facturi);
	}
	
	public Vector<Factura> getFacturi() {
		return this.facturi;
	}
	
	@Override
	public double getTotalFaraTaxe() {
		double result = 0;
		for(Factura f : facturi)
			result = result + f.getTotalFaraTaxe();
		return (Double.parseDouble(df.format(result)));
	}

	@Override
	public double getTotalCuTaxe() {
		double result = 0;
		for(Factura f : facturi)
			result = result + f.getTotalCuTaxe();
		return (Double.parseDouble(df.format(result)));
	}

	@Override
	public double getTotalCuTaxeScutite() {
		return (Double.parseDouble(df.format(this.getTotalCuTaxe() * this.calculScutiriTaxe())));
	}

	@Override
	public double getTotalTaraFaraTaxe(String tara) {
		double result = 0;
		for(Factura f : facturi)
			result = result + f.getTotalTaraFaraTaxe(tara);
		return (Double.parseDouble(df.format(result)));
	}

	@Override
	public double getTotalTaraCuTaxe(String tara) {
		double result = 0;
		for(Factura f : facturi)
			result = result + f.getTotalTaraCuTaxe(tara);
		return (Double.parseDouble(df.format(result)));
	}

	@Override
	public double getTotalTaraCuTaxeScutite(String tara) {
		return (Double.parseDouble(df.format(this.getTotalTaraCuTaxe(tara) * this.calculScutiriTaxe())));
	}

	public String toString() {
		String result = new String();
		result = result + "[Nume: " + getNume() + ", facturi:" + this.getFacturi() + "]";
		return result;
	}
}
