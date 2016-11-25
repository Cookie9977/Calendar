package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class registerUser extends JPanel implements ActionListener{
private JTextField[] textBox;

	private static final long serialVersionUID = -2354560402587167649L;

	public registerUser(){
		setBackground(new Color(123,123,123));
		setSize(new Dimension(100,100));
		setVisible(true);
		Font inputFont = new Font("SansSerif",Font.BOLD,20);

		String typeInput[];
		typeInput = new String[] { "Användarnamn", "Email","Lösenord"};
		
		textBox = new JTextField[2];
		JPasswordField password = new JPasswordField();
		for (int i = 0; i < textBox.length; i++) {
				
		   textBox[i] = new JTextField(i);
		   textBox[i].setText(typeInput[i]);
		   textBox[i].setColumns(8);
		   add(textBox[i]);
		}
		
		password.setText(typeInput[2]);
		password.setColumns(8);
		add(password);
		
		JButton submitButton = new JButton();
		submitButton.setText("Fuckoff");
		submitButton.addActionListener(this);
		add(submitButton);
		
	}
	
	public void actionPerformed(ActionEvent e){
		//String SQL="INSERT INTO (username,email,password) values('"+textBox[0].getText()+"','"+textBox[1].getText()+"','"+textBox[2].getText()+"');";
		JOptionPane.showMessageDialog(null, "textBox[0].getText()"+textBox[1].getText()+""+textBox[2].getText()+"");
//		
//		System.out.print(textBox[0].getText());
//		System.out.print(textBox[1].getText());
//		System.out.print(textBox[2].getText());
		//db.execute(SQL);
	}
}
