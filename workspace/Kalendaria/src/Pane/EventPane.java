package Pane;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

import friend.Friend;
import friend.FriendList;
import main.Main;

public class EventPane extends JPanel implements ActionListener {
	private static final long serialVersionUID = -492939221067396792L;
	private JLabel[] labels;
	private JTextField[] textFields;
	private JTextArea description;
	private String[] labelText = { "Titel", "Plats", "Start", "Stopp", "Kategori", "Beskrivning", "Start datum",
			"Slut datum" };
	private CustomComboBox date_e, date_s, month_e, month_s, category;
	private JFormattedTextField time_s, time_e;
	private ArrayList<String> temp;
	private ArrayList<Friend> friends;
	private DefaultComboBoxModel<String> model;
	private JButton friendButton;
	private FriendPane pane;
	private Friend friend_temp;
	private JPanel friend_display;
	private JLabel friend_name;

	public EventPane() {
		// declaring
		labels = new JLabel[labelText.length];
		textFields = new JTextField[2];
		description = new JTextArea();

		friend_display = new JPanel();
		friend_display.setBorder(BorderFactory.createTitledBorder("Inbjudna vänner"));
		friend_display.setPreferredSize(new Dimension(200, 200));

		friendButton = new JButton("Lägg till vän");
		friendButton.addActionListener(this);

		date_e = new CustomComboBox();
		date_e.setModel(addDate(getMonths()));
		date_e.setId(1);
		date_e.addActionListener(this);

		date_s = new CustomComboBox();
		date_s.setModel(addDate(getMonths()));
		date_s.setId(2);
		date_s.addActionListener(this);

		month_e = new CustomComboBox();
		month_e.setModel(addDate(getDates("")));

		month_s = new CustomComboBox();
		month_s.setModel(addDate(getDates("")));

		category = new CustomComboBox();
		category.setModel(addDate(getCategories()));

		time_s = new JFormattedTextField();
		time_s.setFormatterFactory(new DefaultFormatterFactory(new DateFormatter(new SimpleDateFormat("HH':'mm"))));
		time_s.setValue(Calendar.getInstance().getTime());

		time_e = new JFormattedTextField();
		time_e.setFormatterFactory(new DefaultFormatterFactory(new DateFormatter(new SimpleDateFormat("HH':'mm"))));
		time_e.setValue(Calendar.getInstance().getTime());
		// set labels
		for (int i = 0; i < labelText.length; i++) {
			labels[i] = new JLabel(labelText[i]);
		}
		for (int i = 0; i < textFields.length; i++) {
			textFields[i] = new JTextField();
			textFields[i].setPreferredSize(new Dimension(175, 20));
			labels[i].setLabelFor(textFields[i]);
			add(labels[i]);
			add(textFields[i]);

		}
		// layout
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gridBag);

		// description setup
		description.setPreferredSize(new Dimension(175, 100));
		description.setBorder(BorderFactory.createEtchedBorder());
		labels[5].setLabelFor(description);

		add(friend_display);
		add(labels[5]);
		add(description);
		add(date_e);
		add(date_s);
		add(month_e);
		add(month_s);
		add(category);
		add(time_e);
		add(time_s);
		add(friendButton);

	}

	public ArrayList<String> getMonths() {
		DateFormatSymbols dfc = new DateFormatSymbols();
		temp = new ArrayList<String>();
		String[] months = dfc.getMonths();
		for (int i = 0; i < months.length - 1; i++) {
			temp.add(months[i]);
		}
		return temp;
	}

	public ArrayList<String> getDates(String month) {
		DateFormatSymbols dfc = new DateFormatSymbols();
		temp = new ArrayList<String>();
		int monthValue = 0;
		String[] months = dfc.getMonths();
		for (int i = 0; i < months.length - 1; i++) {
			if (months[i].equals(month)) {
				monthValue = i;
				break;
			}
		}
		Calendar cal = new GregorianCalendar();
		cal.set(cal.get(Calendar.YEAR), monthValue, cal.get(Calendar.DAY_OF_MONTH));
		int nrDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 0; i < nrDays; i++) {
			temp.add(String.valueOf(i + 1));
		}
		return temp;
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

	public DefaultComboBoxModel<String> addDate(ArrayList<String> dates) {
		model = new DefaultComboBoxModel<String>();
		for (int i = 0; i < dates.size(); i++) {
			model.addElement(dates.get(i));
		}
		return model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("comboBoxChanged")) {
			CustomComboBox temp = (CustomComboBox) e.getSource();
			if (temp.getId() == 1) {
				month_e.setModel(addDate(getDates((String) temp.getSelectedItem())));
			} else {
				month_s.setModel(addDate(getDates((String) temp.getSelectedItem())));
			}
		} else {
			pane = new FriendPane();
			int result = JOptionPane.showConfirmDialog(null, pane, "Lägg till en vän", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				friend_temp = new Friend(pane.selected);
				friends = new ArrayList<Friend>();
				friends.add(friend_temp);

				friend_name = new JLabel(friend_temp.toString());
				System.out.println(friend_temp.toString());
				friend_display.add(friend_name);
				friend_display.validate();
				friend_display.repaint();
				//pack();
			}
		}
	}

}
