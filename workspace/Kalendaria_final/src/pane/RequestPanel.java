package pane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import logic.RequestLogic;
import main.Storage;
import window.Window;

public class RequestPanel extends JFrame implements ActionListener {
	private JLabel eventLabel, friendLabel;
	private JPanel eventLabelP, eventPanel, friendLabelP, friendPanel, topPanel, botPanel;
	private JScrollPane eventScroll, friendScroll;
	private int reqLength, friendLength;
	private Font fontPlain, fontBold;
	private RequestLogic reqLogic;
	private Window window;
	protected Color Invisible = new Color(0, 0, 0, 0);

	private static final long serialVersionUID = 4593819136988113328L;

	public RequestPanel(Window windowVal) {
		/*
		 * basic setup
		 */
		super("Förfrågningar");
		setResizable(false);
		setVisible(true);
		setLayout(new GridLayout(2, 1));
		setDefaultCloseOperation(HIDE_ON_CLOSE);

		// size och pos.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 600;
		int height = 800;
		int xStart = getXStart(screenSize, width);
		int yStart = getYStart(screenSize, height);
		setBounds(xStart, yStart, width, height);
		setPreferredSize(new Dimension(width, height));

		// stil + RequestLogic intialisering
		setBackground(new Color(238, 238, 238));
		fontPlain = new Font("SansSerif", Font.PLAIN, 18);
		fontBold = new Font("SansSerif", Font.BOLD, 20);
		reqLogic = new RequestLogic();

		/*
		 * Variabler som måste defineras tidigt
		 */

		this.window = windowVal;
		friendLength = reqLogic.friendLength();
		reqLength = reqLogic.eventLength();
		// JUMP

		// Hämtar ut storleken på scroll rutan baserat på antalet poster som
		// finns.
		int eventSize = eventSizing(reqLength);
		int friendSize = friendSizing(friendLength);

		/*
		 * paneler
		 */

		// top panel, för de 2 övre elementen.
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		add(topPanel);

		// Bot panel för de undre 2 elementen.
		botPanel = new JPanel();
		botPanel.setLayout(new BorderLayout());
		add(botPanel);

		// Eventpanelen.
		eventPanel = new JPanel();
		eventPanel.setPreferredSize(new Dimension(600, eventSize));
		eventPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		eventPanel.setBackground(Invisible);
		// add(eventPanel);

		// Scrollpanel 1, events
		eventScroll = new JScrollPane(eventPanel);
		eventScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		eventScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		eventScroll.setPreferredSize(new Dimension(600, 350));
		eventScroll.getVerticalScrollBar().setUnitIncrement(10);
		topPanel.add(eventScroll, BorderLayout.CENTER);

		// Översta panelen som ska hålla label för events
		eventLabelP = new JPanel();
		eventLabelP.setLayout(new BorderLayout());
		eventLabelP.setPreferredSize(new Dimension(600, 50));
		eventLabelP.setBackground(new Color(72, 90, 234, 255));
		topPanel.add(eventLabelP, BorderLayout.NORTH);

		// Vänpanelen.
		friendPanel = new JPanel();
		friendPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		friendPanel.setPreferredSize(new Dimension(600, friendSize));
		friendPanel.setBackground(Invisible);

		// Scrollpanel 2, vänner
		friendScroll = new JScrollPane(friendPanel);
		friendScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		friendScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		friendScroll.setPreferredSize(new Dimension(600, 350));
		friendScroll.getVerticalScrollBar().setUnitIncrement(10);
		botPanel.add(friendScroll, BorderLayout.CENTER);

		// Label till vänner.
		friendLabelP = new JPanel();
		friendLabelP.setLayout(new BorderLayout());
		friendLabelP.setPreferredSize(new Dimension(600, 50));
		friendLabelP.setBackground(new Color(72, 90, 234, 255));
		// friendLabelP.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
		botPanel.add(friendLabelP, BorderLayout.NORTH);

		/*
		 * Labels för label panelerna
		 */

		// eventlabel
		eventLabel = new JLabel();
		eventLabel.setText("Eventförfrågningar (" + reqLength + ")");
		eventLabel.setFont(fontBold);
		eventLabelP.add(eventLabel);

		// Friendlabel
		friendLabel = new JLabel();
		friendLabel.setText("Vänförfrågningar (" + friendLength + ")");
		friendLabel.setFont(fontBold);
		friendLabelP.add(friendLabel);

		/*
		 * eventförfrågningarna.
		 */
		eventReq(reqLength);
		friendReq(friendLength);

		pack();
	}

