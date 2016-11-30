package Pane;



import javax.swing.JComboBox;

public class DateComboBox extends JComboBox<String>{
	private static final long serialVersionUID = -580013767804396255L;
	private int id;
	public DateComboBox() {}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
