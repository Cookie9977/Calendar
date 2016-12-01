package window;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class weekView extends JPanel {
	public weekView(){
		
//		setPreferredSize(new Dimension(600,600));
//		int height = 2;
//		int length = 4;
//		setLayout(new GridLayout(height,length));
		//setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		JLabel[] dagLabel = new JLabel[8];
		String[] days;
		days  = new String[] {"Vecka","Måndag","Tisdag","Onsdag","Torsdag","Fredag","Lördag","Söndag"};
		int width = 600;
		int height = 500;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int xStart = getXStart(screenSize, width);
		int yStart = getYStart(screenSize, height);
		setBounds(xStart, yStart, width, height);
		setPreferredSize(new Dimension(width, height));
		
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		
		int gridWith = (width / 2);
		int gridHeight = (height / 4);
		
		setLayout(gridBag);
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = gridWith;
		gbc.gridheight = gridHeight;
		gbc.weightx = 0.25;
		gbc.weighty = 0.1;
		//add(datePanel, gbc);
	
//		for (int k = 0; k < dagLabel.length; k++){
//			dagLabel[k] = new JLabel();
//			dagLabel[k].setText(days[k]);
//			dagLabel[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
//			dagLabel[k].setFont(new Font("SansSerif",Font.BOLD,20));
//			add(dagLabel[k]);
//		}
//		for (int i = 0; i < length; i++) {
//			for (int j = 0; j < height; j++) {
//				//Här ska datum sättas in från en funktion
//				final JLabel label = new JLabel("Label");
//			    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//			    add(label);
//			    
//			}
//			
//		}
		setBackground(Color.WHITE);
		
		
	}
	
	private int getXStart(Dimension screenSize, int width) {
		int x = (((int) Math.ceil(screenSize.getWidth()) - (width)) / 2);
		return x;
	}

	private int getYStart(Dimension screenSize, int height) {
		int y = (((int) Math.ceil(screenSize.getHeight()) - (height)) / 2);
		return y;
	}

//	
}
