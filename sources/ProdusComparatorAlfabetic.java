import java.util.Comparator;

public class ProdusComparatorAlfabetic implements Comparator<Produs> {

//Clasa folosita pentru sortarea produselor alfabetic dupa nume
	@Override
	public int compare(Produs produs1, Produs produs2) {
		return produs1.getDenumire().compareTo(produs2.getDenumire());
	}

}
