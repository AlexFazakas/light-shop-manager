import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchButton extends JFrame {

//Clasa care se ocupa de fereastra de search
	
	public JPanel searchButtonPanel;
	public JLabel descriere;
	public JTextField numeProdus;
//Folosim o noua lista de produse pentru afisarea celor gasite:
	public DefaultListModel<Produs> defProduseGasite;
	public JList<Produs> produseGasite;
	public SearchButton() {
		
		this.setSize(450, 160);
		this.setTitle("Cautare produs");
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		Dimension dimensiuneEcran = tk.getScreenSize();
		
		int frameXPos = (dimensiuneEcran.width / 2) - (this.getWidth() / 2);
		int frameYPos = (dimensiuneEcran.height / 2) - (this.getHeight() / 2);
		
		searchButtonPanel = new JPanel();
		
		this.setLocation(frameXPos, frameYPos);
		this.setResizable(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		//Informatii legate de cautarea produsului:
		descriere = new JLabel("Descriere");
		descriere.setText("Introduceti numele produsului cautat:");
		searchButtonPanel.add(descriere);
		
		//String-ul dupa care cautam produse:
		numeProdus = new JTextField("Nume produs", 15);
		numeProdus.setText("Numele produsului cautat...");
		searchButtonPanel.add(numeProdus);
		numeProdus.addActionListener(new NumeProdusListener());
		
		//Declararea listei:
		defProduseGasite = new DefaultListModel<Produs>();
		produseGasite = new JList<Produs>(defProduseGasite);

		produseGasite.setVisible(true);
		searchButtonPanel.add(produseGasite);
		
		this.add(searchButtonPanel);
		this.setVisible(true);
	}
	
	public class NumeProdusListener implements ActionListener {

//Cand se apasa enter, afisam lista cu produse gasite:
		@Override
		public void actionPerformed(ActionEvent e) {
//Golim lista de produse gasite (daca nu e goala),
//pentru a putea da "refresh" intre cautari:
			while(! defProduseGasite.isEmpty())
				defProduseGasite.remove(0);
		//Adaugam la lista toate produsele care fac match:	
			for(Produs produs : Gestiune.getInstance().produse)
				if(produs.getDenumire().toLowerCase().contains(numeProdus.getText().toLowerCase()))
					defProduseGasite.addElement(produs);
		//Updatam lista daca am gasit ceva:
			if(defProduseGasite.isEmpty() == false) {
				produseGasite = new JList<Produs>(defProduseGasite);
			}
			
			else {
		//Daca nu am gasit nimic, afisam un mesaj informativ:
				String message = "Nu a fost gasit niciun produs cu acest nume!";
				JOptionPane.showMessageDialog(SearchButton.this, message, "Produs negasit", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}