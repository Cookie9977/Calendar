package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import logic.TimeLogic;
import main.ClickListener;

public class MonthView extends JPanel{

	private static final long serialVersionUID = 6304391601622162482L;
	private JTable tableMonth;
	private pane.TimePane TimePane;
	private TimeLogic TimeLogic;
	public 	int FmonthColumn;
	public int LmonthRow;
	public int LmonthColumn;

	public MonthView() {
		TimePane = new pane.TimePane();
		TimeLogic = new logic.TimeLogic();
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(770, 720));
		setLayout(new BorderLayout());
		JPanel topLine = new JPanel();
		topLine.setPreferredSize(new Dimension(772, 60));
		topLine.setLayout(new BorderLayout());
		add(topLine, BorderLayout.NORTH);

		
		

		JLabel[] dagLabel = new JLabel[8];
		String[] days;
		days = new String[] { "Vecka","M�ndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "L�rdag", "S�ndag" };
		JPanel theDays = new JPanel();
		theDays.setLayout(new GridLayout(1, 8));
		theDays.setBackground(Color.MAGENTA);
		theDays.setPreferredSize(new Dimension(770, 90));
		topLine.add(theDays, BorderLayout.CENTER);

		for (int k = 0; k < dagLabel.length; k++) {
			dagLabel[k] = new JLabel();
			dagLabel[k].setText(days[k]);
			dagLabel[k].setPreferredSize(new Dimension(96,90));
			dagLabel[k].setBorder(BorderFactory.createLineBorder(new Color(178, 178, 178)));
			theDays.add(dagLabel[k]);
		}
		JPanel westLine = new JPanel();
		//westLine.setSize(new Dimension(60, 400));
		westLine.setLayout(new BorderLayout());
		add(westLine, BorderLayout.WEST);

		JPanel timeView = new JPanel();
		timeView.setBackground(Color.CYAN);
		timeView.setPreferredSize(new Dimension(97, 640));
		timeView.setLayout(new GridLayout(6, 1));
		westLine.add(timeView);
		int[] weeks = TimeLogic.getWeek();
		for (int i = 0; i < 6; i++) {
			JLabel veckan = new JLabel();
			veckan.setText(""+weeks+"");
			veckan.setBorder(BorderFactory.createLineBorder(new Color(178, 178, 178)));
			timeView.add(veckan);

		}
		JPanel containDays = new JPanel();
		containDays.setPreferredSize(new Dimension(770, 645 ));
		containDays.setBackground(Color.green);
		
		containDays.setLayout(new GridLayout(1, 1));
		add(containDays, BorderLayout.CENTER);
		
		tableMonth = new JTable(6, 7){
			public boolean isCellEditable(int aRow, int aColumn) {
				 return false;
				}
		};
		tableMonth.setTableHeader(null);
		tableMonth.setRowHeight(110);
		tableMonth.getColumnModel().getColumn(0).setCellRenderer(new TabellRenderare(this));
		tableMonth.setCellSelectionEnabled(true);	
		tableMonth.addMouseListener(new ClickListener(this));
		containDays.add(tableMonth);
		
		ArrayList<String> dag = TimeLogic.getWeekday();
		System.out.println(dag);
		int lastDayOfMonth = TimeLogic.lastDayMonth();
		int dayOfWeek = TimeLogic.dayOfWeek();
		int firstDayNextMonth = TimeLogic.firstDayNextMonth();
		int lastDayLastMonth = TimeLogic.lastDayLastMonth();
		int justering = dayOfWeek;
		int thisMonth = TimeLogic.getCurrentMonth();
		int firstDayOfMonth = TimeLogic.firstDayMonth();
		int lopNummer = 1;
		System.out.println(thisMonth);
		for (int j = 0; j < tableMonth.getRowCount(); j++) {
			for (int i = 0; i < tableMonth.getColumnCount(); i++) {
			tableMonth.getColumnModel().getColumn(j+1).setCellRenderer(new TabellRenderare(this));
			
				if(lopNummer > 1 && lopNummer<lastDayOfMonth+1 ){
					tableMonth.setValueAt(lopNummer, j, i);
					lopNummer++;
				
				}else if(j==0 && dayOfWeek == i){
					tableMonth.setValueAt(lopNummer, j, i);
					lopNummer++;	
					
				}else if(lopNummer>lastDayOfMonth){
					tableMonth.setValueAt(firstDayNextMonth++, j, i);	
					LmonthColumn = i;
					LmonthRow = j;
					System.out.println("Column "+LmonthColumn+ " Row "+ LmonthRow);
				}
				
				else if(lopNummer<lastDayLastMonth){
					tableMonth.setValueAt(lastDayLastMonth-justering+1, j, (dayOfWeek-justering));
					justering--;
					FmonthColumn = i;
				}	
			}
		}
	}
}
