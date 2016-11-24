package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window extends JFrame{
	private static final long serialVersionUID = -7150710923108249953L;
	private JPanel body, calendar, menyBar, navBar, datePanel;
	//private JLabel calendarLabel, menyBarLabel, navBarLabel, datePanelLabel;
	
	public Window(){
		// basic setup
		super("Kalendarium");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//declaring parts
		body = new JPanel();
		calendar = new JPanel();
		menyBar = new JPanel();
		navBar = new JPanel();
		datePanel = new JPanel();

		//window size and place
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 600;
		int height = 500;
		int xStart = getXStart(screenSize, width);
		int yStart = getYStart(screenSize, height);
		super.setBounds(xStart, yStart, width, height);
		
		//components color
		calendar.setBackground(Color.BLACK);
		menyBar.setBackground(Color.RED);
		navBar.setBackground(Color.BLUE);
		datePanel.setBackground(Color.GREEN);
		body.setBackground(Color.YELLOW);
		
		
		//window grid
		GridBagLayout gridBag = new GridBagLayout(); 
		GridBagConstraints gbc = new GridBagConstraints();
		body.setLayout(gridBag);
		
		
		//adding parts
		add(body);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = (width/3);
        gbc.gridheight = (height/10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.3;
        gbc.weighty = 0.1;
        //gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        body.add(datePanel, gbc);
       	
        gbc.gridx = ((width)/3);
        gbc.gridy = 0;
        gbc.gridwidth = ((2*width)/3);
        gbc.gridheight = (height/10);
        gbc.weightx = 0.7;
        gbc.weighty = 0.1;
        //gbc.anchor = GridBagConstraints.PAGE_START;
       	body.add(menyBar, gbc);
        
       	gbc.gridx = 0;
        gbc.gridy = (height/10);
        gbc.gridwidth = ((width)/3);
        gbc.gridheight = ((9*height)/10);
        gbc.weightx = 0.3;
        gbc.weighty = 0.9;
        //gbc.anchor = GridBagConstraints.LINE_START;
        body.add(navBar, gbc);
        
        gbc.gridx = ((width)/3);
        gbc.gridy = (height/10);
        gbc.gridwidth = ((2*width)/3);
        gbc.gridheight = ((9*height)/10);
        gbc.weightx = 0.7;
        gbc.weighty = 0.9;
        body.add(calendar, gbc);
		//pack();
		
		
		
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
