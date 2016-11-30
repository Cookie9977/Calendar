package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.stream.IntStream;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class dayView extends JPanel{
	public dayView(){
		setPreferredSize(new Dimension(600,600));
		int height = 25;
		int length = 1;
		setLayout(new GridLayout(height,length));
		setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		JLabel[] timeLabel = new JLabel[8];
		String[] days;
		int[] hours = IntStream.iterate(0, n -> n + 1 ).limit(25).toArray();
		
		//{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24}
		
		int[] minutes = IntStream.iterate(1, n -> n + 1).limit(60).toArray();
		
		for (int i = 0; i < height; i++) {
			
				//Här ska datum sättas in från en funktion
				final JLabel label = new JLabel();
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				System.out.println(hours[i]);
				label.setText(String.valueOf(hours[i]));
				add(label);
		
	}
		setBackground(Color.WHITE);
		
	}
}
