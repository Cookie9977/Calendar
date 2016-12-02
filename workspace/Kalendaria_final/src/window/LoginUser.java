package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.Main;

public class LoginUser extends JPanel implements ActionListener {
	private JTextField[] textBox;
	private JPasswordField password;
	private JLabel[] headingLabel;
	private JPanel[] holders;
	private Window windowVal;
	private WindowModifications windowmodifications;

	private static final long serialVersionUID = -2354560402587167649L;

	public LoginUser(Window windowVal) {
		this.windowVal = windowVal;
		setBackground(new Color(123, 123, 123));
		setSize(new Dimension(100, 100));
		setVisible(true);
		// Font inputFont = new Font("SansSerif", Font.BOLD, 20);
		// layout
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gridBag);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		holders = new JPanel[2];
		for (int i = 0; i < holders.length; i++) {
			holders[i] = new JPanel();
			holders[i].setLayout(new GridLayout(1, 2));
			add(holders[i], gbc);
			gbc.gridy++;
		}

		headingLabel = new JLabel[2];
		String[] heading;
		heading = new String[] { "Email", "Lösenord" };
		textBox = new JTextField[1];
		password = new JPasswordField(16);
		for (int i = 0; i < headingLabel.length; i++) {
			headingLabel[i] = new JLabel();
			headingLabel[i].setText(heading[i]);
			holders[i].add(headingLabel[i]);
		}

		for (int i = 0; i < textBox.length; i++) {

			textBox[i] = new JTextField(i);
			// textBox[i].setFont(inputFont);
			textBox[i].setColumns(8);
			holders[i].add(textBox[i]);
		}

		password.setColumns(8);
		holders[1].add(password);

		JButton submitButton = new JButton();
		submitButton.setText("Logga in");
		submitButton.addActionListener(this);
		gbc.gridy++;
		add(submitButton, gbc);

	}
	// String email = textBox[0].getText();
	// String password = textBox[1].getText();

	public void actionPerformed(ActionEvent e) {
		String email = textBox[0].getText();
		char[] pass = password.getPassword();
		String ids = "";

		try {
			String SQL = "select * from user where email ='" + email + "' AND password ='" + new String(pass) + "'";
			// System.out.println(SQL);

			Object[][] data = Main.db.getData(SQL);

			// String SQLJ = "select id from user where email ='"+email+"' AND
			// password ='"+new String(pass)+"'";

			if (!(data[0][0] == "")) {
				// System.out.println(SQL);
				ids = (String) data[0][0];
				Main.id = Integer.parseInt(ids);
				System.out.println("Du är inloggad som " + email + " med ID: " + Main.id);
				// TODO försvinn
				windowmodifications = new WindowModifications(this, windowVal.registerUser, windowVal);
				// windowmodifications = windowVal.getModifications();
				try {
					windowmodifications.showLoggedinView();
				} catch (NullPointerException e2) {
					System.err.println(e2);
				}

			} else {
				JOptionPane.showMessageDialog(null, "Fel användarnamn eller lösenord");
				System.out.println(SQL);

			}

		} catch (Exception f) {
			System.out.println(f);
			JOptionPane.showMessageDialog(null, "Finns inget registrerat på den emailen");
			// Ledsen, fuling där också
		}

	}

	// public void actionPerformed(ActionEvent e){
	// displayUsers(email,password);
	// }
	// System.out.print(textBox[0].getText());
	// System.out.print(textBox[1].getText());
	// System.out.print(textBox[2].getText());

}
