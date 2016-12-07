package event;

public class Event {
	private int id, owner;
	private String title, description, place, timeStart, timeEnd, category;

	public Event(Object[] event) {
		this.id = Integer.parseInt((String) event[0]);
		this.title = (String) event[1];
		this.description = (String) event[2];
		this.place = (String) event[3];
		this.timeStart = (String) event[4];
		this.timeEnd = (String) event[5];
		this.category = (String) event[6];
		this.owner = Integer.parseInt((String) event[7]);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}
	
	public String toString(){
		String event = getId()+" "+getTitle()+" "+getCategory();
		return event;
	}
}
