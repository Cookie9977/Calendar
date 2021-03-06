package window;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class NavArrowButton extends JPanel implements ActionListener {
	// private String[] buttonText = { "", "" };
	private Window window;
	private int direction;
	private String view;
	private WindowModifications mod;

	private static final long serialVersionUID = 5363273381463559887L;

	public NavArrowButton(Window window, int direction) {
		this.window = window;
		this.direction = direction;
		JButton button = new JButton();
		switch (direction) {
		case 0: // left
			try {
				Image leftArrowIco = ImageIO.read(getClass().getResource("/resources/leftarrow.png"));
				Image sizedLeftArrow = leftArrowIco.getScaledInstance((int) button.getPreferredSize().getWidth(),
						(int) button.getPreferredSize().getHeight(), Image.SCALE_SMOOTH);
				button.setIcon(new ImageIcon(sizedLeftArrow));
			} catch (Exception ex) {
				System.out.println(ex);
			}
			// button.setText(buttonText[0]);
			break;
		case 1: // right
			try {
				Image rightArrowIco = ImageIO.read(getClass().getResource("/resources/rightarrow.png"));
				Image sizedRightArrow = rightArrowIco.getScaledInstance((int) button.getPreferredSize().getWidth(),
						(int) button.getPreferredSize().getHeight(), Image.SCALE_SMOOTH);
				button.setIcon(new ImageIcon(sizedRightArrow));
			} catch (Exception ex) {
				System.out.println("fel " + ex);
			}
			// button.setText(buttonText[1]);
			break;
		}
		button.addActionListener(this);
		add(button);

	}

	@Override
	public void actionPerformed(ActionEvent AE) {
		view = window.view.getText();
		switch (direction) {
		case 0: // left backwards
			switch (view) {
			case "Dag":
				mod = new WindowModifications(window.dayView, window);
				mod.previousDay();
				//System.out.println("detta �r dag");
				break;
			case "Vecka":
				mod = new WindowModifications(window.weekView, window);
				mod.previousWeek();
//				System.out.println("detta �r vecka");
				break;
			case "M�nad":
				mod = new WindowModifications(window.monthView, window);
				mod.previousMonth();
				break;

			}
			//System.out.println("Left");
			break;
		case 1: // right forward
			switch (view) {
			case "Dag":
				mod = new WindowModifications(window.dayView, window);
				mod.nextDay();
				// System.out.println("detta �r dag");
				break;
			case "Vecka":
				mod = new WindowModifications(window.weekView, window);
				mod.nextWeek();
				//System.out.println("detta �r vecka");
				break;
			case "M�nad":
				mod = new WindowModifications(window.monthView, window);
				mod.nextMonth();
				break;

			}
			// System.out.println("Right");
			break;
		}
	}

}
