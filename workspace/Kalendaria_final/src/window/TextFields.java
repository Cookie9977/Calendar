package window;

import java.awt.Font;

import javax.swing.JTextField;

public class TextFields extends JTextField {
	private static final long serialVersionUID = 9197347533364164232L;
	public Font font;

	public TextFields(Font font) {
		this.font = font;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
}