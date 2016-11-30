package window;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuNavBar extends JPanel implements ActionListener {
	private JFrame frame;
	private int type;
	private String[] buttonText = { "Månad", "Vecka", "Dag" };

	private static final long serialVersionUID = -6222480473508548550L;

	public MenuNavBar(JFrame frame, int type) {
		this.frame = frame;
		this.type = type;
		JButton button = new JButton();

		switch (type) {
		case 0: // Månad
			button.setText(buttonText[0]);
			break;
		case 1: // Vecka
			button.setText(buttonText[1]);
			break;
		case 2: // Dag
			button.setText(buttonText[2]);
			break;
		}
		button.setPreferredSize(new Dimension(200, 60));
		button.addActionListener(this);
		add(button);
	}

	@Override
	public void actionPerformed(ActionEvent AE) {
		String buttonName = ((JButton) AE.getSource()).getText();
		JOptionPane.showMessageDialog(null, "du vill hit: " + buttonName + " !");

	}

}
