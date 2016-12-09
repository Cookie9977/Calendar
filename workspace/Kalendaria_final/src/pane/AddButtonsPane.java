package pane;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logic.EventLogic;

public class AddButtonsPane extends JPanel implements ActionListener {
	private static final long serialVersionUID = 5973341942470877467L;
	private JButton[] buttons;
	private JPanel[] holder;
	private String[] labels = { "Lägg til en ny event", "Lägg till vänner", "visa vänner", "Förfrågningar" };

	public AddButtonsPane() {
		int rows = labels.length;
		buttons = new JButton[rows];
		holder = new JPanel[rows];
		setLayout(new GridLayout(4, 1));

		for (int i = 0; i < rows; i++) {
			holder[i] = new JPanel();
			buttons[i] = new JButton(labels[i]);
			buttons[i].addActionListener(this);
			buttons[i].setPreferredSize(new Dimension(150, 25));
			add(holder[i]);
			holder[i].add(buttons[i]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		String command = e.getActionCommand();
		switch (command) {
		case "Lägg til en ny event":
			EventLogic logic = new EventLogic();
			EventPane eventPane = new EventPane();
			int test = JOptionPane.showConfirmDialog(null, eventPane, "test", JOptionPane.OK_CANCEL_OPTION);
			if (test == JOptionPane.OK_OPTION) {
				logic.checkEvent(eventPane);
			}
			break;
		case "Lägg till vänner":
			AddFriend addFriend = new AddFriend();
			break;
		case "visa vänner":
			FriendPane friendPane = new FriendPane();
			JOptionPane.showMessageDialog(null, friendPane);
			break;
		case "Förfrågningar":
			RequestPanel requestPanel = new RequestPanel();
			break;

		}

	}

}
