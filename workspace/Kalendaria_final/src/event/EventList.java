package event;

import java.util.ArrayList;

import main.Storage;

public class EventList extends ArrayList<Event>{
	private static final long serialVersionUID = 81700415866039777L;

	public EventList() {
		String SQL = "SELECT event.id, event.title, event.description, event.location, event.start, event.end, category.name, event_link.owner FROM event_link LEFT JOIN event ON event_link.event_id = event.id INNER JOIN category on event.category = category.id WHERE event_link.user_id = "
				+ Storage.id;
		// + " AND DATE_FORMAT(event.start, '%Y-%m-%d') = '" + day
		// + "' AND DATE_FORMAT(event.end, '%Y-%m-%d') = '" + day + "'"
		Object[][] event = Storage.db.getData(SQL);
		for (int i = 0; i < event.length; i++) {
			this.add(new Event(event[i]));
		}
	}
}
