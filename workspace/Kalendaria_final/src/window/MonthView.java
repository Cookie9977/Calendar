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

public class MonthView extends JPanel implements MouseListener {

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
		timeView.setPreferredSize(new Dimension(54, 500));
		timeView.setLayout(new GridLayout(6, 1));
		westLine.add(timeView);

		for (int i = 0; i < 6; i++) {
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
		
		
		
		tableMonth = new JTable(6, 7){
			public boolean isCellEditable(int aRow, int aColumn) {

				 return false;
				}
		};
		tableMonth.setTableHeader(null);
		tableMonth.setRowHeight(92);
		tableMonth.getColumnModel().getColumn(0).setCellRenderer(new TabellRenderare(this));
		tableMonth.setCellSelectionEnabled(true);	
		tableMonth.addMouseListener(new ClickListener(this){
			@Override
			public void DoubleClick(MouseEvent e) {
				JTable source = (JTable)e.getSource();
		        int row = source.rowAtPoint( e.getPoint() );
		        int column = source.columnAtPoint( e.getPoint() );
		        
		        if(source.isRowSelected(row)){
		        	System.out.println("Row:" +row+ "Happy birthday!");
		        	
		        }
		        if (! source.isRowSelected(row))
		            source.changeSelection(row, column, false, false);
				System.out.println("Col: "+column+ " Row: "+row);
			}

			
			
		});
		containDays.add(tableMonth);
		
		ArrayList<String> dag = TimeLogic.getWeekday();
		System.out.println(dag);
		int lastDayOfMonth = TimeLogic.lastDayMonth();
		int dayOfWeek = TimeLogic.dayOfWeek();
		int firstDayNextMonth = TimeLogic.firstDayNextMonth();
		int lastDayLastMonth = TimeLogic.lastDayLastMonth();
		int justering = dayOfWeek;
		//int justering = TimeLogic.justering();
		int lopNummer = 1;
		
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
