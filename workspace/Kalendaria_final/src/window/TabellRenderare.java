package window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TabellRenderare extends DefaultTableCellRenderer {
	private static final long serialVersionUID = -6314622466171260880L;
	private MonthView MonthViewColumnRow;
//	public void MultipleLine(){
//		setLineWrap(true);
//		
//	}
	public TabellRenderare(MonthView monthView) {
		super();
		this.MonthViewColumnRow = monthView;
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

		if (aColumn == 6) {
			renderer.setBackground(new Color(238, 238, 238));
			renderer.setForeground(new Color(55, 55, 55));
			renderedLabel.setHorizontalAlignment(LEFT);
			renderedLabel.setVerticalAlignment(TOP);
			renderer.setFont(new Font("SansSerif", Font.PLAIN, 15));

			if (aColumn <= MonthViewColumnRow.FmonthColumn && aRow == 0) {
				renderer.setBackground(new Color(178, 178, 178));
				renderer.setForeground(new Color(238, 238, 238));
				renderedLabel.setHorizontalAlignment(LEFT);
				renderedLabel.setVerticalAlignment(TOP);
				renderer.setFont(new Font("SansSerif", Font.ITALIC, 15));

			}

			else if (aRow >= MonthViewColumnRow.LmonthRow && aColumn <= MonthViewColumnRow.LmonthColumn) {
				renderer.setBackground(new Color(178, 178, 178));
				renderer.setForeground(new Color(238, 238, 238));
				renderedLabel.setHorizontalAlignment(LEFT);
				renderedLabel.setVerticalAlignment(TOP);
				renderer.setFont(new Font("SansSerif", Font.ITALIC, 15));
				// aColumn <= MonthViewColumnRow.LmonthColumn && aRow ==
				// MonthViewColumnRow.LmonthRow
				// System.out.println("Column "+ aColumn);
				// System.out.println("Row "+aRow);
			} else if (aColumn == 6) {
				renderer.setForeground(Color.RED);
				renderedLabel.setHorizontalAlignment(LEFT);
				renderedLabel.setVerticalAlignment(TOP);
				// renderer.setBackground(Color.red);
			} else if (aColumn < 7) {
				renderedLabel.setHorizontalAlignment(LEFT);
				renderedLabel.setVerticalAlignment(TOP);
				renderer.setFont(new Font("Sansserif", Font.BOLD, 15));
			}
			// else if(aColumn == monthCol) {
			// renderer.setBackground(Color.BLUE);
			// }
			
		}
		return this;

	}

}