import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoadDataListener implements ActionListener {

//ActionListener pentru butonul care incarca fisierele
	@Override
	public void actionPerformed(ActionEvent ae) {
		//Daca sunt incarcate cu succes, afisam urmatorul mesaj:
		try {
			Gestiune.getInstance().citesteProduse();
			Gestiune.getInstance().citesteTaxe();
			Gestiune.getInstance().citesteFacturi();
			JLabel successMessage = new JLabel("", 15);
			successMessage.setText("Datele au fost incarcate cu succes!");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
