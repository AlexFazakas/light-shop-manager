import java.util.Comparator;

public class ProdusComparatorTara implements Comparator<Produs> {
//Clasa folosita pentru sortarea produselor dupa tara:
	@Override
	public int compare(Produs produs1, Produs produs2) {
		return produs1.getTaraOrigine().compareTo(produs2.getTaraOrigine());
	}
}
