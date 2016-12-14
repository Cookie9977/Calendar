package logic;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.Storage;

public class RequestLogic {

	public RequestLogic() {

	}

	// Returnerar ett object som innehåller data om eventet som man har blivit
	// inbjuden till som ska visas upp i panelen. id, title, start, slut,
	// kategori
	public Object[][] getEvent() {
		String SQL = "SELECT event.id,event.title,DATE_FORMAT(event.start, '%Y-%m-%d-%H%:%i') AS 'Start', "
				+ "DATE_FORMAT(event.end,'%Y-%m-%d-%H%:%i') AS 'End',category.name"
				+ " FROM event_link INNER JOIN event on event_link.event_id = event.id "
				+ " INNER JOIN category on event.category = category.id WHERE event_link.user_id = " + Storage.id
				+ " AND event_link.accepted = 0";
		System.out.println("Event för panel: " + SQL);
		Object[][] data = Storage.db.getData(SQL);
		return data;
	}

	// Returnernar objekt som innehåller data om ett specifikt event TODO join
	// category id, title, description, start, end, category
	public Object[][] getEventInfo(int EventId) {
		String SQL = "SELECT event.id,event.title,event.description,event.location,DATE_FORMAT(event.start, '%Y-%m-%d - %H%:%i') AS 'Start', "
				+ "DATE_FORMAT(event.end,'%Y-%m-%d - %H%:%i') AS 'End',category.name FROM event"
				+ " INNER JOIN category on event.category = category.id WHERE event.id = " + EventId;
		System.out.println("Event info: " + SQL);
		Object[][] data = Storage.db.getData(SQL);
		return data;
	}

	// Använder event Id för att leta rätt på ägaren(den som bjöd in) på
	// eventet. Personen finns i [0][0]
	public Object[][] getOwner(int eventId) {
		String SQL = "SELECT user.email FROM event_link INNER JOIN user on event_link.user_id = user.id where event_link.event_id = "
				+ eventId + " AND event_link.owner = 1";
		System.out.println("Get owner: " + SQL);
		Object[][] data = Storage.db.getData(SQL);
		return data;
	}

	// Returnerar objekt med alla vänförfrågningar.
	public Object[][] getFriend() {
		String SQL = "SELECT user.id, user.email FROM friend_link INNER JOIN user on friend_link.requester = user.id WHERE friend_link.reciver = "
				+ Storage.id + " AND friend_link.accepted = 0";
		System.out.println("Get friend: " + SQL);
		Object[][] data = Storage.db.getData(SQL);
		return data;
	}

	// En JOptionpane för att visa mer information angående eventet indata = id
	// för eventet.
	public void showMore(int reqId) {
		Object[][] data = getEventInfo(reqId);
		Object[][] personData = getOwner(reqId);
		String Title = (String) data[0][1];
		String Descript = (String) data[0][2];
		String Location = (String) data[0][3];
		String Start = (String) data[0][4];
		String End = (String) data[0][5];
		String Category = (String) data[0][6];
		String person = (String) personData[0][0];

		JOptionPane.showMessageDialog(null,
				"<html><body width='400'> <p>Inbjuden av: " + person + "</p><p> Plats: " + Location + ". </p><p>Start: "
						+ Start + "</p><p> Slut: " + End + ". </p><p>Kategori: " + Category + "</p><p>Beskrivning: "
						+ Descript + ".</body></html>",
				Title, JOptionPane.INFORMATION_MESSAGE);
	}

	//
	public void acceptEvent(int reqId) {
		// TODO Auto-generated method stub

	}

	//
	public void denyevent(int reqId) {
		// TODO Auto-generated method stub

	}

	//
	public void acceptFriend(int reqId) {
		// TODO Auto-generated method stub

	}

	//
	public void denyFriend(int reqId) {
		// TODO Auto-generated method stub

	}

	// En select Count för antalet eventförfrågningar till inloggade id
	public int eventLength() {
		int retval;
		String SQL = "SELECT COUNT(*) FROM event_link WHERE user_id = " + Storage.id + " and accepted = 0";
		retval = Integer.parseInt((String) Storage.db.getData(SQL)[0][0]);
		return retval;
	}

	// En select Count för antalet vänförfrågningar till inloggade id
	public int friendLength() {
		int retval;
		String SQL = "SELECT COUNT(*) FROM friend_link WHERE reciver = " + Storage.id + " and accepted = 0";
		retval = Integer.parseInt((String) Storage.db.getData(SQL)[0][0]);
		System.out.println(retval);
		return retval;
	}

	// Slår ihop totalen av eventLenght och friendLength för att få en total
	// siffra för antalet förfrågnignar
	public int requestLength() {
		int events = eventLength();
		int friends = eventLength();
		int retval = events + friends;
		return retval;
	}
}
