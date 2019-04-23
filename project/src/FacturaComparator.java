import java.util.Comparator;

public class FacturaComparator implements Comparator<Factura>{

//Comparator folosit pentru sortarea facturilor conform cerintei
	@Override
	public int compare(Factura arg0, Factura arg1) {
		return (int)(100*(arg0.getTotalCuTaxe() - arg1.getTotalCuTaxe()));
	}

}
