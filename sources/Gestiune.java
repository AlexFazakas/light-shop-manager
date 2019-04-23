import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;

public class Gestiune {
	
	private static Gestiune instance = null;
//Am adaugat cateva liste de care ma folosesc pentru a realiza
//operatiile mai departe in aplicatie:
//Acestea contin tarile, respectiv categoriile produselor.
	public Vector<String> tari = new Vector<>();
	public Vector<String> categorii = new Vector<>();
	public Vector<Produs> produse = new Vector<>();
	public Vector<Magazin> magazine = new Vector<>();
	public Hashtable<String, Hashtable<String, Double>> taxe = new Hashtable<>();

//Clasa Gestiune conform cerintei
	private Gestiune() {
		
	}
	
	public static Gestiune getInstance() {
		if(instance == null)
			instance = new Gestiune();
		return instance;
	}
	
	public void getProduse() throws FileNotFoundException {
//Metoda care citeste produsele in vectorul de produse
//De asemenea initializeaza listele de tari si vectori.
		Scanner sc = new Scanner(new File("src/produse.txt"));
		
		String line = new String(sc.nextLine());
		String[] words = line.split(" ");

		for(String word : words)
			Gestiune.getInstance().tari.add(word);
		
		Gestiune.getInstance().tari.remove(0);
		Gestiune.getInstance().tari.remove(0);
//Parsam fiecare linie:		
		while(sc.hasNextLine()) {
			
			line = sc.nextLine();
			words = line.split(" ");
			//Generam cate un produs folosind numele, categoria si pretul pentru fiecare tara:
			for(String tara : Gestiune.getInstance().tari) {
				
				Double tempDouble = Double.parseDouble(words[Gestiune.getInstance().tari.indexOf(tara) + 2]);
				Produs tempProdus = new Produs(words[0], words[1], tara, tempDouble);
				Gestiune.getInstance().produse.add(tempProdus);
			
			}
		}
		
		for(Produs produs : Gestiune.getInstance().produse)
			if(Gestiune.getInstance().categorii.contains(produs.getCategorie()) == false)
				Gestiune.getInstance().categorii.add(produs.getCategorie());
		
		sc.close();
	}
	
	public void getTaxe() throws FileNotFoundException {

//Aici vom parsa fisierul de taxe:
		Scanner sc = new Scanner(new File("src/taxe.txt"));
		sc.nextLine();
		
		for(String tara : Gestiune.getInstance().tari)
			Gestiune.getInstance().taxe.put(tara, new Hashtable<String, Double>());
		
		while(sc.hasNextLine()) {
//Luam fiecare cuvant de pe fiecare linie		
			String line = sc.nextLine();
			String[] words = line.split(" ");
			
			for(String tara : Gestiune.getInstance().tari) {
//Adaugam fieacre termen in campul corespunzator:				
				double tempDouble = Double.parseDouble(words[Gestiune.getInstance().tari.indexOf(tara) + 1]);
				Gestiune.getInstance().taxe.get(tara).put(words[0], tempDouble);
			
			}
		}
		
		sc.close();
	}
	
	public void getFacturi() throws FileNotFoundException {
		
//Metoda care parseaza fisierul de facturi si obtine vectorul de magazine:		
		Scanner sc = new Scanner(new File("src/facturi.txt"));
		String line = sc.nextLine();
		
		while(sc.hasNextLine()) {
			String[] words = line.split(":");
			String numeMagazin = words[2];
			String tipMagazin = words[1];
			MagazinType tipMagazinType = null;
			Vector<Factura> tempFacturi = new Vector<>();
		//Generam folosind Factory Pattern obiectul pe care il cautam:	
			switch(tipMagazin) {
			case "MiniMarket":
				tipMagazinType = MagazinType.MiniMarket;
				break;
			case "MediumMarket":
				tipMagazinType = MagazinType.MediumMarket;
				break;
			case "HyperMarket":
				tipMagazinType = MagazinType.HyperMarket;
				break;
			default:
				break;
			}
			
			line = sc.nextLine();
		//Aici obtinem un vector de facturi si il vom adauga magazinului generat anterior:	
			while( sc.hasNextLine() && (line = sc.nextLine()).startsWith("Magazin") == false) {
				
				String tempNumeFactura = line;
				line = sc.nextLine();
				Vector<ProdusComandat> tempProduseComandate = new Vector<>();
				
				while( sc.hasNextLine() && (line = sc.nextLine()).equals("") == false) {
					
					words = line.split(" " );
					String tempNumeProdus = words[0];
					String tempTaraOrigine = words[1];
					
					int tempCantitate = Integer.parseInt(words[2]);
					String tempCategorie= "";
					double tempPret = 0;
					
					for(Produs produs : Gestiune.getInstance().produse)
						if(produs.getDenumire().equals(tempNumeProdus) && produs.getTaraOrigine().equals(tempTaraOrigine)) {
							tempCategorie = produs.getCategorie();
							tempPret = produs.getPret();
							break;
						}
					
					double tempTaxa = Gestiune.getInstance().taxe.get(tempTaraOrigine).get(tempCategorie);
					Produs tempProdus = new Produs(tempNumeProdus, tempCategorie, tempTaraOrigine, tempPret);
					ProdusComandat tempProdusComandat = new ProdusComandat(tempProdus, tempTaxa, tempCantitate);
					tempProduseComandate.add(tempProdusComandat);
				}
				
				tempFacturi.add(new Factura(tempNumeFactura, tempProduseComandate));
			}
			Gestiune.getInstance().magazine.add(MagazinFactory.buildMagazin(tipMagazinType, numeMagazin, tempFacturi));
		}
		sc.close();
	}
	
