package window;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import logic.TimeLogic;

public class TabellRenderare extends DefaultTableCellRenderer {
	private TimeLogic TimeLogic;
	private MonthView monthRow;
	private MonthView monthColumn;
	
	 @Override public Component getTableCellRendererComponent(
			    JTable aTable, Object aNumberValue, boolean aIsSelected, 
			    boolean aHasFocus, int aRow, int aColumn
			  ) {  
			    /* 
			    * Implementation Note :
			    * It is important that no 'new' objects be present in this 
			    * implementation (excluding exceptions):
			    * if the table is large, then a large number of objects would be 
			    * created during rendering.
			    */
		 		
		 	//	MonthView monthColumn = MonthView.MonthView().monthColumn;
			    if (aNumberValue == null) return this;
			    Component renderer = super.getTableCellRendererComponent(
			      aTable, aNumberValue, aIsSelected, aHasFocus, aRow, aColumn
			    );
			    Number value = (Number)aNumberValue;
			    if (aColumn == 6) {
			    	renderer.setFont(new Font("Sansserif", Font.BOLD, 15));
			    	renderer.setForeground(Color.RED);
//			      renderer.setBackground(Color.red);
			    }
			    else if(aColumn < 7){
			    	renderer.setFont(new Font("Sansserif", Font.BOLD, 15));
			    }
//			    else if(aColumn == monthCol) {
//			      renderer.setBackground(Color.BLUE);
//			    }
			    return this;
			  }
			  

}
