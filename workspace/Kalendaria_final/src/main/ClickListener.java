package main;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import logic.EventLogic;
import pane.EventPane;

public class ClickListener extends MouseAdapter implements ActionListener {
	private int clickInterval = (Integer) Toolkit.getDefaultToolkit().getDesktopProperty("awt.multiClickInterval");
	protected JPanel holder;
	protected static JPanel oldHolder;
	protected Timer timer;

	public ClickListener(JPanel holder) {
		this.holder = holder;
		timer = new Timer(clickInterval, this);
	}
	//Den här ska kopieras över till överskrivningen för att kunna skriva över färg i deselect
	public void mouseReleased(MouseEvent e) {
		if (oldHolder != null && oldHolder != holder) {
			deSelect();
		}
		if (timer.isRunning()) {
			DoubleClick();
			timer.stop();
		} else {
			singleClick();
			timer.restart();
		}
		oldHolder = holder;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.stop();
	}

	public void singleClick() {
		holder.setBackground(new Color(216, 229, 233));
		holder.validate();
		holder.repaint();
	}

	public void DoubleClick() {
		EventLogic logic = new EventLogic();
		EventPane eventPane = new EventPane();
		int test = JOptionPane.showConfirmDialog(null, eventPane, "test", JOptionPane.OK_CANCEL_OPTION);
		if (test == JOptionPane.OK_OPTION) {
			logic.checkEvent(eventPane);
		}
	}
	//För annan deselect färg, ta med den här och byt ut färgen.
	public void deSelect() {
		oldHolder.setBackground(new Color(238, 238, 238));
		holder.validate();
		holder.repaint();
	}
}
