package window;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuNavBar extends JPanel implements ActionListener {
	private Window windowVal;
	private WindowModifications windowmodifications;
	@SuppressWarnings("unused")
	private int type;
	private String[] buttonText = { "M�nad", "Vecka", "Dag" };

	protected MonthView monthView;
	protected WeekView weekView;
	protected DayView dayView;

	private static final long serialVersionUID = -6222480473508548550L;

	public MenuNavBar(Window windowVal, int type) {
		this.windowVal = windowVal;
		this.type = type;
		JButton button = new JButton();

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
		button.setPreferredSize(new Dimension(200, 70));
		button.setFont(new Font("SansSerif", Font.PLAIN, 18));
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
		windowmodifications = windowVal.getModifications();
		String buttonName = ((JButton) AE.getSource()).getText();
		switch (buttonName) {
		case "M�nad":
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
