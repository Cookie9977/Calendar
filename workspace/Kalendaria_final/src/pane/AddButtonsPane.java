package pane;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AddButtonsPane extends JPanel implements ActionListener{
	private static final long serialVersionUID = 5973341942470877467L;
	private JButton[] buttons;
	private JPanel[] holder;
	private String[] labels = {"Lägg til en ny event", "Lägg till vänner", "visa vänner"};
	
	public AddButtonsPane() {
		int rows = labels.length;
		buttons = new JButton[rows];
		holder = new JPanel[rows];
		setLayout(new GridLayout(3, 1));
		
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
	}

}
