package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
public class registerUser extends JPanel{
	private textFields usernameInput,emailInput,passwordInput;

	private static final long serialVersionUID = -2354560402587167649L;

	public registerUser(){
		setBackground(new Color(123,123,123));
		setSize(new Dimension(100,100));
		Font inputFont = new Font("SansSerif",Font.BOLD,20);
		
		usernameInput = new textFields(inputFont);
		emailInput = new textFields(inputFont);
		passwordInput = new textFields(inputFont);

		passwordInput.setFont(new Font("Serif",Font.ITALIC,7));
		
		JButton submitButton = new JButton();
		submitButton.setText("Fuckoff");		
		
		add(usernameInput);
		add(emailInput);
		add(passwordInput);
		add(submitButton);
		
		

		
		
		
		
	

	}
}
