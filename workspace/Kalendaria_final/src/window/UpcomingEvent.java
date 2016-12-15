package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import main.Storage;

public class UpcomingEvent extends JPanel {
	private static final long serialVersionUID = 1020255122741607214L;
	public int id;
	public ArrayList<String> eventId;
	private DefaultListModel<CalendarEvent> eventContent;
	private JList<CalendarEvent> lista;

	// private UpcomingEvent upcomingEvent;
	private WindowModifications windowmodifications;

	public UpcomingEvent(Window windowVal) {
		refreshEvent();
	}

	public void refreshEvent() {
		eventId = getEventId();
		try {
			eventContent.clear();
		} catch (Exception e) {
			System.out.println("Feli" + e);
		}
		eventContent = getEventContent();

		lista = new JList<CalendarEvent>(eventContent);

		setPreferredSize(new Dimension(200, 600));
		setLayout(new BorderLayout());
		setBackground(new Color(32, 86, 173));
		
		String upcoming = "Kommande händelser";
		JLabel heading = new JLabel(upcoming);
		heading.setFont(new Font("SansSerif", Font.BOLD, 22));
		heading.setForeground(new Color(245,245,245));
		add(heading, BorderLayout.NORTH);
		add(lista, BorderLayout.SOUTH);
	}

	public void updateEventList() {
		windowmodifications.updateUpcomingEvents();
		System.out.println("HEllo");

	}

	public ArrayList<String> getEventId() {

		ArrayList<String> lokal = new ArrayList<String>();
		try {

			// String SQLI = "select event_id from event_link where user_id = "
			// + Storage.id;

			String SQLI = "select event_id from event_link inner join event on event_link.event_id = event.id where user_id = "
					+ Storage.id + " and accepted = 1 order by start asc ";

			Object[][] result = Storage.db.getData(SQLI);

			// System.out.println(SQLI);;
			for (int i = 0; i < result.length; i++) {
				for (int j = 0; j < result[i].length; j++) {
					// System.out.println("EventID: " + result[i][j]);
					lokal.add((String) result[i][j]);
					// JOptionPane.showMessageDialog(null, result[i]);
				}
			}
		} catch (Exception f) {
			System.out.println(f);

		}
		return lokal;
	}

	public DefaultListModel<CalendarEvent> getEventContent() {
		try {
			eventContent = new DefaultListModel<CalendarEvent>();

			for (int i = 0; i < eventId.size(); i++) {
				String SQLJ = "select title, location, start, end, category from event where id =" + eventId.get(i);
				// System.out.println("SQLJ: "+SQLJ);
				Object[][] resultat = Storage.db.getData(SQLJ);

				eventContent.addElement(new CalendarEvent(resultat[0][0].toString(), resultat[0][2].toString(),
						resultat[0][3].toString()));
				System.out.println("Antal resultat:  " + eventContent.size());

			}

		} catch (Exception f) {
			System.out.println(f);

		}

		return eventContent;

	}

}
