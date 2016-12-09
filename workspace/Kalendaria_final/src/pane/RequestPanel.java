package pane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

public class RequestPanel extends JFrame implements ActionListener {
	private JLabel eventLabel, friendLabel;
	private JPanel eventLabelP, eventPanel, friendLabelP, friendPanel, topPanel, botPanel;
	private JScrollPane eventScroll, friendScroll;
	private int reqLength;
	private Font fontPlain, fontBold;
	protected Color Invisible = new Color(0, 0, 0, 0);

	private static final long serialVersionUID = 4593819136988113328L;

	public RequestPanel() {
		// basic setup
		super("Förfrågningar");
		setResizable(false);
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
		Font fontPlain = new Font("SansSerif", Font.PLAIN, 18);
		Font fontBold = new Font("SansSerif", Font.BOLD, 20);
		
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
		eventPanel.setLayout(new GridLayout());
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
		
		/*
		 * Labels för label panelerna
		 */ 
		
		//eventlabel
		eventLabel = new JLabel();
		eventLabel.setText("Eventförfrågningar");
		eventLabel.setFont(fontBold);
		eventLabelP.add(eventLabel);
		
		//Friendlabel
		friendLabel = new JLabel();
		friendLabel.setText("Vänförfrågningar");
		friendLabel.setFont(fontBold);
		friendLabelP.add(friendLabel);
		
		/*
		 * eventförfrågningarna.
		 */
		eventReq();
		
		
		
		
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
	
	// Eventförfrågningarpanelen görs här.
	private void eventReq() {

		// Variabel deklareringar för eventförfrågnignar.
		reqLength = 0;
		/*
		 * FIXME reqLength vara lika med en funktion som kollar antalet
		 * förfrågnignar som finns för användare och retunerar dem som en int.
		 */
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
				eventReqPanel[i] = new JPanel();
				eventReqPanel[i].setBackground(Invisible);
				eventReqPanel[i].setLayout(new GridLayout(2, 4));
				eventReqPanel[i].setPreferredSize(new Dimension(600, 200));
				eventReqPanel[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));

				// Titeln, ska hämtas från databas TODO
				titleReq[i] = new JLabel();
				titleReq[i].setText("Bygga pepparkakshus");
				titleReq[i].setFont(fontPlain);
				eventReqPanel[i].add(titleReq[i]);

				// person, ska hämtas från databas(typ allting ska så inga mer
				// TODO för dom)
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
			}
		}
		else{
			//visar upp en sak om att du inte har några förfrågningar
			JPanel sorryPanel = new JPanel();
			JLabel sorryLabel = new JLabel();
			
			sorryPanel.setBackground(Invisible);
			sorryPanel.setPreferredSize(new Dimension(600,200));
			sorryPanel.setLayout(new BorderLayout());
			
			sorryLabel.setText(" Inga förfrågningar hittades. Vänligen återkom senare.");
			sorryLabel.setFont(new Font("SansSerif",Font.PLAIN,24));
			sorryLabel.setOpaque(true);
			sorryPanel.add(sorryLabel, BorderLayout.CENTER);
			
			eventPanel.add(sorryPanel);
			//TODO en "sorry ingen gillar dig grej"
		}
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