	public void printMagazin(PrintWriter writer, String tipMagazin)
							throws FileNotFoundException, IOException {
	//Metoda care printeaza un magazin in fisierul de output in functie de
	//tipul magazinului (folosita pentru a afisa magazinele pe tipuri)
		for(Magazin magazin : Gestiune.getInstance().magazine)
			if(magazin.getTip().equals(tipMagazin)) {
			
				writer.println(magazin.getNume());
				writer.println();
				writer.println("Total " + magazin.getTotalFaraTaxe() + " " +
								magazin.getTotalCuTaxe() + " " + magazin.getTotalCuTaxeScutite());
				writer.println();
				writer.println("Tara");
				
				for(String tara : Gestiune.getInstance().taxe.keySet())
					if(magazin.getTotalTaraFaraTaxe(tara) == 0 && magazin.getTotalTaraCuTaxe(tara) == 0
							&& magazin.getTotalTaraCuTaxeScutite(tara) == 0)
						writer.println(tara + " 0");
					else
						writer.println(tara + " " + magazin.getTotalTaraFaraTaxe(tara) + " " + 
									magazin.getTotalTaraCuTaxe(tara) + " " + magazin.getTotalTaraCuTaxeScutite(tara));
				writer.println();
				
				for(Factura factura : magazin.getFacturi()) {
				
					writer.println(factura.getDenumire());
					writer.println();
					writer.println("Total " + factura.getTotalFaraTaxe() + " " + factura.getTotalCuTaxe());
					writer.println();
					writer.println("Tara");
					
					for(String tara : Gestiune.getInstance().taxe.keySet())
						if(factura.getTotalTaraFaraTaxe(tara) == 0 && factura.getTotalTaraCuTaxe(tara) == 0)
							writer.println(tara + " 0");
						else
							writer.println(tara + " " + factura.getTotalTaraFaraTaxe(tara) +
											" " + factura.getTotalTaraCuTaxe(tara));
					writer.println();
				}
			}
	}
	
	public void getOutput() throws FileNotFoundException, IOException {
	//Aceasta functie obtine output-ul in fisierul tinta	
		PrintWriter writer = new PrintWriter("src/output.txt", "UTF-8");
		
		writer.println("MiniMarket");
		Gestiune.getInstance().printMagazin(writer, "MiniMarket");
		writer.println("MediumMarket");
		Gestiune.getInstance().printMagazin(writer, "MediumMarket");
		writer.println("HyperMarket");
		Gestiune.getInstance().printMagazin(writer, "HyperMarket");
		
		writer.close();
	}
	
	//Metoda care returneaza pretul unui produs cautat dupa tara si nume:
	public double pretProdusDupaTara(String produsCautat, String tara) {
		for(Produs produs : Gestiune.getInstance().produse)
			if(produs.getDenumire().equals(produsCautat) && produs.getTaraOrigine().equals(tara))
				return produs.getPret();
		return 0;
	}
	
	//Metoda care returneaza categoria unui produs cautat dupa nume:
	public String categorieProdus(String produsCautat) {
		for(Produs produs : Gestiune.getInstance().produse)
			if(produs.getDenumire().equals(produsCautat))
				return produs.getCategorie();
		return null;
	}
	
	//Metoda care afiseaza produsele in fisierul produse.txt
	//Este folosita de fiecare data cand modificam lista de produse:
	public void printProduse() throws FileNotFoundException, IOException {
		PrintWriter writer = new PrintWriter("src/produse.txt", "UTF-8");
		
		String line = "Produs Categorie";
		
		for(String tara : Gestiune.getInstance().tari)
			line = line + " " + tara;
		writer.println(line);

		Vector<String> produseUnice = new Vector<>();
		
		for(Produs produs : Gestiune.getInstance().produse)
			if(produseUnice.contains(produs.getDenumire()) == false)
				produseUnice.add(produs.getDenumire());
		
		for(String produsUnic : produseUnice) {
			line = produsUnic + " " + Gestiune.getInstance().categorieProdus(produsUnic);
			for(String tara : Gestiune.getInstance().tari)
				line = line + " " + Gestiune.getInstance().pretProdusDupaTara(produsUnic, tara);
			writer.println(line);
		}
		
		writer.close();
	}
	
