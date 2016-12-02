package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.stream.IntStream;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class monthView extends JPanel {

private static final long serialVersionUID = 6304391601622162482L;
private JTable table;
	public monthView(){
		setPreferredSize(new Dimension(600,600));
//		int height = 6;
//		int length = 6;
//		setLayout(new GridLayout(height,length));
//		setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		setLayout(new BorderLayout());
		int[] m = IntStream.iterate(0, n -> n + 1 ).limit(24).toArray();
		JPanel topLine = new JPanel();
		topLine.setPreferredSize(new Dimension(600,50));
		topLine.setLayout(new BorderLayout());
		add(topLine, BorderLayout.NORTH);
		
		JPanel vecka = new JPanel();
		vecka.setPreferredSize(new Dimension(20,50));
		JLabel veckaLabel = new JLabel();
		veckaLabel.setText("Veckor");
		vecka.add(veckaLabel);
		topLine.add(vecka, BorderLayout.WEST);
		
		JLabel[] dagLabel = new JLabel[7];
		String[] days;
		days  = new String[] {"Måndag","Tisdag","Onsdag","Torsdag","Fredag","Lördag","Söndag"};		
		JPanel theDays = new JPanel();
		theDays.setLayout(new GridLayout(1,7));
		theDays.setPreferredSize(new Dimension(500,90));
		topLine.add(theDays, BorderLayout.EAST);
		
		JPanel westLine = new JPanel();
		westLine.setSize(new Dimension(80,400));
		westLine.setLayout(new BorderLayout());
		add(westLine, BorderLayout.WEST);
		
		JPanel timeView = new JPanel();
		timeView.setBackground(Color.GRAY);
		timeView.setPreferredSize(new Dimension(80,500));
		timeView.setLayout(new GridLayout(5,1));
		timeView.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
		westLine.add(timeView);
		
		for (int i = 0; i < 5; i++) {
			JLabel veckan = new JLabel();
			veckan.setBorder(BorderFactory.createLineBorder(Color.blue));
			veckan.setText("v.");
			timeView.add(veckan);


		}
		
		JPanel containDays = new JPanel();
		containDays.setPreferredSize(new Dimension(600,500));
		containDays.setBackground(Color.green);
		containDays.setBorder(BorderFactory.createLineBorder(Color.RED));
		containDays.setLayout(new GridLayout(1,1));
		add(containDays, BorderLayout.CENTER);
		
//		JPanel lengthDays = new JPanel();
//		lengthDays.setPreferredSize(new Dimension(200,40));
//		lengthDays.setLayout(new GridLayout(1,7));
//		lengthDays.setBackground(Color.ORANGE);
//		containDays.add(lengthDays);
		
		
		JPanel heightDays = new JPanel();
		heightDays.setPreferredSize(new Dimension(40,200));
		//heightDays.setLayout(new GridLayout(5,7));
		heightDays.setBackground(Color.GREEN);
		
		//containDays.add(heightDays);
		table = new JTable(5,7);
		table.setRowHeight(100);
		JScrollPane scroll = new JScrollPane(table);
		containDays.add(scroll);
		heightDays.setLocation(0,0);
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				

				JLabel dayByDays = new JLabel();
				dayByDays.setText("Höjd");
				dayByDays.setBorder(BorderFactory.createLineBorder(Color.RED));
				dayByDays.setPreferredSize(new Dimension(40,40));
				heightDays.add(dayByDays);
				System.out.println(m[j]);
			}
		}
		
		
//		
//		for (int i = 0; i < 5; i++) {
//			JLabel dayByDay = new JLabel();
//			dayByDay.setText("Dag nr..");
//			dayByDay.setBorder(BorderFactory.createLineBorder(Color.RED));
//			heightDays.add(dayByDay);
//		}
		
//		for (int i = 0; i < 7; i++) {
//			//Här ska datum sättas in från en funktion
//			final JLabel label = new JLabel("Höjd");
//		    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		    
//
//	}
		setBackground(Color.WHITE);

	}

}
