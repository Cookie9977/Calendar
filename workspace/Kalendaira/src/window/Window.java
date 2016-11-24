package window;

import javax.swing.JFrame;

public class Window extends JFrame{
	private static final long serialVersionUID = -7150710923108249953L;
	
	public Window(){
		// super
		super("Kalendarium");
		super.setResizable(false);
		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setBounds(0, 0, 400, 400);
	}
	
	private double getScreenHeight() {
		double y = 0;
		return y;
	}
	
	private double getScreenWidth() {
		double x = 0;
		return x;
	}
}
//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//double width = screenSize.getWidth();
//double height = screenSize.getHeight();