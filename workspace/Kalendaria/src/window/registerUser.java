package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.PasswordView;

import main.Main;
public class registerUser extends JPanel implements ActionListener{
private JTextField[] textBox;
private JPasswordField password;
private JLabel[] headingLabel;

	private static final long serialVersionUID = -2354560402587167649L;

	public registerUser(){
		setBackground(new Color(123,123,123));
		setSize(new Dimension(100,100));
		setVisible(true);
		Font inputFont = new Font("SansSerif",Font.BOLD,20);

		headingLabel = new JLabel[3];
		String[] heading;
		heading  = new String[] { "Användarnamn","Email","Lösenord"};
		
		for (int i = 0; i < headingLabel.length; i++){
			headingLabel[i] = new JLabel();
			headingLabel[i].setText(heading[i]);
			add(headingLabel[i]);
		}
		
		
		textBox = new JTextField[2];
		password = new JPasswordField(16);
		for (int i = 0; i < textBox.length; i++) {	
		   textBox[i] = new JTextField(i);
		   textBox[i].setColumns(8);
		   add(textBox[i]);
		}

		password.setColumns(8);
		add(password);
		
		JButton submitButton = new JButton();
		submitButton.setText("Registrera");
		submitButton.addActionListener(this);
		add(submitButton);
		
	}
	
	public void actionPerformed(ActionEvent e){
		String username = textBox[0].getText();
		String email = textBox[1].getText();
		char[] pass = password.getPassword();
		 
		try{
		
			String SQL = "select * from user where email ="+email+" OR username = "+username+"";
			Object[][] data = Main.db.getData(SQL);
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					System.out.println(data[i][j]);
				}
			}
			if(!data[0][0].equals("")){
				JOptionPane.showMessageDialog(null, "Already Registered!");
		}else{
			String SQLI="insert into user(username,email,password) values('"+username+"','"+email+"','"+new String(pass)+"')";
			Main.db.execute(SQLI);
			
		}
	}catch(Exception f){
		System.out.println(f);
	}
		 
		 
		//
//		System.out.print(textBox[0].getText());
//		System.out.print(textBox[1].getText());
//		System.out.print(textBox[2].getText());
		//db.execute(SQL);
	}
}
