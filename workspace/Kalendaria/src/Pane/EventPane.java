package pane;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import friend.Friend;
import main.Main;

public class EventPane extends JPanel implements ActionListener {
	private static final long serialVersionUID = -492939221067396792L;
	private JLabel[] labels;
	private String[] labelText = { "Titel", "Plats", "Start", "Stopp", "Kategori", "Beskrivning", "Start datum",
			"Slut datum" };
	private ArrayList<String> temp;
	private JButton friendButton, s_timeButton, e_timeButton;
	private FriendPane friendPane;
	private TimePane timePane;
	private Friend friend_temp;
	private JPanel friend_display, time_s_display, time_e_display;
	private JLabel friend_name, time_s_name, time_e_name;
	private DefaultComboBoxModel<String> model;

	public JFormattedTextField time_s, time_e;
	public JTextField[] textFields;
	public JTextArea description;
	public ArrayList<Friend> friends;
	public CustomComboBox category;
	public String time_start, time_end, temp_time;

	public EventPane() {
		// declaring
		labels = new JLabel[labelText.length];
		textFields = new JTextField[2];
		description = new JTextArea();

		friend_display = new JPanel();
		friend_display.setBorder(BorderFactory.createTitledBorder("Inbjudna vänner"));
		friend_display.setPreferredSize(new Dimension(200, 200));

		time_s_display = new JPanel();
		time_s_display.setBorder(BorderFactory.createTitledBorder("Start tid"));
		time_s_display.setPreferredSize(new Dimension(150, 50));

		time_e_display = new JPanel();
		time_e_display.setBorder(BorderFactory.createTitledBorder("Slut tid"));
		time_e_display.setPreferredSize(new Dimension(150, 50));

		friendButton = new JButton("Lägg till vän");
		friendButton.addActionListener(this);

		s_timeButton = new JButton("Start tid");
		s_timeButton.addActionListener(this);

		e_timeButton = new JButton("Slut tid");
		e_timeButton.addActionListener(this);

		category = new CustomComboBox();
		category.setModel(addThings(getCategories()));
		
		// layout
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gridBag);
		
		gbc.gridx = 0;
		gbc.gridy= 0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 0.5;
		
		System.out.println(4%2);

		// set labels
		for (int i = 0; i < labelText.length; i++) {
			labels[i] = new JLabel(labelText[i]);
		}
		for (int i = 0; i < textFields.length; i++) {
			gbc.gridx = 0;
			textFields[i] = new JTextField();
			textFields[i].setPreferredSize(new Dimension(175, 20));
			labels[i].setLabelFor(textFields[i]);
			add(labels[i],gbc);
			gbc.gridx = 1;
			add(textFields[i],gbc);
			gbc.gridy++;
		}

		// description setup
		description.setPreferredSize(new Dimension(175, 100));
		description.setBorder(BorderFactory.createEtchedBorder());
		labels[5].setLabelFor(description);

		gbc.gridx = 0;
		add(labels[5],gbc);
		gbc.gridx = 1;
		add(description,gbc);
		gbc.gridy++;
		gbc.gridx = 0;
		add(labels[4],gbc);
		gbc.gridx = 1;
		add(category,gbc);
		gbc.gridy++;
		gbc.gridx = 0;
		add(friendButton,gbc);
		gbc.gridx = 1;
		add(friend_display,gbc);
		gbc.gridy++;
		gbc.gridx = 0;
		add(s_timeButton,gbc);
		gbc.gridx = 1;
		add(time_s_display,gbc);
		gbc.gridy++;
		gbc.gridx = 0;
		add(e_timeButton,gbc);
		gbc.gridx = 1;
		add(time_e_display,gbc);
		

	}

	public ArrayList<String> getCategories() {
		String SQL = "SELECT name FROM category";
		Object[][] data = Main.db.getData(SQL);
		temp = new ArrayList<String>();
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				temp.add((String) data[i][j]);
			}
		}
		return temp;
	}

	public DefaultComboBoxModel<String> addThings(ArrayList<String> dates) {
		model = new DefaultComboBoxModel<String>();
		for (int i = 0; i < dates.size(); i++) {
			model.addElement(dates.get(i));
		}
		return model;
	}

	public String TimeFormat() {
		temp_time = (String) timePane.year.getSelectedItem();
		if (timePane.month.getSelectedIndex() + 1 < 10) {
			temp_time += "-0" + ((int) timePane.month.getSelectedIndex() + 1);
		} else {
			temp_time += "-" + ((int) timePane.month.getSelectedIndex() + 1);
		}
		if (timePane.day.getSelectedIndex() + 1  < 10) {
			temp_time += "-0" + timePane.day.getSelectedItem();
		} else {
			temp_time += "-" + timePane.day.getSelectedItem();
		}
		temp_time += " " + timePane.time.getText();
		return temp_time;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Lägg till vän") {
			friendPane = new FriendPane();
			int result = JOptionPane.showConfirmDialog(null, friendPane, "Lägg till en vän",
					JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				friend_temp = new Friend(friendPane.selected);
				friends = new ArrayList<Friend>();
				friends.add(friend_temp);

				friend_name = new JLabel(friend_temp.toString());

				friend_display.add(friend_name);
				friend_display.validate();
				friend_display.repaint();

			}
		} else if (e.getActionCommand() == "Start tid" || e.getActionCommand() == "Slut tid") {
			timePane = new TimePane();
			int result = JOptionPane.showConfirmDialog(null, timePane, "Lägg till en vän",
					JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				if (e.getActionCommand() == "Start tid") {
					time_start = TimeFormat();
					time_s_name = new JLabel(time_start);
					time_s_display.removeAll();
					time_s_display.add(time_s_name);
					time_s_display.validate();
					time_s_display.repaint();

				} else {
					time_end = TimeFormat();
					time_e_name = new JLabel(time_end);
					time_e_display.removeAll();
					time_e_display.add(time_e_name);
					time_e_display.validate();
					time_e_display.repaint();
				}
			}
		}
	}
}
