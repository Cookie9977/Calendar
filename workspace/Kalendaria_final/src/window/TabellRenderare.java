package window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.time.Month;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import logic.TimeLogic;

public class TabellRenderare extends DefaultTableCellRenderer {
	private static final long serialVersionUID = -6314622466171260880L;
	private MonthView MonthViewColumnRow;
	private TimeLogic timeLogic;
//	public void MultipleLine(){
//		setLineWrap(true);
//		
//	}
	public TabellRenderare(MonthView monthView) {
		super();
		this.MonthViewColumnRow = monthView;
		timeLogic = new TimeLogic();
	}
	

	@Override
	public Component getTableCellRendererComponent(JTable aTable, Object aNumberValue, boolean aIsSelected,
			boolean aHasFocus, int aRow, int aColumn) {

		JLabel renderedLabel = (JLabel) super.getTableCellRendererComponent(aTable, aNumberValue, aIsSelected,
				aHasFocus, aRow, aColumn);

		if (aNumberValue == null)
			return this;
		Component renderer = super.getTableCellRendererComponent(aTable, aNumberValue, aIsSelected, aHasFocus, aRow,
				aColumn);
		

		
		if (aColumn <= MonthViewColumnRow.FmonthColumn && aRow == 0) {
			renderer.setBackground(new Color(180, 180, 180));
			renderer.setForeground(new Color(240, 240, 240));
				setVerticalAlignment(TOP);
				setHorizontalAlignment(LEFT);

			}else if ((aRow == MonthViewColumnRow.LmonthRow && aColumn >= MonthViewColumnRow.LmonthColumn) || aRow > MonthViewColumnRow.LmonthRow ) {
				
				renderer.setBackground(new Color(180, 180, 180));
				renderer.setForeground(new Color(220, 220, 220));
				System.out.println(aRow+ "   " + aColumn);
				setVerticalAlignment(TOP);
				setHorizontalAlignment(LEFT);
				if(aColumn==6){
					renderer.setForeground(new Color(200, 0, 0));
				}

			}	else if (aColumn == 6) {
				renderer.setBackground(new Color(238, 238, 238));
				renderer.setForeground(new Color(200, 0, 0));
				setVerticalAlignment(TOP);
				setHorizontalAlignment(LEFT);
			}	
		
		else{
			renderer.setBackground(new Color(238, 238, 238));
			renderer.setForeground(new Color(100, 100, 100));
			setVerticalAlignment(TOP);
			setHorizontalAlignment(LEFT);
		}
		
		return this;

	}

}