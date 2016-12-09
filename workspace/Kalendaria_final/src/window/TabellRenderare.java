package window;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.time.Month;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import logic.TimeLogic;

public class TabellRenderare extends DefaultTableCellRenderer {
	private TimeLogic TimeLogic;
	private MonthView monthRow;
	private MonthView monthColumn;
	private MonthView MonthViewColumnRow;
	
	 public TabellRenderare(MonthView monthView) {
		 super();
		 this.MonthViewColumnRow = monthView;
		 
		// TODO Auto-generated constructor stub
	}
	 

	
	@Override public Component getTableCellRendererComponent(
			    JTable aTable, Object aNumberValue, boolean aIsSelected, 
			    boolean aHasFocus, int aRow, int aColumn
			  ) {  
		
			    /* 
			    * Implementation Note :
			    * It is important that no 'new' objects be present in this 
@@ -25,26 +37,47 @@ public class TabellRenderare extends DefaultTableCellRenderer {
			    * if the table is large, then a large number of objects would be 
			    * created during rendering.
			    */
				JLabel renderedLabel = (JLabel) super.getTableCellRendererComponent(aTable, aNumberValue, aIsSelected, aHasFocus, aRow, aColumn);
				
		 		
		 	//	MonthView monthColumn = MonthView.MonthView().monthColumn;
		 		//System.out.println("Raden h�r"+MonthViewColumnRow.FmonthRow);
			    if (aNumberValue == null) return this;
			    Component renderer = super.getTableCellRendererComponent(
			      aTable, aNumberValue, aIsSelected, aHasFocus, aRow, aColumn
			    );
			    
			    Number value = (Number)aNumberValue;
			    if (aColumn == 6) {
			    	renderer.setFont(new Font("Sansserif", Font.BOLD, 15));
			    
			    
			    renderer.setBackground(new Color(238, 238, 238));
			    renderer.setForeground(new Color(55, 55, 55));
			    renderedLabel.setHorizontalAlignment(LEFT);
		    	renderedLabel.setVerticalAlignment(TOP);
		    	renderer.setFont(new Font("SansSerif", Font.PLAIN, 15));
		    	
		    	if(aColumn <= MonthViewColumnRow.FmonthColumn && aRow == 0) {
				      renderer.setBackground(new Color(178, 178, 178));
				      renderer.setForeground(new Color(238,238,238));
				      renderer.setFont(new Font("SansSerif", Font.ITALIC, 15));
				      
				    }
		    	
		    	else if(aRow >= MonthViewColumnRow.LmonthRow && aColumn <= MonthViewColumnRow.LmonthColumn) {
		    		 renderer.setBackground(new Color(178, 178, 178));
				      renderer.setForeground(new Color(238,238,238));
				      renderer.setFont(new Font("SansSerif", Font.ITALIC, 15));
				    //aColumn <= MonthViewColumnRow.LmonthColumn && aRow == MonthViewColumnRow.LmonthRow
//					    System.out.println("Column "+ aColumn);
//					    System.out.println("Row "+aRow);
				    }
		    	else if (aColumn == 6) {	
			    	renderer.setForeground(Color.RED);
//			      renderer.setBackground(Color.red);
			    }
			    else if(aColumn < 7){
			    	renderer.setFont(new Font("Sansserif", Font.BOLD, 15));
			    }
//			    else if(aColumn == monthCol) {
//			      renderer.setBackground(Color.BLUE);
//			    }
			    
		    	}
			    return this;
			    
			  }
			  
			
		

}