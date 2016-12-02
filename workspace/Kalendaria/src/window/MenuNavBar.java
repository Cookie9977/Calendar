package window;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuNavBar extends JPanel implements ActionListener {
	private Window windowVal;
	private windowModifications windowmodifications;
	private String[] buttonText = { "Månad", "Vecka", "Dag" };
	protected monthView monthView;
	protected weekView weekView;
	protected dayView dayView;

	private static final long serialVersionUID = -6222480473508548550L;

	public MenuNavBar(Window windowVal, int type) {
		this.windowVal = windowVal;
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
	/*
	 * Efter remove och revalidate så gör pack(); TODO: strukturera om kod till
	 * en visa vy, visa vy ska finnas för varje enskild klass för att måla upp
	 * deras grej men sen också för huvudfönstret där varje panel kommer att
	 * visas in.
	 */

	@Override
	public void actionPerformed(ActionEvent AE) {
		windowmodifications = windowVal.getModifications();
		String buttonName = ((JButton) AE.getSource()).getText();
		switch (buttonName) {
		case "Månad":
			try {
				windowmodifications.showMonthView();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getStackTrace());
				JOptionPane.showMessageDialog(null, e);
			}
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
