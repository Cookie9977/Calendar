package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.Storage;

public class RegisterUser extends JPanel implements ActionListener {
	private JTextField[] textBox;
	private JPanel[] holders;
	private JPasswordField password;
	private JLabel[] headingLabel;

	private static final long serialVersionUID = -2354560402587167649L;

	public RegisterUser() {
		setBackground(new Color(0, 0, 0, 0));
		setSize(new Dimension(100, 100));
		setVisible(true);
		setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, (new Color(245, 245, 245))));
		// layout
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gridBag);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		holders = new JPanel[3];
		for (int i = 0; i < holders.length; i++) {
			holders[i] = new JPanel();
			holders[i].setLayout(new GridLayout(1, 2));
			add(holders[i], gbc);
			gbc.gridy++;
		}

		headingLabel = new JLabel[3];
		String[] heading;
		heading = new String[] { "  Anv�ndarnamn:", "  E-mail:", "  L�senord:" };
		for (int i = 0; i < headingLabel.length; i++) {
			headingLabel[i] = new JLabel();
			headingLabel[i].setFont(new Font("SansSerif", Font.PLAIN, 15));
			headingLabel[i].setText(heading[i]);
			holders[i].add(headingLabel[i]);
		}
		textBox = new JTextField[2];
		password = new JPasswordField(16);
		for (int i = 0; i < textBox.length; i++) {
			textBox[i] = new JTextField(i);
			textBox[i].setColumns(8);
			holders[i].add(textBox[i]);
		}

		password.setColumns(8);
		holders[2].add(password);

		JButton submitButton = new JButton();
		submitButton.setText("Registrera");
		submitButton.addActionListener(this);
		gbc.gridy++;
		add(submitButton, gbc);

	}

	public void actionPerformed(ActionEvent e) {
		String username = textBox[0].getText();
		String email = textBox[1].getText();
		char[] pass = password.getPassword();

		try {

			String SQL = "select * from user where email ='" + email + "' OR username = '" + username + "'";
			String SQLI = "insert into user(username,email,password) values('" + username + "','" + email + "','"
					+ new String(pass) + "')";
			System.out.println(SQL);
			System.out.println(SQLI);
			Object[][] data = Storage.db.getData(SQL);

			if (!(data[0][0] == "")) {
				System.out.println(SQL);
				JOptionPane.showMessageDialog(null, "Already Registered!");

			} else {
				System.out.println(SQLI);
			}

		} catch (Exception f) {
			String SQLI = "insert into user(username,email,password) values('" + username + "','" + email + "','"
					+ new String(pass) + "')";
			Storage.db.execute(SQLI);
			// Fuling, m�ste fixa med typ 'isNull'
		}

	}

	//
	// System.out.print(textBox[0].getText());
	// System.out.print(textBox[1].getText());
	// System.out.print(textBox[2].getText());
	// db.execute(SQL);
}
