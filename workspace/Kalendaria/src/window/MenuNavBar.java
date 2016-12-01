package window;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuNavBar extends JPanel implements ActionListener {
	private Window window;
	private windowModifications windowmodifications;
	private JFrame frame;
	private int type;
	private String[] buttonText = { "M�nad", "Vecka", "Dag" };

	private static final long serialVersionUID = -6222480473508548550L;

	public MenuNavBar(JFrame frame, int type, Window window) {
		this.frame = frame;
		this.type = type;
		this.window = window;
		JButton button = new JButton();
		windowmodifications = new windowModifications(window);

		switch (type) {
		case 0: // M�nad
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
	/*
	 * Efter remove och revalidate s� g�r pack(); TODO: strukturera om kod till
	 * en visa vy, visa vy ska finnas f�r varje enskild klass f�r att m�la upp
	 * deras grej men sen ocks� f�r huvudf�nstret d�r varje panel kommer att
	 * visas in.
	 */

	@Override
	public void actionPerformed(ActionEvent AE) {
		String buttonName = ((JButton) AE.getSource()).getText();
		switch (buttonName) {
		case "M�nad":
			windowmodifications.showMonthView();
			break;
		case "Vecka":
			windowmodifications.showWeekView();
			break;
		case "Dag":
			windowmodifications.showDayView();
			break;
		}

	}

}
