package pane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

import logic.TimeLogic;

public class TimePane extends JPanel implements ActionListener {
	private static final long serialVersionUID = 6463497046997180551L;
	private DefaultComboBoxModel<String> model;

	public JFormattedTextField time;
	public CustomComboBox year, month, day;
	private TimeLogic logic;

	public TimePane() {
		logic = new TimeLogic();
		year = new CustomComboBox();
		month = new CustomComboBox();
		day = new CustomComboBox();
		time = new JFormattedTextField();

		month.setModel(addThings(logic.getMonths()));
		month.addActionListener(this);
		day.setModel(addThings(logic.getDays("")));
		year.setModel(addThings(logic.getYears()));
		time.setFormatterFactory(new DefaultFormatterFactory(new DateFormatter(new SimpleDateFormat("HH':'mm"))));
		time.setValue(Calendar.getInstance().getTime());

		add(year);
		add(month);
		add(day);
		add(time);
	}

	public DefaultComboBoxModel<String> addThings(ArrayList<String> dates) {
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
			day.setModel(addThings(logic.getDays((String) temp.getSelectedItem())));
		}
	}
}
