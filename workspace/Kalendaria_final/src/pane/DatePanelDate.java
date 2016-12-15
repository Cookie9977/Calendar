package pane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DatePanelDate extends JPanel {

	private static final long serialVersionUID = 2465797833895976796L;

	public DatePanelDate() {
		setLayout(new BorderLayout());
		JLabel dateLabel = new JLabel();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEEE d MMM yyyy");
		LocalDate localDate = LocalDate.now();
		// System.out.println(dtf.format(localDate)); // 2 december 2016
		String date = (String)dtf.format(localDate);
		date = date.substring(0,1).toUpperCase() + date.substring(1).toLowerCase();
		dateLabel.setText(date);
		dateLabel.setVerticalAlignment(SwingConstants.CENTER);
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		dateLabel.setBackground(new Color(32, 86, 173));
		dateLabel.setOpaque(true);
		dateLabel.setForeground(new Color(245,245,245));
		add(dateLabel,BorderLayout.CENTER);
		
	}

}
