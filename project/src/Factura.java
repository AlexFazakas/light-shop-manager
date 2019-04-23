import java.text.DecimalFormat;
import java.util.Vector;

public class Factura {
	private String denumire;
	private Vector<ProdusComandat> produseComandate = new Vector<>();
	private DecimalFormat df = new DecimalFormat("###.####");

//Clasa care defineste elementul de Factura
	public Factura(String denumire, Vector<ProdusComandat> produseComandate) {
		this.setDenumire(denumire);
		this.setProduseComandate(produseComandate);
	}
	
	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}
	
	public String getDenumire() {
		return this.denumire;
	}
	
	public void setProduseComandate(Vector<ProdusComandat> produseComandate) {
		this.produseComandate.addAll(produseComandate);
	}
	
	public Vector<ProdusComandat> getProduseComandate() {
		return this.produseComandate;
	}
	
	public double getTotalFaraTaxe() {
		double result = 0;
		for(ProdusComandat pc : produseComandate)
			result = result + pc.getProdus().getPret() * pc.getCantitate();
		return (Double.parseDouble(df.format(result)));
	}
	
	public double getTotalCuTaxe() {
		double result = 0;
		for(ProdusComandat pc : produseComandate)
			result = result + pc.getProdus().getPret() * pc.getCantitate() * (pc.getTaxa() + 100) / 100;
		return (Double.parseDouble(df.format(result)));
	}
	
	public double getTaxe() {
		double result = 0;
		for(ProdusComandat pc : produseComandate)
			result = result + pc.getTaxa();
		return (Double.parseDouble(df.format(result)));
	}
	
	public double getTotalTaraFaraTaxe(String tara) {
		double result = 0;
		for(ProdusComandat pc : produseComandate)
			if(pc.getProdus().getTaraOrigine().equals(tara) == true)
				result = result + pc.getProdus().getPret() * pc.getCantitate();
		return (Double.parseDouble(df.format(result)));
	}
	
	public double getTotalTaraCuTaxe(String tara) {
		double result = 0;
		for(ProdusComandat pc : produseComandate)
			if(pc.getProdus().getTaraOrigine().equals(tara) == true)
				result = result + pc.getProdus().getPret() * pc.getCantitate() * (pc.getTaxa() + 100) / 100;
		return (Double.parseDouble(df.format(result)));
	}
	
	public double getTaxeTara(String tara) {
		double result = 0;
		for(ProdusComandat pc : produseComandate)
			if(pc.getProdus().getTaraOrigine().equals(tara) == true)
				result = result + pc.getTaxa();
		return (Double.parseDouble(df.format(result)));
	}
	
	public String toString() {
		String result = new String();
		result = result + "[Denumire: " + this.getDenumire() + ", " + this.getProduseComandate() + "]";
		return result;
	}
}
