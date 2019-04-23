import java.util.Vector;

public class MagazinFactory {

//Clasa ce realizeaza subtipurile de magazin cerute
	public static Magazin buildMagazin(MagazinType tip, String nume, Vector<Factura> facturi) {
		Magazin mag = null;
		
		switch(tip) {
		case MiniMarket:
			mag = new MiniMarket(nume, facturi);
			break;
		case MediumMarket:
			mag = new MediumMarket(nume, facturi);
			break;
		case HyperMarket:
			mag = new HyperMarket(nume, facturi);
			break;
		default:
			break;
		}
		return mag;
	}
}