	//Metoda specifica pentru citirea produselor:
	public void citesteProduse() throws FileNotFoundException, IOException {
		Gestiune.getInstance().getProduse();
	}
	//Metoda specifica pentru citirea taxelor:	
	public void citesteTaxe() throws FileNotFoundException, IOException {
		Gestiune.getInstance().getTaxe();
	}
	//Metoda specifica pentru citirea facturilor (si sortarea lor corespunzator):
	public void citesteFacturi() throws FileNotFoundException, IOException {
		
		Gestiune.getInstance().getFacturi();
		Collections.sort(Gestiune.getInstance().magazine, new MagazinComparator());
		for(Magazin magazin : Gestiune.getInstance().magazine)
			Collections.sort(magazin.getFacturi(), new FacturaComparator());
	}
	
	public boolean contineProdus(String nume, String categorie, String tara, double pret) {
		for(Produs produs : Gestiune.getInstance().produse)
			if(produs.getDenumire().toLowerCase().equals(nume.toLowerCase()) 
					&& produs.getCategorie().equals(categorie) 
					&& produs.getTaraOrigine().equals(tara) &&
					produs.getPret() == pret)
				return true;
		return false;
	}
	
	public String magazinVanzariMaxime() {
		int index = 0;
		double vanzari = 0;
		for(Magazin magazin : Gestiune.getInstance().magazine)
			if(magazin.getTotalCuTaxe() > vanzari) {
				vanzari = magazin.getTotalCuTaxe();
				index = Gestiune.getInstance().magazine.indexOf(magazin);
			}
		Magazin tempMagazin = Gestiune.getInstance().magazine.get(index);
		
		return (new String(tempMagazin.getNume() + " " + tempMagazin.getTotalFaraTaxe() + " " 
							+ tempMagazin.getTotalCuTaxe() + " " + 
								tempMagazin.getTotalCuTaxeScutite()));
		
	}
	
	public String magazinVanzariMaximeTara(String tara) {
		int index = 0;
		double vanzariMaxime = 0;
		for(Magazin magazin : Gestiune.getInstance().magazine) {
			double vanzari = 0;
			for(Factura factura : magazin.getFacturi())
				vanzari = vanzari + factura.getTotalTaraCuTaxe(tara);
			if(vanzari > vanzariMaxime) {
				vanzariMaxime = vanzari;
				index = Gestiune.getInstance().magazine.indexOf(magazin);
			}
		}
		Magazin tempMagazin = Gestiune.getInstance().magazine.get(index);

		return (new String((tempMagazin.getNume() + " " + tempMagazin.getTotalFaraTaxe() + " " 
							+ tempMagazin.getTotalCuTaxe() + " " + 
							tempMagazin.getTotalCuTaxeScutite())));
	}
	
	public String magazinVanzariMaximeCategorie(String categorie) {
		int index = 0;
		double vanzariMaxime = 0;
		double vanzari = 0;
		for(Magazin magazin : Gestiune.getInstance().magazine) {
			vanzari = 0;
			for(Factura factura : magazin.getFacturi()) {
				for(ProdusComandat produsComandat : factura.getProduseComandate())
					if(produsComandat.getProdus().getCategorie().equals(categorie))
						vanzari = vanzari + produsComandat.getCantitate() * 
									produsComandat.getProdus().getPret() * produsComandat.getTaxa();					
			}
			if(vanzari > vanzariMaxime) {
				vanzariMaxime = vanzari;
				index = Gestiune.getInstance().magazine.indexOf(magazin);
			}
		}
		Magazin tempMagazin = Gestiune.getInstance().magazine.get(index);
		
		return (new String(tempMagazin.getNume() + " " + tempMagazin.getTotalFaraTaxe() + 
							" " + tempMagazin.getTotalCuTaxe() + " " 
								+ tempMagazin.getTotalCuTaxeScutite()));
		
	}
	
	public String facturaVanzariMaxime() {
		int indexMagazin = 0, indexFactura = 0;
		double vanzari = 0;
		for(Magazin magazin : Gestiune.getInstance().magazine)
			for(Factura factura : magazin.getFacturi())
				if(factura.getTotalFaraTaxe() > vanzari) {
					vanzari = factura.getTotalFaraTaxe();
					indexMagazin = Gestiune.getInstance().magazine.indexOf(magazin);
					indexFactura = magazin.getFacturi().indexOf(factura);
				}
		Factura tempFactura = Gestiune.getInstance().magazine.elementAt(indexMagazin).getFacturi().elementAt(indexFactura);
		
		return (new String(tempFactura.getDenumire() + " " + tempFactura.getTotalFaraTaxe() +
								" " + tempFactura.getTotalCuTaxe()));
		
	}
}
