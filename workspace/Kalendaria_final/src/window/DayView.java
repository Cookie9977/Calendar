package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.stream.IntStream;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DayView extends JPanel {
	private static final long serialVersionUID = -5546841496999182019L;
	private JPanel time;
	private JPanel hoursMinutes;
	private JPanel[][] dayPanel;
	private JTable timeTable;

	public DayView() {
		setBackground(Color.darkGray);
		setPreferredSize(new Dimension(600, 500));
		setLayout(new BorderLayout());
		int[] hours = IntStream.iterate(0, n -> n + 1).limit(25).toArray();
		int[] minutes = IntStream.iterate(0, n -> n + 5).limit(24).toArray();
		JPanel topLine = new JPanel();
		topLine.setPreferredSize(new Dimension(600,70));
		topLine.setBackground(Color.CYAN);
		add(topLine, BorderLayout.NORTH);	
		
		JPanel bottomLine = new JPanel();
		bottomLine.setPreferredSize(new Dimension(600,500));
		bottomLine.setBackground(Color.RED);
		bottomLine.setLayout(new BorderLayout());
		add(bottomLine, BorderLayout.CENTER);	
		
//		JScrollPane timeScroll = new JScrollPane();
//		timeScroll.setBackground(Color.GREEN);
//		timeScroll.setPreferredSize(new Dimension(600,500));
//		bottomLine.add(timeScroll, BorderLayout.EAST);
		String[] columnNames = {"Tid","Händelse"};
		Object[][] time = new Object[24][2];
			
			/*{
				{""+hours+":"+minutes+"","Här är händelse"}
		};*/
		
		for (int i = 0; i < hours.length; i++) {
			//time[0][i] = hours[i];
			System.out.println(hours[i]);
		}
		
		timeTable = new JTable(time,columnNames);
		timeTable.setRowHeight(20);
		timeTable.setBackground(Color.GREEN);
		bottomLine.add(new JScrollPane(timeTable));		
		
		
		
		
		
//		int height = 24;
//		int length = 2;
//		dayPanel = new JPanel[height][length];
//		setLayout(new GridLayout(height, length));
//		setPreferredSize(new Dimension(800, 600));
//
//		for (int i = 0; i < height; i++) {
//			for (int j = 0; j < length; j++) {
//				dayPanel[i][j] = new JPanel();
//				add(dayPanel[i][j]);
//			}
//		}
//		time = new JPanel();
//		time.setPreferredSize(new Dimension(300, 30));
//		time.setBackground(Color.black);
//		// Gammal
//		int[] hours = IntStream.iterate(0, n -> n + 1).limit(24).toArray();
//		int[] minutes = IntStream.iterate(0, n -> n + 5).limit(24).toArray();
//		;
//
//		for (int i = 0; i < height; i++) {
//			for (int j = 0; j < minutes.length; j++) {
//
//				// Här ska datum sättas in från en funktion
//				final JLabel label = new JLabel();
//				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//				// System.out.println(hours[i]);
//				// System.out.println(minutes[i]);
//				label.setText(String.valueOf(hours[i]) + ":" + String.valueOf(minutes[j]));
//				label.setSize(20, 300);
//				dayPanel[i][0].add(label);
//
//			}
//		}
//		// Ny
//
//		dayPanel[0][0].add(time);
//
//		setPreferredSize(new Dimension(600, 600));
//		hoursMinutes = new JPanel();
//		hoursMinutes.setBackground(Color.CYAN);

		// int height = 25;
		// int length = 6;
		// setLayout(new GridLayout(height,length));
		// time = new JPanel();
		// time.setPreferredSize(new Dimension(500,500));
		// time.setLocation(10, 1);
		// time.setBackground(Color.BLUE);
		// time.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		// add(time);
		//
		//
		// int[] hours = IntStream.iterate(0, n -> n + 1 ).limit(25).toArray();
		// int[] minutes = IntStream.iterate(0, n -> n + 5
		// ).limit(12).toArray();;

		// for (int i = 0; i < height; i++) {
		//
		// //Här ska datum sättas in från en funktion
		//// final JLabel label = new JLabel();
		//// label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//// System.out.println(hours[i]);
		//// System.out.println(minutes[i]);
		//// label.setText(String.valueOf(hours[i])+":"+String.valueOf(minutes[i]));
		//// time.add(label);
		//
		// }
		

	}
}
