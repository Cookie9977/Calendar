package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.Main;

public class loginUser extends JPanel implements ActionListener{
	private JTextField[] textBox;
	private JPasswordField password;
	private JLabel[] headingLabel;

	private static final long serialVersionUID = -2354560402587167649L;

	public loginUser(){
		setBackground(new Color(123,123,123));
		setSize(new Dimension(100,100));
		setVisible(true);
		setLayout(new GridLayout(5,1));
		Font inputFont = new Font("SansSerif",Font.BOLD,20);

		headingLabel = new JLabel[2];
		String[] heading;
		heading  = new String[] { "Email","Lösenord"};
		textBox = new JTextField[1];
		JPasswordField password = new JPasswordField();
		for (int i = 0; i < headingLabel.length; i++){
			headingLabel[i] = new JLabel();
			headingLabel[i].setText(heading[i]);
			add(headingLabel[i]);
		}
		
		for (int i = 0; i < textBox.length; i++) {
				
		   textBox[i] = new JTextField(i);
		   textBox[i].setFont(inputFont);
		   textBox[i].setColumns(8);
		   add(textBox[i]);
		}
		
		password.setColumns(8);
		add(password);
		
		JButton submitButton = new JButton();
		submitButton.setText("Logga in");
		submitButton.addActionListener(this);
		add(submitButton);
		
		
	}
	//String email = textBox[0].getText();
	//String password = textBox[1].getText();
	
	public void actionPerformed(ActionEvent e){
		String email = textBox[0].getText();
		char[] pass = password.getPassword();
		String SQL="SELECT * FROM user WHERE email ='"+email+"' AND password ='"+pass+"')";
		
		Main.db.execute(SQL);
		System.out.println(SQL);
		

}

	//public void actionPerformed(ActionEvent e){			
		//		displayUsers(email,password);
			//}
//		System.out.print(textBox[0].getText());
//		System.out.print(textBox[1].getText());
//		System.out.print(textBox[2].getText());
		
	
}
