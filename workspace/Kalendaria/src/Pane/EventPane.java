package Pane;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EventPane extends JPanel{
	private static final long serialVersionUID = -492939221067396792L;
	private JLabel[] labels;
	private JTextField[] textFields;
	private JTextArea description;
	private String[] labelText = {"Titel", "Plats", "Start", "Stopp", "Kategori", "Beskrivning"};

	public EventPane() {
		//declaring
		labels = new JLabel[6];
		textFields = new JTextField[2];
		description = new JTextArea();
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
	

	}

}
