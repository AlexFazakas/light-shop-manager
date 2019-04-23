import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Menu extends JFrame {
	
	public JPanel panelMenu = new JPanel(null);
	public JButton butonAdministratie = new JButton("Administratie");
	public JButton butonLoadData = new JButton("Load Data");
	public JButton butonStatistici = new JButton("Statistici");
	
	public Menu() {
//Aici avem meniul principal al aplicatiei. Are 3 optiuni, dupa cum se poate vedea.
		this.setSize(300, 500);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		Dimension dimensiuneEcran = tk.getScreenSize();
//Plasam fereastra in centrul ecranului.		
		int frameXPos = (dimensiuneEcran.width / 2) - (this.getWidth() / 2);
		int frameYPos = (dimensiuneEcran.height / 2) - (this.getHeight() / 2);
		
		this.setLocation(frameXPos, frameYPos);
		
		this.setResizable(false);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Main menu");
//Butonul de administratie cu ActionListener-ul corespunzator		
		butonAdministratie.setText("Administratie");
		butonAdministratie.addActionListener(new AdministratieListener());
		butonAdministratie.setContentAreaFilled(false);
		butonAdministratie.setBounds(100, 50, 120, 50);
		panelMenu.add(butonAdministratie);
		
//Butonul de incarcare a fisierelor cu ActionListener-ul corespunzator		
		butonLoadData.setText("Load data");
		butonLoadData.addActionListener(new LoadDataListener());
		butonLoadData.setContentAreaFilled(false);
		butonLoadData.setBounds(100, 200, 120, 50);
		panelMenu.add(butonLoadData);
		
//Butonul catre meniul cu statistici cu ActionListener-ul corespunzator		
		butonStatistici.setText("Statistici");
		butonStatistici.setContentAreaFilled(false);
		butonStatistici.addActionListener(new StatisticiListener());
		butonStatistici.setBounds(100, 350, 120, 50);
		butonStatistici.setEnabled(false);
		panelMenu.add(butonStatistici);
		
		this.add(panelMenu);
		
		this.setVisible(true);
	}
//ActionListener pentru butonul LoadData	
	private class LoadDataListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == butonLoadData)
				try {
//La apasarea butonului, incarcam continuturile fisierelor in clasa de Gestiune					
					Gestiune.getInstance().citesteProduse();
					Gestiune.getInstance().citesteTaxe();
					Gestiune.getInstance().citesteFacturi();
					Gestiune.getInstance().getOutput();
//Dam disable la buton dupa ce il folosim:
					butonLoadData.setEnabled(false);
//Afisam un mesaj informativ sugestiv:
					String message = "Fisierele au fost incarcate cu succes!";
					butonStatistici.setEnabled(true);
					JOptionPane.showMessageDialog(Menu.this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
				}
//Daca apar erori la deschiderea fisierelor, afisam un mesaj de eroare:
				catch (Exception exception) {
					String message = "Au aparut erori la deschiderea fisierelor!";
					JOptionPane.showMessageDialog(Menu.this, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
		}
	}
	
	private class AdministratieListener implements ActionListener {
//ActionListener pentru butonul de Administratie:
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == butonAdministratie)
				try {
//La apasarea sa, se va deschide fereastra corespunatoare:
					Administratie adm = new Administratie();
				}
			
				catch (Exception exception) {
					exception.printStackTrace();
				}
		}
		
	}
//Similar ca mai sus, acest listener deschide meniul de statistici.	
	private class StatisticiListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == butonStatistici)
				try {
					Statistici stats = new Statistici();
				}
			
				catch (Exception exception) {
					exception.printStackTrace();
				}
		}
		
	}
	
}