	// Returnerar en storlek för vänfönstret, 55 * friendLength eller 320 min
	private int friendSizing(int friendLength) {
		int friendHeight = (friendLength * 55);
		if (friendHeight >= 300) {
			// System.out.println(friendHeight + "if");
			return friendHeight;
		} else {
			friendHeight = 320;
			// System.out.println(friendHeight + "else");
			return friendHeight;
		}
	}

	// Returnerar en storlek för eventFönstret, 105 * reqLength eller 320 min
	private int eventSizing(int reqLength) {
		int eventHeight = (reqLength * 105);
		if (eventHeight >= 300) {
			// System.out.println(eventHeight + "if");
			return eventHeight;
		} else {
			eventHeight = 320;
			// System.out.println(eventHeight + "else");
			return eventHeight;
		}
	}

	// Loopar igenom och skapar alla paneler med vänförfrågnignar
	private void friendReq(int friendLength) {
		// Variabel deklareringar för vänförfrågnignar.
		if (friendLength > 0) {
			Object[][] data = reqLogic.getFriend();
			JPanel[] friendReqPanel = new JPanel[friendLength];
			JPanel[] buttonPanel = new JPanel[friendLength];
			JLabel[] userReqLabel = new JLabel[friendLength];
			JButton[] yesButton = new JButton[friendLength];
			JButton[] noButton = new JButton[friendLength];
			for (int i = 0; i < friendLength; i++) {
				// Förälder panel
				friendReqPanel[i] = new JPanel();
				friendReqPanel[i].setLayout(new BorderLayout());
				friendReqPanel[i].setBorder(BorderFactory.createLineBorder(Color.black));
				friendReqPanel[i].setBackground(new Color(72, 90, 234, 255));
				friendReqPanel[i].setPreferredSize(new Dimension(600, 50));

				// Panel där knapparna ska ligga i
				buttonPanel[i] = new JPanel();
				buttonPanel[i].setPreferredSize(new Dimension(250, 50));
				buttonPanel[i].setLayout(new GridLayout(1, 2));
				buttonPanel[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
				friendReqPanel[i].add(buttonPanel[i], BorderLayout.EAST);

				// Användaren som skickat förfrågans email.
				userReqLabel[i] = new JLabel();
				userReqLabel[i].setPreferredSize(new Dimension(350, 50));
				userReqLabel[i].setText(" "+(String) data[i][1]);
				userReqLabel[i].setFont(fontPlain);
				friendReqPanel[i].add(userReqLabel[i], BorderLayout.WEST);

				// Ja knapp.
				yesButton[i] = new JButton();
				yesButton[i].setText("Acceptera");
				yesButton[i].setActionCommand((String) data[i][0]);
				yesButton[i].addActionListener(this);
				buttonPanel[i].add(yesButton[i]);

				// Nej knapp
				noButton[i] = new JButton();
				noButton[i].setText("Neka");
				noButton[i].setActionCommand((String) data[i][0]);
				noButton[i].addActionListener(this);
				buttonPanel[i].add(noButton[i]);

				friendPanel.add(friendReqPanel[i]);
				// JUMP friendpanel ending
			}

		} else {
			JPanel sorryPanel = new JPanel();
			JLabel sorryLabel = new JLabel();

			sorryPanel.setBackground(Invisible);
			sorryPanel.setPreferredSize(new Dimension(600, 350));
			sorryPanel.setLayout(new BorderLayout());

			sorryLabel.setText(" Inga förfrågningar hittades. Vänligen återkom senare.");
			sorryLabel.setFont(new Font("SansSerif", Font.PLAIN, 22));
			sorryLabel.setOpaque(true);
			sorryPanel.add(sorryLabel, BorderLayout.CENTER);

			friendPanel.add(sorryPanel);

		}

	}

	// Loopar ingeom och skapar alla paneler för eventförfrågnignar.
	private void eventReq(int reqLength) {
		// Variabel deklareringar för eventförfrågnignar.
		if (reqLength > 0) {
			Object[][] data = reqLogic.getEvent();
			JPanel[] eventReqPanel = new JPanel[reqLength];
			JLabel[] titleReq = new JLabel[reqLength];
			JLabel[] personReq = new JLabel[reqLength];
			JLabel[] startReq = new JLabel[reqLength];
			JLabel[] stopReq = new JLabel[reqLength];
			JLabel[] categoryReq = new JLabel[reqLength];
			JButton[] showReq = new JButton[reqLength];
			JButton[] yesReq = new JButton[reqLength];
			JButton[] noReq = new JButton[reqLength];

			for (int i = 0; i < reqLength; i++) {
				// Panelen
				// System.out.println("i: " + i + " req: " + reqLength);
				eventReqPanel[i] = new JPanel();
				eventReqPanel[i].setBackground(new Color(72, 90, 234, 255));
				eventReqPanel[i].setLayout(new GridLayout(2, 4));
				eventReqPanel[i].setPreferredSize(new Dimension(600, 100));
				eventReqPanel[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));

				// Titel
				titleReq[i] = new JLabel();
				titleReq[i].setText((String) data[i][1]);
				titleReq[i].setFont(fontPlain);
				eventReqPanel[i].add(titleReq[i]);

				// Hämta variabel för personen
				// System.out.println("person: "+data[i][0]);
				Object[][] person = reqLogic.getOwner(Integer.parseInt((String) data[i][0]));

				// person,
				personReq[i] = new JLabel();
				personReq[i].setText((String) person[0][0]);
				personReq[i].setFont(fontPlain);
				eventReqPanel[i].add(personReq[i]);

				// starttid.
				startReq[i] = new JLabel();
				startReq[i].setText((String) data[i][2]);
				startReq[i].setFont(fontPlain);
				eventReqPanel[i].add(startReq[i]);

				// sluttid.
				stopReq[i] = new JLabel();
				stopReq[i].setText((String) data[i][3]);
				stopReq[i].setFont(fontPlain);
				eventReqPanel[i].add(stopReq[i]);

				// Kategori.
				categoryReq[i] = new JLabel();
				categoryReq[i].setText((String) data[i][4]);
				categoryReq[i].setFont(fontPlain);
				eventReqPanel[i].add(categoryReq[i]);

				// "visa mer" knapp.
				showReq[i] = new JButton();
				showReq[i].setText("Visa mer");
				showReq[i].setActionCommand((String) data[i][0]);
				showReq[i].addActionListener(this);
				eventReqPanel[i].add(showReq[i]);

				// "Ja" knapp.
				yesReq[i] = new JButton();
				yesReq[i].setText("Ja");
				yesReq[i].setActionCommand((String) data[i][0]);
				yesReq[i].addActionListener(this);
				eventReqPanel[i].add(yesReq[i]);

				// "nej" knapp
				noReq[i] = new JButton();
				noReq[i].setText("Nej");
				noReq[i].setActionCommand((String) data[i][0]);
				noReq[i].addActionListener(this);
				eventReqPanel[i].add(noReq[i]);

				eventPanel.add(eventReqPanel[i]);
				// System.out.println(eventReqPanel[i]);
				// JUMP event panel ending
			}
		} else {
			// visar upp en sak om att du inte har några förfrågningar
			JPanel sorryPanel = new JPanel();
			JLabel sorryLabel = new JLabel();

			sorryPanel.setBackground(Invisible);
			sorryPanel.setPreferredSize(new Dimension(600, 350));
			sorryPanel.setLayout(new BorderLayout());

			sorryLabel.setText(" Inga förfrågningar hittades. Vänligen återkom senare.");
			sorryLabel.setFont(new Font("SansSerif", Font.PLAIN, 22));
			sorryPanel.add(sorryLabel, BorderLayout.CENTER);

			eventPanel.add(sorryPanel);
		}
	}
	
