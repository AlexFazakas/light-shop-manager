import java.util.Vector;

public class HyperMarket extends Magazin {

	HyperMarket(String nume, Vector<Factura> facturi) {
		super(nume, facturi);
		this.setTip("HyperMarket");
	}
	
	@Override
	public double calculScutiriTaxe() {
		for(Factura factura : this.getFacturi())
			if(factura.getTotalCuTaxe() >= this.getTotalCuTaxe() / 10)
				return 0.99;
		return 1;
	}
	
	public String toString() {
		String result = new String();
		result = result + "[Tip: " + this.getTip() + ", " + super.toString() + "]";
		return result;
	}

}
