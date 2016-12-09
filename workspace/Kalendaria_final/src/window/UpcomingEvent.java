package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.ClickListener;
import main.Main;

public class UpcomingEvent extends JPanel {
	public UpcomingEvent(){
		
		setPreferredSize(new Dimension(460,40));
		setLayout(new BorderLayout());
		JPanel eventBox = new JPanel();
		eventBox.setLayout(new GridLayout(10, 1));
		eventBox.setBackground(new Color(238, 238, 238));
		add(eventBox, BorderLayout.CENTER);
		JLabel[] eventListItem = new JLabel[10];
		String ids = "";
		try {
			String SQL = "select * from event";
			System.out.println(SQL);

			Object[][] data = Main.db.getData(SQL);

			// String SQLJ = "select id from user where email ='"+email+"' AND
			// password ='"+new String(pass)+"'";

			if (!(data[0][0] == "")) {
				ids = (String) data[0][0];
				Main.id = Integer.parseInt(ids);
				String SQLI = "select * from event_link where user_id = "+ Main.id;
				System.out.println("SQLI = "+SQLI);
				}
		}catch (Exception f) {
			System.out.println(f);
			
		}
		
		
		for (int i = 0; i < eventListItem.length; i++) {
			eventListItem[i] = new JLabel();
			eventListItem[i].setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.BLACK));
			eventListItem[i].setPreferredSize(new Dimension(40, 20));
			eventListItem[i].addMouseListener(new ClickListener(this));	

			eventListItem[i].setText("Test "+i);
			System.out.println(eventListItem[i]);
			eventBox.add(eventListItem[i], BorderLayout.NORTH);
		}
		
	}
}
