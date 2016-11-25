package window;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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
		textFields = new JTextField[4];
		description = new JTextArea();
		
		for (int i = 0; i < labelText.length; i++) {
			labels[i] = new JLabel(labelText[i]);
		}
		
		//set labels
		//labels[5].setLabelFor(description);
		add(description);
		//layout
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gridBag);
	}

}
