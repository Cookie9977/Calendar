package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import main.ClickListener;
import main.Storage;

public class UpcomingEvent extends JPanel {
	private static final long serialVersionUID = 1020255122741607214L;
	public JPanel eventBox;
	public JLabel[] eventListItem;
	public int id;
	public ArrayList<String> eventId;
	private DefaultListModel<CalendarEvent> eventContent;
	private JList<CalendarEvent> lista;
	public UpcomingEvent() {
		
		refreshEvent();	
	}
	public void refreshEvent(){
		eventId = getEventId();
		eventContent = getEventContent();
		lista = new JList<CalendarEvent>(eventContent);
		
		//JOptionPane.showMessageDialog(null,""+eventId.size());
		setPreferredSize(new Dimension(100, 600));
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		eventBox = new JPanel();
		eventBox.setLayout(new GridLayout(5, 1));
		eventBox.setBackground(new Color(238, 238, 238));
		
		add(eventBox, BorderLayout.CENTER);
		eventListItem = new JLabel[5];
		for (int i = 0; i < eventContent.size(); i++) {
			System.out.println("Hämta eventcontent i "+eventContent.get(i));
		}
		
		for (int i = 0; i < eventListItem.length; i++) {
			eventListItem[i] = new JLabel();
//			eventListItem[i].setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.BLACK));
//			eventListItem[i].setPreferredSize(new Dimension(40, 20));
//			eventListItem[i].addMouseListener(new ClickListener(this));
			
			try{
				eventListItem[i].setText(""+eventContent.get(i));
				
			}catch(Exception e){
			
				eventListItem[i].setText("Inget ");
			}
			//eventBox.add(eventListItem[i], BorderLayout.NORTH);
			eventBox.add(lista, BorderLayout.NORTH);
		}
//			
	}
	public void updateEventList(){
		eventContent.removeAllElements();
		refreshEvent();
		System.out.println("HEllo");

	}
	
	
//	public ArrayList<String> getEvents()
//	{
//		return eventContent;
//	}
	
	
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

		String SQLI = "select event_id from event_link inner join event on event_link.event_id = event.id where user_id = "+Storage.id+" and accepted = 1 order by start asc ";

		Object[][] result = Storage.db.getData(SQLI);
		
		System.out.println(SQLI);;
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				System.out.println("EventID: " + result[i][j]);
				lokal.add((String) result[i][j]);
				//JOptionPane.showMessageDialog(null, result[i]);
			}
		}
	}catch (Exception f) {
		System.out.println(f);

	}
		return lokal;
}

	public DefaultListModel<CalendarEvent> getEventContent() {
		try {
			eventContent = new DefaultListModel<CalendarEvent>();
			
			for (int i = 0; i < eventId.size(); i++) {
				String SQLJ = "select title, location, start, end, category from event where id =" + eventId.get(i);
				System.out.println("SQLJ: "+SQLJ);
				Object[][] resultat = Storage.db.getData(SQLJ);
						eventContent.addElement(new CalendarEvent( resultat[0][0].toString(),resultat[0][2].toString(),resultat[0][3].toString()));
						System.out.println("Antal resultat:  "+ eventContent.size());
			}

		} catch (Exception f) {
			System.out.println(f);

		}
		
		return eventContent;

	}

}
