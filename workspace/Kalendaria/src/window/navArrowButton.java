package window;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class navArrowButton extends JPanel implements ActionListener{
	private String[] buttonText = {"",""};

	private static final long serialVersionUID = 5363273381463559887L;
	
	public navArrowButton(){
		JButton leftButton = new JButton();
		try{
			Image leftArrowIco = ImageIO.read(getClass().getResource("/resources/leftarrow.png"));
			Image sizedLeftArrow = leftArrowIco.getScaledInstance((int)leftButton.getPreferredSize().getWidth() ,(int)leftButton.getPreferredSize().getHeight(), Image.SCALE_SMOOTH);
			leftButton.setIcon(new ImageIcon(sizedLeftArrow));		
		}
		catch(Exception ex){
			System.out.println(ex);
		}
		leftButton.setText(buttonText[0]);
		leftButton.addActionListener(this);
		add(leftButton);
		
		JButton rightButton = new JButton();
//		System.out.println((int)rightButton.getPreferredSize().getWidth());
//		System.out.println((int)rightButton.getPreferredSize().getHeight());
		try{
			Image rightArrowIco = ImageIO.read(getClass().getResource("/resources/rightarrow.png"));
			Image sizedRightArrow = rightArrowIco.getScaledInstance((int)rightButton.getPreferredSize().getWidth() ,(int)rightButton.getPreferredSize().getHeight(), Image.SCALE_SMOOTH);
			rightButton.setIcon(new ImageIcon(sizedRightArrow));		
		}
		catch(Exception ex){
			System.out.println("fel "+ex);
		}
		rightButton.setText(buttonText[1]);
		rightButton.addActionListener(this);
		add(rightButton);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent AE) {
		// TODO Auto-generated method stub
		String buttonName = ((JButton) AE.getSource()).getText();
		JOptionPane.showMessageDialog(null,"du vill åt det "+buttonName+" hållet!");
	}
	
}
