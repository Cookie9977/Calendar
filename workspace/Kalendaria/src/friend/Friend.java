package friend;

public class Friend {
	public int id;
	public String username, email;
	public Friend(Object[] data){
		this.id = Integer.parseInt((String) data[0]);
		this.username = (String) data[1];
		this.email = (String) data[2];
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString(){
		String str = getUsername()+", "+getEmail();
		return str;
	}

}
