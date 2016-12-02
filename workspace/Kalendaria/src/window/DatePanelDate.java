package window;

import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DatePanelDate extends JPanel{

	private static final long serialVersionUID = 2465797833895976796L;

	public DatePanelDate() {
		JLabel dateLabel = new JLabel();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEEE d MMMM yyyy");
		LocalDate localDate = LocalDate.now();
		// System.out.println(dtf.format(localDate)); // 2 december 2016
		dateLabel.setText(dtf.format(localDate));
		dateLabel.setFont(new Font("SansSerif",Font.BOLD,20));
		add(dateLabel);
	}

}
