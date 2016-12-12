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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import main.Storage;

public class RequestPanel extends JFrame implements ActionListener {
	private JLabel eventLabel, friendLabel;
	private JPanel eventLabelP, eventPanel, friendLabelP, friendPanel, topPanel, botPanel;
	private JScrollPane eventScroll, friendScroll;
	private int reqLength, friendLength;
	private Font fontPlain, fontBold;
	protected Color Invisible = new Color(0, 0, 0, 0);

	private static final long serialVersionUID = 4593819136988113328L;

	public RequestPanel() {
		// basic setup
		super("F�rfr�gningar");
		setResizable(false);
		setVisible(true);
		setLayout(new GridLayout(2, 1));
		// TODO �ndra exit till hide efter testfas. samma med pack()
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
		Font fontPlain = new Font("SansSerif", Font.PLAIN, 18);
		Font fontBold = new Font("SansSerif", Font.BOLD, 20);

		/*
		 * Variabler som m�ste defineras tidigt
		 */
		friendLength = friendLength();
		reqLength = eventLength();

		System.out.println("req Length: " + reqLength);
		// JUMP
		reqLength += 300;
		System.out.println(reqLength + " justerad f�r testing");

		System.out.println("v�n l�ngd: " + friendLength);
		friendLength += 20;
		System.out.println(friendLength + " justerad f�r testning");

		// H�mtar ut storleken p� scroll rutan baserat p� antalet poster som
		// finns.
		int eventSize = eventSizing(reqLength);
		int friendSize = friendSizing(friendLength);

		/*
		 * paneler
		 */

		// top panel, f�r de 2 �vre elementen.
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		add(topPanel);

		// Bot panel f�r de undre 2 elementen.
		botPanel = new JPanel();
		botPanel.setLayout(new BorderLayout());
		add(botPanel);

		// Eventpanelen.
		eventPanel = new JPanel();
		eventPanel.setPreferredSize(new Dimension(600, eventSize));
		eventPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		eventPanel.setBackground(Color.YELLOW);
		// add(eventPanel);

		// Scrollpanel 1, events
		eventScroll = new JScrollPane(eventPanel);
		eventScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		eventScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		eventScroll.setPreferredSize(new Dimension(600, 350));
		eventScroll.getVerticalScrollBar().setUnitIncrement(10);
		topPanel.add(eventScroll, BorderLayout.CENTER);

		// �versta panelen som ska h�lla label f�r events
		eventLabelP = new JPanel();
		eventLabelP.setLayout(new BorderLayout());
		eventLabelP.setPreferredSize(new Dimension(600, 50));
		eventLabelP.setBackground(Color.GREEN);
		topPanel.add(eventLabelP, BorderLayout.NORTH);

		// V�npanelen.
		friendPanel = new JPanel();
		friendPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		friendPanel.setPreferredSize(new Dimension(600, friendSize));
		friendPanel.setBackground(Color.BLUE);

		// Scrollpanel 2, v�nner
		friendScroll = new JScrollPane(friendPanel);
		friendScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		friendScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		friendScroll.setPreferredSize(new Dimension(600, 350));
		friendScroll.getVerticalScrollBar().setUnitIncrement(10);
		botPanel.add(friendScroll, BorderLayout.CENTER);

		// Label till v�nner.
		friendLabelP = new JPanel();
		friendLabelP.setLayout(new BorderLayout());
		friendLabelP.setPreferredSize(new Dimension(600, 50));
		friendLabelP.setBackground(Color.gray);
		// friendLabelP.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
		botPanel.add(friendLabelP, BorderLayout.NORTH);

		/*
		 * Labels f�r label panelerna
		 */

		// eventlabel
		eventLabel = new JLabel();
		eventLabel.setText("Eventf�rfr�gningar (" + reqLength + ")");
		eventLabel.setFont(fontBold);
		eventLabelP.add(eventLabel);

		// Friendlabel
		friendLabel = new JLabel();
		friendLabel.setText("V�nf�rfr�gningar (" + friendLength + ")");
		friendLabel.setFont(fontBold);
		friendLabelP.add(friendLabel);

		/*
		 * eventf�rfr�gningarna.
		 */
		eventReq(reqLength);
		friendReq(friendLength);

		/// FIXME pack ska bort n�r testning �r klar tillsammans med exit

		pack();

		/*
		 * System.out.println(topPanel); System.out.println(botPanel);
		 * System.out.println(eventLabelP); System.out.println(eventPanel);
		 * 
		 * System.out.println(eventScroll); System.out.println(friendLabelP);
		 * System.out.println(friendPanel); System.out.println(friendScroll);
		 */
	}

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

