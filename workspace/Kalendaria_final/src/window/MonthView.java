package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.stream.IntStream;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import logic.TimeLogic;

public class MonthView extends JPanel {

	private static final long serialVersionUID = 6304391601622162482L;
	private JTable tableMonth;
	private pane.TimePane TimePane;
	private TimeLogic TimeLogic;

	public MonthView() {
		TimePane = new pane.TimePane();
		TimeLogic = new logic.TimeLogic();
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(600, 600));
		setLayout(new BorderLayout());
		JPanel topLine = new JPanel();
		topLine.setPreferredSize(new Dimension(600, 50));
		topLine.setLayout(new BorderLayout());
		add(topLine, BorderLayout.NORTH);

		JPanel vecka = new JPanel();
		vecka.setPreferredSize(new Dimension(50, 90));
		JLabel veckaLabel = new JLabel();
		veckaLabel.setText("Veckor");
		vecka.add(veckaLabel);
		topLine.add(vecka, BorderLayout.WEST);

		JLabel[] dagLabel = new JLabel[7];
		String[] days;
		days = new String[] { "Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag" };
		JPanel theDays = new JPanel();
		theDays.setLayout(new GridLayout(1, 7));
		theDays.setPreferredSize(new Dimension(550, 90));
		topLine.add(theDays, BorderLayout.EAST);

		for (int k = 0; k < dagLabel.length; k++) {
			dagLabel[k] = new JLabel();
			dagLabel[k].setText(days[k]);
			//dagLabel[k].setPreferredSize(new Dimension(0, 50));
			dagLabel[k].setBackground(Color.CYAN);
			dagLabel[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			theDays.add(dagLabel[k]);
		}
		JPanel westLine = new JPanel();
		//westLine.setSize(new Dimension(60, 400));
		westLine.setLayout(new BorderLayout());
		add(westLine, BorderLayout.WEST);

		JPanel timeView = new JPanel();
		timeView.setBackground(Color.GRAY);
		timeView.setPreferredSize(new Dimension(52, 500));
		timeView.setLayout(new GridLayout(5, 1));
		westLine.add(timeView);

		for (int i = 0; i < 5; i++) {
			JLabel veckan = new JLabel();
			veckan.setText("v.");
			veckan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			timeView.add(veckan);

		}
		
		
		JPanel containDays = new JPanel();
		containDays.setPreferredSize(new Dimension(600, 500));
		containDays.setBackground(Color.green);
		containDays.setBorder(BorderFactory.createLineBorder(Color.RED));
		containDays.setLayout(new GridLayout(1, 1));
		add(containDays, BorderLayout.CENTER);

		
		tableMonth = new JTable(5, 7);
		tableMonth.setTableHeader(null);
		tableMonth.setRowHeight(110);

		containDays.add(tableMonth);
		

		ArrayList<String> dag = TimeLogic.getWeekday();
		System.out.println(dag);
		int thisMonth = TimeLogic.getCurrentMonth();
		int firstDayOfMonth = TimeLogic.firstDayMonth();
		int lastDayOfMonth = TimeLogic.lastDayMonth();
		ArrayList<String> daysOfMonth = TimeLogic.getDays(""+thisMonth);
		int lopNummer = 1;
		
		for (int j = 0; j < tableMonth.getRowCount(); j++) {
			for (int i = 0; i < tableMonth.getColumnCount(); i++) {
			
				if(lopNummer > 1 && lopNummer<lastDayOfMonth+1 ){
					tableMonth.setValueAt(lopNummer, j, i);
					lopNummer++;
				}else if(j==0 && 3 == i){//Fixa 3
					tableMonth.setValueAt(lopNummer, j, i);
					lopNummer++;
				}else if(lopNummer>lastDayOfMonth){
					tableMonth.setValueAt("Empty", j, i);
					
				}
				//tableMonth.setValueAt(dag.get(i), j, i);
				String thisDay = daysOfMonth.get(i);
				System.out.println(firstDayOfMonth);
				System.out.println(lastDayOfMonth);
				
				
			}
		}
		System.out.println(daysOfMonth);

		
		
		
		


	

	}

}
