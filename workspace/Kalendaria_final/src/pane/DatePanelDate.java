package pane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DatePanelDate extends JPanel {

	private static final long serialVersionUID = 2465797833895976796L;

	public DatePanelDate() {
		JLabel dateLabel = new JLabel();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEEE d MMMM yyyy");
		LocalDate localDate = LocalDate.now();
		// System.out.println(dtf.format(localDate)); // 2 december 2016
		dateLabel.setText(dtf.format(localDate));
		dateLabel.setVerticalAlignment(SwingConstants.CENTER);
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
	}

}
