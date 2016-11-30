package Pane;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EventPane extends JPanel implements ActionListener{
	private static final long serialVersionUID = -492939221067396792L;
	private JLabel[] labels;
	private JTextField[] textFields;
	private JTextArea description;
	private String[] labelText = {"Titel", "Plats", "Start", "Stopp", "Kategori", "Beskrivning"};
	private DateComboBox date_e, date_s, month_e, month_s, category;
	private ArrayList<String> temp;
	private DefaultComboBoxModel<String> date_e_model, date_s_model,month_e_model,month_s_model;

	public EventPane() {
		//declaring
		labels = new JLabel[6];
		textFields = new JTextField[2];
		description = new JTextArea();
		date_e_model = new DefaultComboBoxModel<String>();
		date_s_model = new DefaultComboBoxModel<String>();
		month_e_model = new DefaultComboBoxModel<String>();
		month_s_model = new DefaultComboBoxModel<String>();
		date_e = new DateComboBox();
		date_e.setModel(addDate(date_e_model, getMonths()));
		date_e.setId(1);
		date_e.addActionListener(this);
		
		date_s = new DateComboBox();
		date_s.setModel(addDate(date_s_model, getMonths()));
		date_s.setId(2);
		date_s.addActionListener(this);
		
		month_e = new DateComboBox();
		month_e.setModel(addDate(month_e_model,getDates("")));
		
		month_s = new DateComboBox();
		month_s.setModel(addDate(month_s_model,getDates("")));
		//set labels
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
		//layout
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gridBag);
		
		//description setup
		description.setPreferredSize(new Dimension(175, 100));
		description.setBorder(BorderFactory.createEtchedBorder());
		labels[5].setLabelFor(description);
		
		
		add(labels[5]);
		add(description);
		add(date_e);
		add(date_s);
		add(month_e);
		add(month_s);
	

	}
	
	public ArrayList<String> getMonths(){
		DateFormatSymbols dfc = new DateFormatSymbols();
		temp = new ArrayList<String>();
        String[] months = dfc.getMonths();
        for (int i = 0; i < months.length-1; i++) {
            temp.add(months[i]);
        }
		return temp;
	}
	
	public ArrayList<String> getDates(String month){
		DateFormatSymbols dfc = new DateFormatSymbols();
		temp = new ArrayList<String>();
		int monthValue = 0;
        String[] months = dfc.getMonths();
        for (int i = 0; i < months.length-1; i++) {
           if (months[i].equals(month)) {
			monthValue = i;
			break;
           }
        } 
        Calendar cal = new GregorianCalendar();
        cal.set(cal.get(Calendar.YEAR), monthValue, cal.get(Calendar.DAY_OF_MONTH));
        int nrDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < nrDays; i++) {
			temp.add(String.valueOf(i+1));
		}
		return temp;
	}
	
	public DefaultComboBoxModel<String> addDate(DefaultComboBoxModel<String> model, ArrayList<String> dates){
		model.removeAllElements();
		for (int i = 0; i < dates.size(); i++) {
			model.addElement(dates.get(i));
		}
		return model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unchecked")
		DateComboBox temp = (DateComboBox) e.getSource();
		//System.out.println(temp.getSelectedItem());
		if (temp.getId() == 1) {
			month_e.setModel(addDate(month_e_model,getDates((String)temp.getSelectedItem())));
			repaint();
		} else {
			month_s.setModel(addDate(month_s_model,getDates((String)temp.getSelectedItem())));
			//add(date_s);
		}
	}

}
