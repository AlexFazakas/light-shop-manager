import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddButton extends JFrame {
//Aceasta clasa se ocupa de fereastra de adaugare a produselor	
	public JPanel addButtonPanel;
	public JComboBox<String> categoriiProduse, tariProduse;
	public JTextField numeProdus, pretProdus;
	public JLabel numeProdusLabel, pretProdusLabel;
	public JButton adaugaButton;
	
	public AddButton() {
		this.setSize(450, 120);
		this.setTitle("Adaugare produs");
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		Dimension dimensiuneEcran = tk.getScreenSize();
		
		int frameXPos = (dimensiuneEcran.width / 2) - (this.getWidth() / 2);
		int frameYPos = (dimensiuneEcran.height / 2) - (this.getHeight() / 2);
	
		addButtonPanel = new JPanel();
		
		this.setLocation(frameXPos, frameYPos);
		this.setResizable(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);

		categoriiProduse = new JComboBox<String>(Gestiune.getInstance().categorii);
		tariProduse = new JComboBox<String>(Gestiune.getInstance().tari);
		
		//Label si textfield pentru numele produsului de adaugat
		numeProdusLabel = new JLabel("Nume produs");
		numeProdusLabel.setText("Nume produs nou:");
		numeProdus = new JTextField("Nume produs nou..", 15);
		
		//Label si textfield pentru pretul produsului de adaugat
		pretProdusLabel = new JLabel("Pret produs");
		pretProdusLabel.setText("Pret produs:");
		pretProdus = new JTextField("", 5);
		
		//Acest buton va face efectiv adaugarea si se va ocupa de schimbarea
		//listei si a elementelor de gestiune(vectorul de produse si fisierul)
		adaugaButton = new JButton("Add");
		adaugaButton.setText("Adauga");
		adaugaButton.addActionListener(new AdaugaProdus());
		
		addButtonPanel.add(numeProdusLabel);
		addButtonPanel.add(numeProdus);
		addButtonPanel.add(pretProdusLabel);
		addButtonPanel.add(pretProdus);
		addButtonPanel.add(categoriiProduse);
		addButtonPanel.add(tariProduse);
		addButtonPanel.add(adaugaButton);
		
		this.add(addButtonPanel);
		this.setVisible(true);
	}
	
	private class AdaugaProdus implements ActionListener {
//Action listener care realizeaza actiunea de adaugare a unui produs
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == adaugaButton)
				try {
				//Luam toate informatiile necesare obtinerii unui produs
					String nume = numeProdus.getText();
					String categorie = (String) categoriiProduse.getSelectedItem();
					String tara = (String) tariProduse.getSelectedItem();
					try {
						//Daca input-ul in textfield-ul de pret este corespunzator:
						double pret = Double.parseDouble(pretProdus.getText());
						Produs produsNou = new Produs(nume, categorie, tara, pret);
						
						//Verificam daca produsul exista deja in lista:
						if(Gestiune.getInstance().contineProdus(nume, categorie, tara, pret) == false) {
							//Daca nu, il adaugam si updatam vectorul de produse si fisierul cu produse
							Administratie.defListaProduse.addElement(produsNou);
							
							Gestiune.getInstance().produse.add(produsNou);
							Gestiune.getInstance().printProduse();
							
							AddButton.this.setVisible(false);
							//Activam butoanele de remove si de edit pentru ca avem in lista
							//cel putin un element acum
							Administratie.removeButton.setEnabled(true);
							Administratie.editButton.setEnabled(true);
						}
						
						else {
							//Daca produsul exista deja, afisam un mesaj si nu adaugam nimic:
							String message = "Produsul deja exista!";
							JOptionPane.showMessageDialog(AddButton.this, message, "Produs existent", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					catch(NumberFormatException doubleException) {
						//Daca pretul nu are format-ul corespunzator, afisam un mesaj:
						String message = "Pretul trebuie sa fie un numar real!";
						JOptionPane.showMessageDialog(AddButton.this, message, "Pret incorect", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			
				catch (Exception exception) {
					exception.printStackTrace();
				}
		}
		
	}
}