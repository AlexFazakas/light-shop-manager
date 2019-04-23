import java.util.Comparator;

public class MagazinComparator implements Comparator<Magazin>{

//Clasa folosita pentru sortarea magazinelor conform cerintei:
	@Override
	public int compare(Magazin mag1, Magazin mag2) {
		return (int)(100*(mag1.getTotalFaraTaxe() - mag2.getTotalFaraTaxe()));
	}
	
}