	// Actionperformed, autogenerated
	@Override
	public void actionPerformed(ActionEvent ae) {
		String bText = ((AbstractButton) ae.getSource()).getText();
		int reqId;
		switch (bText) {
		// Visa mer info om event.
		case "Visa mer":
			reqId = Integer.parseInt(ae.getActionCommand());
			reqLogic.showMore(reqId);
			break;
		// Tacka ja till att delta i event.
		case "Ja":
			reqId = Integer.parseInt(ae.getActionCommand());
			reqLogic.acceptEvent(reqId, this, window);
			break;
		// Tacka nej till att delta i event.
		case "Nej":
			reqId = Integer.parseInt(ae.getActionCommand());
			reqLogic.denyevent(reqId, this, window);
			break;
		// Acceptera vänförfrågan.
		case "Acceptera":
			reqId = Integer.parseInt(ae.getActionCommand());
			reqLogic.acceptFriend(reqId, this, window);
			System.out.println(this);
			break;
		// Neka vänförfrågan.
		case "Neka":
			reqId = Integer.parseInt(ae.getActionCommand());
			reqLogic.denyFriend(reqId, this, window);
			break;
		}

	}

	// Räknar ut en startposition i x-led, center
	private int getXStart(Dimension screenSize, int width) {
		int y = (((int) Math.ceil(screenSize.getWidth()) - (width)) / 2);
		return y;
	}

	// Räknar ut en startposition i y-led, center
	private int getYStart(Dimension screenSize, int height) {
		int x = (((int) Math.ceil(screenSize.getHeight()) - (height)) / 2);
		return x;
	}

}