	private void friendReq(int friendLength) {
		// Variabel deklareringar f�r v�nf�rfr�gnignar.
		if (friendLength > 0) {
			JPanel[] friendReqPanel = new JPanel[friendLength];
			JPanel[] buttonPanel = new JPanel[friendLength];
			JLabel[] userReqLabel = new JLabel[friendLength];
			JButton[] yesButton = new JButton[friendLength];
			JButton[] noButton = new JButton[friendLength];
			for (int i = 0; i < friendLength; i++) {
				// F�r�lder panel
				friendReqPanel[i] = new JPanel();
				friendReqPanel[i].setLayout(new BorderLayout());
				friendReqPanel[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				friendReqPanel[i].setPreferredSize(new Dimension(600, 50));

				// Panel d�r knapparna ska ligga i
				buttonPanel[i] = new JPanel();
				buttonPanel[i].setPreferredSize(new Dimension(250, 50));
				buttonPanel[i].setLayout(new GridLayout(1, 2));
				buttonPanel[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
				friendReqPanel[i].add(buttonPanel[i], BorderLayout.EAST);

				// Anv�ndaren som skickat f�rfr�gans email.
				userReqLabel[i] = new JLabel();
				userReqLabel[i].setPreferredSize(new Dimension(350, 50));
				userReqLabel[i].setText("thisisatesttotestthelengthoflongemailsand@livet.se");
				userReqLabel[i].setFont(fontPlain);
				friendReqPanel[i].add(userReqLabel[i], BorderLayout.WEST);

				// Ja knapp.
				yesButton[i] = new JButton();
				yesButton[i].setText("Acceptera");
				yesButton[i].addActionListener(this);
				buttonPanel[i].add(yesButton[i]);

				// Nej knapp
				noButton[i] = new JButton();
				noButton[i].setText("Neka");
				noButton[i].addActionListener(this);
				buttonPanel[i].add(noButton[i]);

				friendPanel.add(friendReqPanel[i]);
			}

		} else {
			JPanel sorryPanel = new JPanel();
			JLabel sorryLabel = new JLabel();

			sorryPanel.setBackground(Invisible);
			sorryPanel.setPreferredSize(new Dimension(600, 200));
			sorryPanel.setLayout(new BorderLayout());

			sorryLabel.setText(" Inga f�rfr�gningar hittades. V�nligen �terkom senare.");
			sorryLabel.setFont(new Font("SansSerif", Font.PLAIN, 22));
			sorryLabel.setOpaque(true);
			sorryPanel.add(sorryLabel, BorderLayout.CENTER);

			friendPanel.add(sorryPanel);

		}

	}

	// Eventf�rfr�gningarpanelen g�rs h�r.
	private void eventReq(int reqLength) {
		// Variabel deklareringar f�r eventf�rfr�gnignar.
		if (reqLength > 0) {
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
				eventReqPanel[i].setBackground(Color.red);
				eventReqPanel[i].setLayout(new GridLayout(2, 4));
				eventReqPanel[i].setPreferredSize(new Dimension(600, 100));
				eventReqPanel[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));

				// TODO Titeln, ska h�mtas fr�n databas
				titleReq[i] = new JLabel();
				titleReq[i].setText("Bygga pepparkakshus");
				titleReq[i].setFont(fontPlain);
				eventReqPanel[i].add(titleReq[i]);

				// person, ska h�mtas fr�n databas(typ allting ska s� inga mer
				// TODO f�r dom)
				personReq[i] = new JLabel();
				personReq[i].setText("thisisatesttotestthelengthoflongemailsand@livet.se");
				personReq[i].setFont(fontPlain);
				eventReqPanel[i].add(personReq[i]);

				// starttid.
				startReq[i] = new JLabel();
				startReq[i].setText("2016-12-09 11:30");
				startReq[i].setFont(fontPlain);
				eventReqPanel[i].add(startReq[i]);

				// sluttid.
				stopReq[i] = new JLabel();
				stopReq[i].setText("2016-12-09 11:30");
				stopReq[i].setFont(fontPlain);
				eventReqPanel[i].add(stopReq[i]);

				// Kategori.
				categoryReq[i] = new JLabel();
				categoryReq[i].setText("Fritid");
				categoryReq[i].setFont(fontPlain);
				eventReqPanel[i].add(categoryReq[i]);

				// "visa mer" knapp.
				showReq[i] = new JButton();
				showReq[i].setText("Visa mer");
				showReq[i].addActionListener(this);
				eventReqPanel[i].add(showReq[i]);

				// "Ja" knapp.
				yesReq[i] = new JButton();
				yesReq[i].setText("Ja");
				yesReq[i].addActionListener(this);
				eventReqPanel[i].add(yesReq[i]);

				// "nej" knapp
				noReq[i] = new JButton();
				noReq[i].setText("Nej");
				noReq[i].addActionListener(this);
				eventReqPanel[i].add(noReq[i]);

				eventPanel.add(eventReqPanel[i]);
				// System.out.println(eventReqPanel[i]);
			}
		} else {
			// visar upp en sak om att du inte har n�gra f�rfr�gningar
			JPanel sorryPanel = new JPanel();
			JLabel sorryLabel = new JLabel();

			sorryPanel.setBackground(Invisible);
			sorryPanel.setPreferredSize(new Dimension(600, 200));
			sorryPanel.setLayout(new BorderLayout());

			sorryLabel.setText(" Inga f�rfr�gningar hittades. V�nligen �terkom senare.");
			sorryLabel.setFont(new Font("SansSerif", Font.PLAIN, 22));
			sorryLabel.setOpaque(true);
			sorryPanel.add(sorryLabel, BorderLayout.CENTER);

			eventPanel.add(sorryPanel);
		}
	}

	public int eventLength() {
		int retval;
		String SQL = "SELECT COUNT(*) FROM event_link WHERE user_id = " + Storage.id + " and accepted = 0";
		retval = Integer.parseInt((String) Storage.db.getData(SQL)[0][0]);
		return retval;
	}

	public int friendLength() {
		int retval;
		String SQL = "SELECT COUNT(*) FROM friend_link WHERE reciver = " + Storage.id + " and accepted = 0";
		retval = Integer.parseInt((String) Storage.db.getData(SQL)[0][0]);
		System.out.println(retval);
		return retval;
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
