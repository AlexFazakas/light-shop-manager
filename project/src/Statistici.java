import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Statistici extends JFrame{

//Clasa ce realizeaza fereastra cu statistici:
//Foloseste un TextArea continand datele si un panel ce il sustine:
	public JPanel statisticiPanel;
	public JTextArea informationToShow;
	
	public Statistici() {
		this.setSize(560, 240);
		this.setTitle("Statistici");
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		Dimension dimensiuneEcran = tk.getScreenSize();
		
		int frameXPos = (dimensiuneEcran.width / 2) - (this.getWidth() / 2);
		int frameYPos = (dimensiuneEcran.height / 2) - (this.getHeight() / 2);
	
		statisticiPanel = new JPanel();
		
		this.setLocation(frameXPos, frameYPos);
		this.setResizable(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		//In toShow am obtinut tot output-ul pe care trebuie sa il afisam: 
		String toShow = new String("Magazinul cu cele mai mari vanzari totale este: ");
		toShow = toShow + Gestiune.getInstance().magazinVanzariMaxime();
		toShow = toShow + '\n' + '\n';
		
		for(String tara : Gestiune.getInstance().tari)
			toShow = toShow + "Magazinul cu cele mai mari vanzari pe tara " + tara + " este: " + 
						Gestiune.getInstance().magazinVanzariMaximeTara(tara) + '\n';
		toShow = toShow + '\n';
		
		for(String categorie : Gestiune.getInstance().categorii)
			toShow = toShow + "Magazinul cu cele mai mari vanzari pe categoria " + categorie + " este: " +
						Gestiune.getInstance().magazinVanzariMaximeCategorie(categorie) + '\n';
		toShow = toShow + '\n' + '\n';
		
		toShow = toShow + "Factura cu cele mai mari vanzari este: " + Gestiune.getInstance().facturaVanzariMaxime() + '\n';
		
		//Apoi l-am atasat zonei de text:
		informationToShow = new JTextArea();
		informationToShow.setEditable(false);
		informationToShow.setText(toShow);
		statisticiPanel.add(informationToShow);
		
		statisticiPanel.add(informationToShow);
		
		this.add(statisticiPanel);
		this.setVisible(true);
	}
}
