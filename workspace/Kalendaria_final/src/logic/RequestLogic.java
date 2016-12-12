package logic;

import main.Storage;

public class RequestLogic {

	public RequestLogic() {

	}

	// Returnerar ett object som innehåller data om eventet som man har blivit inbjuden till.
	public Object[][] getEvent() {
		String SQL = "SELECT event.id,event.title,event.description,event.location,event.start,event.end,event.category"
				+ " FROM event_link INNER JOIN event on event_link.event_id = event.id WHERE event_link.user_id = "
				+ Storage.id + " AND event_link.accepted = 0";
		System.out.println(SQL);
		Object[][] data = Storage.db.getData(SQL);
		return data;
	}
	
	// TODO Använder event Id för att leta rätt på ägaren(den som bjöd in) på eventet. 
	// Kommer retunera det som ett objekt, ska ligga i for loopen.
	public Object[][] getOwner(int eventId){
		
		return null;
	}
}
