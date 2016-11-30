package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class weekView extends JPanel {
	public weekView(){
		setPreferredSize(new Dimension(600,600));
		int height = 2;
		int length = 4;
		setLayout(new GridLayout(height,length));
		setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		JLabel[] dagLabel = new JLabel[8];
		String[] days;
		days  = new String[] {"Vecka","M�ndag","Tisdag","Onsdag","Torsdag","Fredag","L�rdag","S�ndag"};
		
		for (int k = 0; k < dagLabel.length; k++){
			dagLabel[k] = new JLabel();
			dagLabel[k].setText(days[k]);
			dagLabel[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			dagLabel[k].setFont(new Font("SansSerif",Font.BOLD,20));
			add(dagLabel[k]);
		}
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < height; j++) {
				//H�r ska datum s�ttas in fr�n en funktion
				final JLabel label = new JLabel("Label");
			    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			    add(label);
			    
			}
			
		}
		setBackground(Color.WHITE);
		
		
	}
}
