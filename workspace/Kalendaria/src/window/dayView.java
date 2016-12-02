package window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.stream.IntStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class dayView extends JPanel{
	private JPanel time;
	private JPanel hoursMinutes;
	private JPanel[][] dayPanel;
	public dayView(){
		
		int height = 24;
		int length = 2;
		dayPanel = new JPanel[height][length];
		setLayout(new GridLayout(height, length));
		setPreferredSize(new Dimension(800,600));
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < length; j++) {
				dayPanel[i][j] = new JPanel();
				add(dayPanel[i][j]);
			}
		}
		time = new JPanel();
		time.setPreferredSize(new Dimension(300,30));
		time.setBackground(Color.black);
		//Gammal
		int[] hours = IntStream.iterate(0, n -> n + 1 ).limit(24).toArray();		
		int[] minutes = IntStream.iterate(0, n -> n + 5 ).limit(24).toArray();;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < minutes.length; j++) {
				
			
				//Här ska datum sättas in från en funktion
				final JLabel label = new JLabel();
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				System.out.println(hours[i]);
				System.out.println(minutes[i]);
				label.setText(String.valueOf(hours[i])+":"+String.valueOf(minutes[j]));
				label.setSize(20,300);
				dayPanel[i][0].add(label);
		
			}
		}		
	//Ny	
		
		
		
		
		dayPanel[0][0].add(time);
	
		setPreferredSize(new Dimension(600,600));
		hoursMinutes = new JPanel();
		hoursMinutes.setBackground(Color.CYAN);
		
//		int height = 25;
//		int length = 6;
//		setLayout(new GridLayout(height,length));
//		time = new JPanel();
//		time.setPreferredSize(new Dimension(500,500));
//		time.setLocation(10, 1);
//		time.setBackground(Color.BLUE);
//		time.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
//		add(time);
//		
//
//		int[] hours = IntStream.iterate(0, n -> n + 1 ).limit(25).toArray();		
//		int[] minutes = IntStream.iterate(0, n -> n + 5 ).limit(12).toArray();;
		
//		for (int i = 0; i < height; i++) {
//
//				//Här ska datum sättas in från en funktion
////				final JLabel label = new JLabel();
////				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
////				System.out.println(hours[i]);
////				System.out.println(minutes[i]);
////				label.setText(String.valueOf(hours[i])+":"+String.valueOf(minutes[i]));
////				time.add(label);
//		
//	}
		setBackground(Color.darkGray);
		
	}
}
