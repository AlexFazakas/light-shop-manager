import java.util.Vector;

public class MediumMarket extends Magazin {

	MediumMarket(String nume, Vector<Factura> facturi) {
		super(nume, facturi);
		this.setTip("MediumMarket");
	}

	
	@Override
	public double calculScutiriTaxe() {
		
		Vector<String> categorii = new Vector<>();
		
		for(Factura factura : getFacturi())
			for(ProdusComandat produsComandat : factura.getProduseComandate())
				if(categorii.contains(produsComandat.getProdus().getCategorie()) == false)
					categorii.add(produsComandat.getProdus().getCategorie());
		
		for(String categorie : categorii) {
			double sum = 0;
			
			for(Factura factura : getFacturi())
				for(ProdusComandat produsComandat : factura.getProduseComandate())
					if(produsComandat.getProdus().getCategorie().equals(categorie))
						sum = sum + produsComandat.getProdus().getPret() * produsComandat.getCantitate() * produsComandat.getTaxa();
			
			if(sum >= this.getTotalCuTaxe() / 2)
				return 0.95;
			
		}
		return 1;
	}
	
	public String toString() {
		String result = new String();
		result = result + "[Tip: " + this.getTip() + ", " + super.toString() + "]";
		return result;
	}

}
