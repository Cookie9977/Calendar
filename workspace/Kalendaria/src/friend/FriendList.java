package friend;

import javax.swing.DefaultComboBoxModel;

import main.Main;

public class FriendList extends DefaultComboBoxModel<Friend>{
	private static final long serialVersionUID = -4953539867820044887L;
	
	public FriendList(){
		String SQL = "SELECT user.id, user.username, user.email  FROM friend_link LEFT JOIN user ON friend_link.reciver = user.id WHERE friend_link.accepted = 1 AND friend_link.requester = "+Main.id;
		Object[][] data = Main.db.getData(SQL);
		for (int i = 0; i < data.length; i++) {
			this.addElement(new Friend(data[i]));
		}
	}
	
}
