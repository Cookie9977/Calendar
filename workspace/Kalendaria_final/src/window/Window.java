package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import pane.AddButtonsPane;
import pane.DatePanelDate;

public class Window extends JFrame {
	private static final long serialVersionUID = -7150710923108249953L;
	// byt ut dessa mot respective jpanel classer som ni har gjort. Body ska
	// vara kvar.
	private NavArrowButton leftArrowButton, rightArrowButton;
	private LoginUser loginUser;
	protected AddButtonsPane addButtons;
	protected RegisterUser registerUser;
	protected JPanel body, menyBar, navBar, datePanel, topLine, westLine, centerBlock, cTop, cContent, cWest, cEast,
			cSouth, westBorder, eastBorder;
	protected MenuNavBar monthButton, weekButton, dayButton;
	protected WindowModifications windowmodifications;
	protected MonthView monthView;
	protected DayView dayView;
	protected WeekView weekView;
	protected DatePanelDate datePanelDate;
	protected JLabel view;
	protected UpcomingEvent upcomingEvent;
	public Color invis = new Color(0, 0, 0, 0);
	// private JPanel[] menyBarHolders;
	// private JLabel calendarLabel, menyBarLabel, navBarLabel, datePanelLabel;

	public Window() {
		// basic setup
		super("Kalendarium");
		lookAndFeel();
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// declaring parts
		body = new JPanel();
		topLine = new JPanel();
		westLine = new JPanel();
		centerBlock = new JPanel();
		cTop = new JPanel();
		cContent = new JPanel();
		cWest = new JPanel();
		cEast = new JPanel();
		cSouth = new JPanel();
		menyBar = new JPanel();
		westBorder = new JPanel();
		eastBorder = new JPanel();
		navBar = new JPanel();
		datePanel = new JPanel();
		datePanelDate = new DatePanelDate();
		monthView = new MonthView();
		weekView = new WeekView();
		dayView = new DayView();
		addButtons = new AddButtonsPane();
		// FIXME byt färg och sånt, gör den fin. ser hemskt ut just nu.
		view = new JLabel("Månad");
		view.setFont(new Font("SansSerif", Font.BOLD, 20));
		view.setForeground(Color.YELLOW);
		windowmodifications = new WindowModifications(monthView, weekView, dayView, this);

		// menyBarHolders = new JPanel[3];

		// window size and place
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 1125; // 1125
		int height = 900; // 900
		int xStart = getXStart(screenSize, width);
		int yStart = getYStart(screenSize, height);
		setBounds(xStart, yStart, width, height);
		int gridWidth = (width / 4);
		int gridHeight = (height / 10);

		// giving window sizes and layout
		setPreferredSize(new Dimension(width, height));
		setLayout(new BorderLayout());

		// components color
		menyBar.setBackground(invis);
		navBar.setBackground(Color.BLUE);
		datePanel.setBackground(Color.GREEN);

		// window grid
		// GridBagConstraints gbc = new GridBagConstraints();
		body.setPreferredSize(new Dimension(width, height));
		body.setLayout(new BorderLayout());
		add(body);

		// TopLine för allt som ska vara på samma linje i norra delen
		topLine.setPreferredSize(new Dimension(width, gridHeight));
		topLine.setBackground(invis);
		topLine.setLayout(new BorderLayout());
		body.add(topLine, BorderLayout.NORTH);

		// Westline, Vänsterkolumnen för allt som ska ligga i den raden.
		westLine.setPreferredSize(new Dimension(gridWidth, (height - gridHeight)));
		westLine.setBackground(new Color(155, 155, 155, 155));
		westLine.setLayout(new BorderLayout());
		body.add(westLine, BorderLayout.WEST);

		/*
		 * centerBlock, Center blocket i det sydvästra delen av fönstret.
		 * Content panelen där kalender vyer kommer att finnas.
		 */
		centerBlock.setPreferredSize(new Dimension((width - gridWidth), (height - gridHeight)));
		centerBlock.setBackground(new Color(255, 0, 255));
		centerBlock.setLayout(new BorderLayout());
		body.add(centerBlock, BorderLayout.CENTER);

		// Top höger där dagens datum kommer att stå. finns i topLine.
		datePanel.setPreferredSize(new Dimension(gridWidth, gridHeight));
		datePanel.setBackground(new Color(238, 231, 56));
		datePanel.setLayout(new GridLayout(1, 1));
		topLine.add(datePanel, BorderLayout.WEST);

		// NavBar, där navigering mellan vyer sker. finns i topLine.
		navBar.setPreferredSize(new Dimension((width - gridWidth), gridHeight));
		navBar.setBackground(new Color(66, 244, 229));
		navBar.setLayout(new GridLayout(1, 3, 0, 0));
		topLine.add(navBar, BorderLayout.CENTER);

		/*
		 * MenyBar, sidopanelen där funktionalitet kommer finnas t.ex. logga in
		 * och ut och saker som lägg till vänner o vänlista, lite beroende på
		 * inloggningstatusen.
		 */
		menyBar.setPreferredSize(new Dimension(gridWidth, (height - gridHeight)));
		westBorder.setPreferredSize(new Dimension(20,(height - gridHeight)));
		eastBorder.setPreferredSize(new Dimension(20,(height - gridHeight)));
		menyBar.setBackground(new Color(66, 86, 244, 255));
		menyBar.setLayout(new GridLayout(5, 1));
		westLine.add(westBorder, BorderLayout.WEST);
		westLine.add(eastBorder, BorderLayout.EAST);
		westLine.add(menyBar, BorderLayout.CENTER);
		
		//Dagens datum.
		datePanel.add(datePanelDate);

		// Holders till navbar knapparna
		JPanel[] navBarHolders = new JPanel[3];
		for (int i = 0; i < 3; i++) {
			navBarHolders[i] = new JPanel();
			navBarHolders[i].setLayout(new BorderLayout());
			navBarHolders[i].setBackground(invis);
			navBar.add(navBarHolders[i]);
		}

		// Navbar knapparna läggs in i olika holders.
		monthButton = new MenuNavBar(this, 0);
		monthButton.setBackground(invis);
		navBarHolders[0].add(monthButton, BorderLayout.CENTER);
		weekButton = new MenuNavBar(this, 1);
		weekButton.setBackground(invis);
		navBarHolders[1].add(weekButton, BorderLayout.CENTER);
		dayButton = new MenuNavBar(this, 2);
		dayButton.setBackground(invis);
		navBarHolders[2].add(dayButton, BorderLayout.CENTER);

		//Registreringen i menybar
		registerUser = new RegisterUser();
		loginUser = new LoginUser(this);
		upcomingEvent = new UpcomingEvent();
		menyBar.add(loginUser);
		menyBar.add(registerUser);
		//menyBar.add(upcomingEvent);
		/*
		 * Padding rutor i centerBlock
		 */

		// Center blockets top, ska inehålla view och nav pilar.
		cTop.setPreferredSize(new Dimension((width - gridWidth), gridHeight / 2));
		cTop.setBackground(Color.gray);

		// Center blockets västra del, ska vara padding i samma bredd som
		// navknappavståndet
		cWest.setPreferredSize(new Dimension((gridWidth / 8), (height - (gridHeight) * 2)));
		cWest.setBackground(Color.DARK_GRAY);

		// Center blockets östra del, ska vara padding i samma bredd som
		// navknappavståndet
		cEast.setPreferredSize(new Dimension((gridWidth / 8), (height - (gridHeight) * 2)));
		cEast.setBackground(Color.DARK_GRAY);

		// Center blockets södra del, ska vara padding i botten
		cSouth.setPreferredSize(new Dimension((width - (gridWidth * 2)), gridHeight / 2));
		cSouth.setBackground(Color.GRAY);

		// Center blockets Content del, här ska kalendrar visas upp.
		
		//XXX width är 772 och height 720 på cContent så skala era kalendrar efter den.
		cContent.setPreferredSize(new Dimension((int) (gridWidth * 2.75), (gridHeight * 8)));
		cContent.setBackground(Color.blue);
		
		//Lägg in blocken
		centerBlock.add(cTop, BorderLayout.NORTH);
		centerBlock.add(cWest, BorderLayout.WEST);
		centerBlock.add(cEast, BorderLayout.EAST);
		centerBlock.add(cSouth, BorderLayout.SOUTH);
		centerBlock.add(cContent, BorderLayout.CENTER);
		
		//Vänster pilen i top vyn, följt av visa vilken kalender vi har sen högerknappen.
		leftArrowButton = new NavArrowButton(this, 0);
		leftArrowButton.setBackground(invis);
		cTop.add(leftArrowButton);
		
		cTop.add(view);

		rightArrowButton = new NavArrowButton(this, 1);
		rightArrowButton.setBackground(invis);
		cTop.add(rightArrowButton);
		
		//default content view.
		cContent.add(monthView);
		// cContent.add(weekView);
		// cContent.add(dayView);
		

		pack();
	}

	public WindowModifications getModifications() {
		return windowmodifications;
	}

	public void lookAndFeel() {
		try {
			// Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}
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
