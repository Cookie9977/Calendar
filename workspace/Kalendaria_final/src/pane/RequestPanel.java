package pane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class RequestPanel extends JFrame implements ActionListener {
	private JLabel eventLabel, friendLabel;
	private JPanel eventLabelP, eventPanel, friendLabelP, friendPanel, topPanel, botPanel;
	private JScrollPane eventScroll, friendScroll;

	private static final long serialVersionUID = 4593819136988113328L;

	public RequestPanel() {
		// basic setup
		super("Förfrågningar");
		setResizable(true);
		setVisible(true);
		setLayout(new GridLayout(2, 1));
		// TODO ändra exit till hide efter testfas. samma med pack()
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// size och pos.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 600;
		int height = 800;
		int xStart = getXStart(screenSize, width);
		int yStart = getYStart(screenSize, height);
		setBounds(xStart, yStart, width, height);
		setPreferredSize(new Dimension(width, height));

		// stil
		setBackground(new Color(238, 238, 238));

		/*
		 * paneler
		 */
		// top panel, för de 2 övre elementen.
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(600, 400));
		add(topPanel, BorderLayout.NORTH);

		// Bot panel för de undre 2 elementen.
		botPanel = new JPanel();
		botPanel.setLayout(new BorderLayout());
		botPanel.setPreferredSize(new Dimension(600, 400));
		add(botPanel, BorderLayout.SOUTH);

		// Översta panelen som ska hålla label för events
		eventLabelP = new JPanel();
		eventLabelP.setLayout(new BorderLayout());
		eventLabelP.setPreferredSize(new Dimension(600, 50));
		eventLabelP.setBackground(Color.GREEN);
		topPanel.add(eventLabelP, BorderLayout.NORTH);

		// Eventpanelen.
		eventPanel = new JPanel();
		// FIXME fråga angående layout, är borderLayout rätt när
		// saker ska förändras, är det 1 eller 10 inbjudningar?
		eventPanel.setPreferredSize(new Dimension(600, 350));
		eventPanel.setLayout(new BorderLayout());
		eventPanel.setBackground(Color.YELLOW);
		// add(eventPanel);

		// Scrollpanel
		eventScroll = new JScrollPane();
		eventScroll.setViewportView(eventPanel);
		eventScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		eventScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// eventScroll.setPreferredSize(new Dimension(600, 350));
		eventScroll.getVerticalScrollBar().setUnitIncrement(10);
		topPanel.add(eventScroll, BorderLayout.SOUTH);

		// Label till vänner.
		friendLabelP = new JPanel();
		friendLabelP.setLayout(new BorderLayout());
		friendLabelP.setPreferredSize(new Dimension(600, 50));
		friendLabelP.setBackground(Color.RED);
		// friendLabelP.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
		botPanel.add(friendLabelP, BorderLayout.NORTH);

		// Vänpanelen.
		friendPanel = new JPanel();
		friendPanel.setLayout(new BorderLayout());
		friendPanel.setPreferredSize(new Dimension(600, 350));
		friendPanel.setBackground(Color.BLUE);

		// Scrollpanel 2 TODO
		friendScroll = new JScrollPane();
		friendScroll.setViewportView(friendPanel);
		friendScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		friendScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// friendScroll.setPreferredSize(new Dimension(600, 350));
		friendScroll.getVerticalScrollBar().setUnitIncrement(10);
		botPanel.add(friendScroll, BorderLayout.SOUTH);
		
		///FIXME den ska bort när testning är klar tillsammans med exit
		pack();

		/*
		 * System.out.println(topPanel); System.out.println(botPanel);
		 * System.out.println(eventLabelP); System.out.println(eventPanel);
		 * 
		 * System.out.println(eventScroll); System.out.println(friendLabelP);
		 * System.out.println(friendPanel); System.out.println(friendScroll);
		 */
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

	private int getXStart(Dimension screenSize, int width) {
		int y = (((int) Math.ceil(screenSize.getWidth()) - (width)) / 2);
		return y;
	}

	private int getYStart(Dimension screenSize, int height) {
		int x = (((int) Math.ceil(screenSize.getHeight()) - (height)) / 2);
		return x;
	}

}
