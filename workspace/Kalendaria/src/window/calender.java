package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class calender extends JPanel {
	public calender(){
		setPreferredSize(new Dimension(500,500));
		int height = 7;
		int length = 6;
		setLayout(new GridLayout(height,length));
		setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		JLabel[] dagLabel = new JLabel[7];
		String[] days;
		days  = new String[] {"Måndag","Tisdag","Onsdag","Torsdag","Fredag","Lördag","Söndag"};
		
		for (int k = 0; k < dagLabel.length; k++){
			dagLabel[k] = new JLabel();
			dagLabel[k].setText(days[k]);
			add(dagLabel[k]);
		}
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < height; j++) {
				
				final JLabel label = new JLabel("Label");
			    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			    add(label);
			    
			}
			
		}
		setBackground(Color.WHITE);
		
		
	}

}
