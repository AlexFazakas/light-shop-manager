import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

public class Administratie extends JFrame {
	
	public JPanel panelAdministratie = new JPanel(null);
	public static DefaultListModel<Produs> defListaProduse = new DefaultListModel<>();
	public static JList<Produs> listaProduse = new JList<>();
	public JScrollPane listaProduseScroll = new JScrollPane();
	public JRadioButton sortareAlfabetica, sortareTara;
	public JButton sortButton, searchButton, addButton;
	public static JButton removeButton, editButton;
	
	public Administratie() {

		//Aceasta clasa se ocupa de meniul de administratie		

		this.setSize(500, 400);
		this.setTitle("Administratie");
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		Dimension dimensiuneEcran = tk.getScreenSize();
		
		int frameXPos = (dimensiuneEcran.width / 2) - (this.getWidth() / 2);
		int frameYPos = (dimensiuneEcran.height / 2) - (this.getHeight() / 2);
		
		this.setLocation(frameXPos, frameYPos);
		this.setResizable(false);
		
		//Nu inchidem toata aplicatia cand e inchis acest meniu:
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		//Lista produselor se bazeaza pe un JList care este creat peste un DefaultListModel
		if(defListaProduse.isEmpty() == true)
			for(Produs produs : Gestiune.getInstance().produse)
				defListaProduse.addElement(produs);
		
		listaProduse = new JList<>(defListaProduse);
		listaProduseScroll = new JScrollPane(listaProduse,
											JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
											JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		panelAdministratie.add(listaProduse);
		listaProduse.add(listaProduseScroll);
		listaProduse.setFixedCellHeight(20);
		listaProduse.setFixedCellWidth(500);
		listaProduse.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		panelAdministratie.add(listaProduseScroll);
		listaProduse.setBounds(0, 140, 500, 240);
		panelAdministratie.add(listaProduse);
		panelAdministratie.add(listaProduse);
		
		//Aceste butoane se ocupa de sortarea produselor:
		
		sortareAlfabetica = new JRadioButton("Alfabetica");
		sortareTara = new JRadioButton("Dupa tara");
		sortButton = new JButton("Sorteaza");
		sortButton.setText("Sorteaza");
		sortButton.addActionListener(new SortButtonListener());
		
		ButtonGroup sortButtons = new ButtonGroup();
		sortButtons.add(sortareAlfabetica);
		sortButtons.add(sortareTara);
		
		sortareAlfabetica.setSelected(true);
		
		JPanel sortPanel = new JPanel();
		Border sortPanelBorder = BorderFactory.createTitledBorder("Sortare");
		
		sortPanel.setBorder(sortPanelBorder);
		sortPanel.add(sortareAlfabetica);
		sortPanel.add(sortareTara);
		sortPanel.add(sortButton);
		sortPanel.setBounds(0, 0, 300, 100);
		
		//Acest buton ne va duce la fereastra de Search:
		
		searchButton = new JButton("Search");
		searchButton.setText("Search");
		searchButton.setBounds(400, 105, 80, 30);
		searchButton.addActionListener(new SearchButtonListener());
		
		//Acest buton ne va deschide optiunea de a adauga un produs:
		
		addButton = new JButton("Add");
		addButton.setText("Adauga produs");
		addButton.setBounds(5, 105, 120, 30);
		addButton.addActionListener(new AddButtonListener());
		
		//Acest buton (odata apasat) sterge elementul selectat din lista:
		
		removeButton = new JButton("Remove");
		removeButton.setText("Sterge produs");
		removeButton.setBounds(130, 105, 120, 30);
		removeButton.addActionListener(new RemoveButtonListener());
		
		//Acest buton permite editarea produsului selectat:
		
		editButton = new JButton("Edit");
		editButton.setText("Editeaza produs");
		editButton.setBounds(255, 105, 140, 30);
		editButton.addActionListener(new EditButtonListener());
		
		panelAdministratie.add(addButton);
		panelAdministratie.add(removeButton);
		panelAdministratie.add(searchButton);	
		panelAdministratie.add(editButton);
		panelAdministratie.add(sortPanel);
		
		this.add(panelAdministratie);
		this.setVisible(true);
	}
	
	private class SortButtonListener implements ActionListener {
//ActionListener pentru butonul de sortare:
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == sortButton)
				try {
			//Eliminam toate elementele din lista, sortam vectorul de produse corespunzator,
			//apoi punem elementele inapoi in lista in ordinea corespunzatoare:
					while(! defListaProduse.isEmpty())
						defListaProduse.remove(0);
					if(sortareAlfabetica.isSelected() == true)
						Collections.sort(Gestiune.getInstance().produse, new ProdusComparatorAlfabetic());
					else
						Collections.sort(Gestiune.getInstance().produse, new ProdusComparatorTara());
					for(Produs produs : Gestiune.getInstance().produse)
						defListaProduse.addElement(produs);
				}
				catch(Exception exception) {
					exception.printStackTrace();
				}
		}
		
	}
	
	private class SearchButtonListener implements ActionListener {
//ActionListener corespunzator butonului de search. Va deschide fereastra de cautare:
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == searchButton)
				try {
					new SearchButton();
				}
				catch(Exception exception) {
					exception.printStackTrace();
				}
		}
		
	}
	
	private class AddButtonListener implements ActionListener {
//ActionListener care va deschide meniul de adaugare al unui produs.
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == addButton)
				try {
					AddButton add = new AddButton();
				}
				
				catch (Exception exception) {
					exception.printStackTrace();
				}
		}	
	}
	
	private class RemoveButtonListener implements ActionListener {
//ActionListener care va sterge elementul curent selectat din lista.
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == removeButton) {
				try {
					if(listaProduse.isSelectionEmpty() == false) {
						//Stergem din lista elementul selectat, apoi updatam lista de produse interna
						//si fisierul de produse
						int produsSelectat = listaProduse.getSelectedIndex();
						Gestiune.getInstance().produse.remove((Produs) listaProduse.getSelectedValue());
						defListaProduse.removeElementAt(produsSelectat);
						Gestiune.getInstance().printProduse();
					}
					else {
						//Daca nu este nimic selectat, afisam un mesaj informativ.
						String message = "Nu ati selectat niciun produs spre a fi sters!";
						JOptionPane.showMessageDialog(Administratie.this, message, "Produs neselectat", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
				catch(Exception exception) {
					exception.printStackTrace();
				}
				//Daca am ramas fara produse, dezactivam butoanele de stergere si editare ale produselor:
				if(defListaProduse.size() == 0) {
					removeButton.setEnabled(false);
					editButton.setEnabled(false);
				}
			}
		}
	}
	
	private class EditButtonListener implements ActionListener {
//ActionListener pentru butonul efectiv de Edit.
//Va deschide o fereastra pentr editarea produsului curent selectat.
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == editButton)
				try {
					EditButton eb = new EditButton();
				}
			
				catch (Exception exception) {
					exception.printStackTrace();
				}
		}
		
	}
}
