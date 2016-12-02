package window;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;

public class TextFields extends JTextField {
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