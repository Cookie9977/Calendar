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
	private String[] labels = { "L�gg til en ny event", "L�gg till v�nner", "visa v�nner", "F�rfr�gningar" };

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
		case "L�gg til en ny event":
			EventLogic logic = new EventLogic();
			EventPane eventPane = new EventPane();
			int test = JOptionPane.showConfirmDialog(null, eventPane, "test", JOptionPane.OK_CANCEL_OPTION);
			if (test == JOptionPane.OK_OPTION) {
				logic.checkEvent(eventPane);
			}
			break;
		case "L�gg till v�nner":
			AddFriend addFriend = new AddFriend();
			break;
		case "visa v�nner":
			FriendPane friendPane = new FriendPane();
			JOptionPane.showMessageDialog(null, friendPane);
			break;
		case "F�rfr�gningar":
			RequestPanel requestPanel = new RequestPanel();
			break;

		}

	}

}
