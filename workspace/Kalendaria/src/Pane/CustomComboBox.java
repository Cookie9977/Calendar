package pane;



import javax.swing.JComboBox;

public class CustomComboBox extends JComboBox<String>{
	private static final long serialVersionUID = -580013767804396255L;
	private int id;
	public CustomComboBox() {}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
