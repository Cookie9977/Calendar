package pane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

public class TimePane extends JPanel implements ActionListener {
	private static final long serialVersionUID = 6463497046997180551L;
	private ArrayList<String> temp;
	private DefaultComboBoxModel<String> model;

	public JFormattedTextField time;
	public CustomComboBox year, month, day;

	public TimePane() {
		year = new CustomComboBox();
		month = new CustomComboBox();
		day = new CustomComboBox();
		time = new JFormattedTextField();

		month.setModel(addThings(getMonths()));
		month.addActionListener(this);
		day.setModel(addThings(getDays("")));
		year.setModel(addThings(getYear()));
		time.setFormatterFactory(new DefaultFormatterFactory(new DateFormatter(new SimpleDateFormat("HH':'mm"))));
		time.setValue(Calendar.getInstance().getTime());

		add(year);
		add(month);
		add(day);
		add(time);
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

	public ArrayList<String> getDays(String month) {
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

	public ArrayList<String> getYear() {
		temp = new ArrayList<String>();
		Calendar cal = new GregorianCalendar();
		int thisYear = cal.get(Calendar.YEAR);

		for (int i = 0; i < 21; i++) {
			temp.add(String.valueOf(thisYear + i));
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("comboBoxChanged")) {
			CustomComboBox temp = (CustomComboBox) e.getSource();
			day.setModel(addThings(getDays((String) temp.getSelectedItem())));
		}
	}
}
