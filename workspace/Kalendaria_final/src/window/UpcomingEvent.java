package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.ClickListener;
import main.Storage;

public class UpcomingEvent extends JPanel {
	private static final long serialVersionUID = 1020255122741607214L;
	public JPanel eventBox;
	public JLabel[] eventListItem;
	public int id;
	public ArrayList<String> eventId;
	private ArrayList<String> eventContent;

	public UpcomingEvent() {
		
		
	//	int tal = createEventList();
		eventId = getEventId();
		
		eventContent = getEventContent();
		JOptionPane.showMessageDialog(null,""+eventId.size());
		setPreferredSize(new Dimension(460, 40));
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		eventBox = new JPanel();
		eventBox.setLayout(new GridLayout(5, 1));
		eventBox.setBackground(new Color(238, 238, 238));
		add(eventBox, BorderLayout.CENTER);
		eventListItem = new JLabel[5];
		id = Storage.id;

		for (int i = 0; i < eventContent.size(); i++) {
			System.out.println("Hämta eventcontent i"+eventContent.get(i));
		}
		for (int i = 0; i < eventListItem.length; i++) {
			eventListItem[i] = new JLabel();
			eventListItem[i].setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.BLACK));
			eventListItem[i].setPreferredSize(new Dimension(40, 20));
			eventListItem[i].addMouseListener(new ClickListener(this));
			try{
				eventListItem[i].setText(""+eventContent.get(i));
			}catch(Exception e){
				eventListItem[i].setText("Inget ");
			}
			
			eventBox.add(eventListItem[i], BorderLayout.NORTH);
		}
	}
	
	
	public ArrayList<String> getEvents()
	{
		return eventContent;
	}
	
	
	public ArrayList<String> getList()
	{
		ArrayList<String>lokal = new ArrayList<String>();
		lokal.add("Kalle");
		lokal.add("Olle");
				return lokal;
		
		
	}
	
	public ArrayList<String> getEventId(){
		ArrayList<String> lokal = new ArrayList<String>();
		try {
		
		//String SQLI = "select event_id from event_link where user_id = " + Storage.id;
		String SQLI = "select event_id from event_link where user_id ="+ Storage.id;
		Object[][] result = Storage.db.getData(SQLI);

		System.out.println(SQLI);;
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				System.out.println("Result " + result[i][j]);
				lokal.add((String) result[i][j]);
				JOptionPane.showMessageDialog(null, result[i]);
			}
		}
	}catch (Exception f) {
		System.out.println(f);

	}
		return lokal;
}

	public ArrayList<String> getEventContent() {
		try {
			eventContent = new ArrayList<String>();
			
			for (int i = 0; i < eventId.size(); i++) {
				String SQLJ = "select title, location, start, end, category from event where id =" + eventId.get(i);
				System.out.println("SQLJ "+SQLJ);
				Object[][] resultat = Storage.db.getData(SQLJ);
						eventContent.add((String) resultat[0][0]+", "+resultat[0][2]+","+resultat[0][3]);
						System.out.println("STRL "+ eventContent.size());
			}

		} catch (Exception f) {
			System.out.println(f);

		}
		
		return eventContent;

	}

}
