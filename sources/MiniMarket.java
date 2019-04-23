import java.util.Vector;

public class MiniMarket extends Magazin {

	MiniMarket(String nume, Vector<Factura> facturi) {
		super(nume, facturi);
		this.setTip("MiniMarket");
	}
	
	@Override
	public double calculScutiriTaxe() {
		Vector<String> tari = new Vector<>();
		for(Factura factura : getFacturi())
			for(ProdusComandat produsComandat : factura.getProduseComandate())
				if(tari.contains(produsComandat.getProdus().getTaraOrigine()) == false)
					tari.add(produsComandat.getProdus().getTaraOrigine());
		for(String tara : tari)
			if(this.getTotalTaraCuTaxe(tara) >= this.getTotalCuTaxe() / 2)
				return 0.9;
		return 1;
	}
	
	public String toString() {
		String result = new String();
		result = result + "[Tip: " + this.getTip() + ", magazin: " + super.toString() + "]";
		return result;
	}

}
