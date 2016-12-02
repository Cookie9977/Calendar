package pane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;

import friend.Friend;
import friend.FriendList;

public class FriendPane extends JPanel {
	private FriendList friendList;
	private JList<Friend> list;
	public int selected;
	private static final long serialVersionUID = 509271110346269191L;

	public FriendPane() {

		friendList = new FriendList();
		list = new JList<Friend>();
		list.setModel(friendList);

		list.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
		list.setPreferredSize(new Dimension(200, 200));
		list.setAutoscrolls(true);
		list.addMouseListener(new Mousehandler());
		for (int i = 0; i < friendList.getSize(); i++) {
		}

		add(list);

	}
	
	public FriendPane(boolean unimportant){
		friendList = new FriendList();
		list = new JList<Friend>();
		list.setModel(friendList);

		list.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
		list.setPreferredSize(new Dimension(200, 200));
		list.setAutoscrolls(true);
		add(list);
	}

	class Mousehandler extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			selected = list.getSelectedValue().getId();
		}
	}
}
