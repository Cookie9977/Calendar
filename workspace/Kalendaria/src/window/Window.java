package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
	private static final long serialVersionUID = -7150710923108249953L;
	//byt ut dessa mot respective jpanel classer som ni har gjort. Body ska vara kvar.
	private JPanel body, calendar, menyBar, navBar, datePanel;
	private registerUser registerUser;
	private loginUser loginUser;
	//private JPanel[] menyBarHolders;
	//private JLabel calendarLabel, menyBarLabel, navBarLabel, datePanelLabel;
	
	public Window(){
		// basic setup
		super("Kalendarium");
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//declaring parts
		body = new JPanel();
		calendar = new JPanel();
		menyBar = new JPanel();
		navBar = new JPanel();
		datePanel = new JPanel();
		//menyBarHolders = new JPanel[3];


		//window size and place
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 1000;
		int height = 800;
		int xStart = getXStart(screenSize, width);
		int yStart = getYStart(screenSize, height);
		setBounds(xStart, yStart, width, height);
		setPreferredSize(new Dimension(width, height));
		
		//components color
		calendar.setBackground(Color.BLACK);
		menyBar.setBackground(Color.RED);
		navBar.setBackground(Color.BLUE);
		datePanel.setBackground(Color.GREEN);
		
		
		//window grid
		GridBagLayout gridBag = new GridBagLayout(); 
		GridBagConstraints gbc = new GridBagConstraints();
		body.setLayout(gridBag);
		navBar.setLayout(gridBag);
		menyBar.setLayout(gridBag);
		int gridWith = (width/4);
		int gridHeight = (height/10);
		
		//adding parts
		add(body);
		
        gbc.fill = GridBagConstraints.BOTH;
        
        gbc.gridwidth = gridWith;
        gbc.gridheight = gridHeight;
        gbc.weightx = 0.25;
        gbc.weighty = 0.1;
        body.add(datePanel, gbc);
       	
        gbc.gridx = gridWith;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 0.75;
       	body.add(menyBar, gbc);
        
       	gbc.gridx = 0;
        gbc.gridy = gridHeight;
        gbc.gridwidth = gridWith;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.weightx = 0.25;
        gbc.weighty = 0.9;
        body.add(navBar, gbc);
        
        gbc.gridx = gridWith;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        body.add(calendar, gbc);
        /* försök för layout på menybaren
        gbc = new GridBagConstraints();
        int margin = 20;
        int startPos = (width-gridWith+margin);
        int Buttonwidth = ((width*2)/10);  
		gbc.gridy = gridHeight;
		gbc.gridwidth = Buttonwidth;
		gbc.gridheight = ((width)/20);
		//gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(((width)/20)0, 0left, 0bottom, 0right);
		gbc.weightx = 0.1;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.SOUTH;
        for (int i = 0; i < menyBarHolders.length; i++) {
			menyBarHolders[i] = new JPanel();
			menyBarHolders[i].setBackground(Color.YELLOW);
			gbc.gridx = startPos+((margin+Buttonwidth)*i);

			menyBar.add(menyBarHolders[i], gbc);
		}
        */
       	registerUser = new registerUser();
        calendar.add(registerUser);
       loginUser = new loginUser();
       navBar.add(loginUser);
        pack();
	}
	
	private int getXStart(Dimension screenSize,int width) {
		int y = (((int) Math.ceil(screenSize.getWidth())-(width))/2);
		return y;
	}
	
	private int getYStart(Dimension screenSize, int height) {
		int x = (((int) Math.ceil(screenSize.getHeight())-(height))/2);
		return x;
	}

}
