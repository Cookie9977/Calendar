package logic;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import friend.Friend;
import main.Storage;
import pane.EventPane;

public class EventLogic {

	public void checkEvent(EventPane event) {
		String title = event.textFields[0].getText();
		String place = event.textFields[1].getText();
		ArrayList<Friend> friend;
		if (event.friends != null) {
			friend = event.friends;
		} else {
			friend = new ArrayList<Friend>();
		}
		String description = event.description.getText();
		boolean correctTime = true;
		if (event.time_start == null || event.time_end == null) {
			correctTime = false;
		}
		String startTime = event.time_start + ":00";
		String endTime = event.time_end + ":00";
		int category = event.category.getSelectedIndex() + 1;
		boolean dubbleBok = true;
		if (correctTime) {
			dubbleBok = checkTime(startTime, endTime, Storage.id, category);

		}
		if (dubbleBok) {
			int ok = JOptionPane.showConfirmDialog(null, "Du har dubblebokat är du okej med detta?", "Varning",
					JOptionPane.WARNING_MESSAGE);
			if (ok == JOptionPane.OK_OPTION) {
				dubbleBok = false;
			}
		}
//		if (!dubbleBok && correctTime) {
//			addEvent(Storage.id, title, place, description, startTime, endTime, category, friend);
//		} else {
//			JOptionPane.showMessageDialog(null, "Du har dubbelbokat eller skrivit in felaktig tid");
//		}
	}

	private void addEvent(int id, String title, String place, String description, String startTime, String endTime,
			int category, ArrayList<Friend> friend) {
		String SQL = "INSERT INTO event(title, description, location, start, end, category) VALUES ('" + title + "','"
				+ description + "','" + place + "','" + startTime + "','" + endTime + "','" + category + "')";
		int add_id = Storage.db.executeReturn(SQL);
		addLink(add_id, id, friend);

	}

	private void addLink(int add_id, int id, ArrayList<Friend> friend) {
		String SQL = "INSERT INTO event_link(event_id, user_id, owner, accepted) VALUES ('" + add_id + "','" + id
				+ "','1','1')";
		Storage.db.execute(SQL);
		for (int i = 0; i < friend.size(); i++) {
			SQL = "INSERT INTO event_link(event_id, user_id, owner, accepted) VALUES ('" + add_id + "','"
					+ friend.get(i).getId() + "','0', '0')";
			Storage.db.execute(SQL);
		}
	}

	public boolean checkTime(String start, String end, int id, int category) {
		boolean dbook = false;
		long timeStart = timeToLong(start);
		// System.out.println(timeStart);
		long timeEnd = timeToLong(end);
		// System.out.println(timeEnd);
		long db_timeStart, db_timeEnd;
		String SQL = "SELECT DATE_FORMAT(event.start, '%Y%m%d%H%i%s'),DATE_FORMAT(event.end, '%Y%m%d%H%i%s') FROM event_link LEFT JOIN event ON event_link.event_id = event.id WHERE event_link.user_id = '"
				+ id + "' AND event.category = '" + category + "'";
		Object[][] booking = Storage.db.getData(SQL);
		for (int i = 0; i < booking.length; i++) {
			if (dbook) {
				break;
			} else {
				for (int j = 0; j < booking[i].length; j += 2) {
					db_timeStart = Long.parseLong((String) booking[i][j]);
					db_timeEnd = Long.parseLong((String) booking[i][j + 1]);
					if ((timeStart >= db_timeStart && timeStart <= db_timeEnd)
							|| (timeEnd >= db_timeStart && timeEnd <= db_timeEnd)
							|| (timeStart <= db_timeStart && timeEnd >= db_timeEnd)) {
						// System.out.println("ts: "+timeStart);
						// System.out.println("te: "+timeEnd);
						// System.out.println("dbs: "+db_timeStart);
						// System.out.println("dbe: "+db_timeEnd);
						dbook = true;
						break;
					}
				}
			}
		}
		return dbook;
	}

	private long timeToLong(String time) {
		time = time.replace("-", "");
		time = time.replace(":", "");
		time = time.replace(" ", "");
		return Long.parseLong(time);
	}
}
