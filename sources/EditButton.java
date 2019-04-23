import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javafx.stage.WindowEvent;

public class EditButton extends JFrame {
	
	public JPanel addButtonPanel;
	public JComboBox<String> categoriiProduse, tariProduse;
	public JTextField numeProdus, pretProdus;
	public JLabel numeProdusLabel, pretProdusLabel;
	public JButton editeazaButton;
	
	public EditButton() {
		this.setSize(450, 120);
		this.setTitle("Editare produs");
		
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
		
		numeProdusLabel = new JLabel("Nume produs");
		numeProdusLabel.setText("Nume produs nou:");
		numeProdus = new JTextField("Nume produs nou..", 15);
		
		pretProdusLabel = new JLabel("Pret produs");
		pretProdusLabel.setText("Pret produs:");
		pretProdus = new JTextField("", 5);
		
		editeazaButton = new JButton("Edit");
		editeazaButton.setText("Editeaza");
		editeazaButton.addActionListener(new AdaugaProdus());
		
		addButtonPanel.add(numeProdusLabel);
		addButtonPanel.add(numeProdus);
		addButtonPanel.add(pretProdusLabel);
		addButtonPanel.add(pretProdus);
		addButtonPanel.add(categoriiProduse);
		addButtonPanel.add(tariProduse);
		addButtonPanel.add(editeazaButton);
		
		this.add(addButtonPanel);
		this.setVisible(true);
	}
	
	private class AdaugaProdus implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == editeazaButton)
				if(Administratie.listaProduse.isSelectionEmpty() == false)
					try {
						
						String nume = numeProdus.getText();
						String categorie = (String) categoriiProduse.getSelectedItem();
						String tara = (String) tariProduse.getSelectedItem();
						
						try {
							double pret = Double.parseDouble(pretProdus.getText());
							Produs produsNou = new Produs(nume, categorie, tara, pret);
							
							if(Gestiune.getInstance().contineProdus(nume, categorie, tara, pret) == false) {
							
								int indexCurent = Administratie.listaProduse.getSelectedIndex();
								
								Administratie.defListaProduse.remove(indexCurent);
								Administratie.defListaProduse.add(indexCurent, produsNou);
								
								Gestiune.getInstance().produse.remove(indexCurent);
								Gestiune.getInstance().produse.add(indexCurent, produsNou);
								Gestiune.getInstance().printProduse();
								
								EditButton.this.setVisible(false);
							}
						
							else {
								String message = "Produsul deja exista!";
								JOptionPane.showMessageDialog(EditButton.this, message, "Produs existent",
																JOptionPane.INFORMATION_MESSAGE);
							}
						}
						catch(NumberFormatException doubleException) {
							String message = "Pretul trebuie sa fie un numar real!";
							JOptionPane.showMessageDialog(EditButton.this, message, "Pret incorect", JOptionPane.INFORMATION_MESSAGE);
						}
					}
			
					catch (Exception exception) {
						exception.printStackTrace();
					}
				else {
					String message = "Nu ati selectat niciun produs spre a fi editat!";
					JOptionPane.showMessageDialog(EditButton.this, message, "Produs neselectat", JOptionPane.INFORMATION_MESSAGE);
				}
		}
		
	}
}