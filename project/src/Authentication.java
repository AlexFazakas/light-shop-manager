import java.awt.Button;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Authentication extends JFrame{
//Aceasta clasa reprezinta o fereastra care se ocupa de login. Foloseste 2 campuri
//si compara input-ul din ele cu niste valori hardcodate. Daca logarea reuseste,
//putem trece mai departe la meniu. Altfel, se afiseaza un mesaj sugestiv si se
//curata campurile.

	public JTextField username;
	public JPasswordField password;
	public JLabel usernameLabel, passwordLabel;
	public JButton login;
	public JPanel authenticationPanel;
	
	public Authentication() {
		
		this.setSize(200, 180);
	
		Toolkit tk = Toolkit.getDefaultToolkit();
	
		Dimension dimensiuneEcran = tk.getScreenSize();
	
		int frameXPos = (dimensiuneEcran.width / 2) - (this.getWidth() / 2);
		int frameYPos = (dimensiuneEcran.height / 2) - (this.getHeight() / 2);
	
		this.setLocation(frameXPos, frameYPos);
	
		this.setResizable(false);
		
		authenticationPanel = new JPanel();
		
		usernameLabel = new JLabel("Username");
		usernameLabel.setText("Username:");
		
		authenticationPanel.add(usernameLabel);
		
		username = new JTextField("", 12);
		
		authenticationPanel.add(username);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setText("Password:");
	
		authenticationPanel.add(passwordLabel);

		password = new JPasswordField("", 12);
		
		authenticationPanel.add(password);
		
		login = new JButton("Login");
		login.setText("Login");
		login.addActionListener(new LoginListener());
		
		authenticationPanel.add(login);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(authenticationPanel);
		authenticationPanel.setVisible(true);
		this.setVisible(true);
	}
	
	public class LoginListener implements ActionListener {
//ActionListener care verifica logarea
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == login)
				try {
				//Daca username-ul si parola se potrivesc, accesam meniul si ascundem
				//fereastra de login:
					if(username.getText().equals("student") && (password.getText()).equals("student")) {
						Menu m = new Menu();
						Authentication.this.setVisible(false);
					}
					
					else {
				//Alfel, curatam campurile si afisam un mesaj corespunzator:
						username.setText("");
						password.setText("");
						String message = "Contul si parola introduse sunt gresite!";
						JOptionPane.showMessageDialog(Authentication.this, message, "Logare esuata", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			
				catch (Exception exception) {
					exception.printStackTrace();
				}
		}
		
	}

	public static void main(String[] args) {
		new Authentication();
	}
}
