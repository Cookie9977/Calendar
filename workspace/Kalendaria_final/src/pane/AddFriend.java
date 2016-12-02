package pane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddFriend extends JFrame implements ActionListener {

	private JLabel headingLabel;
	private JTextField textBox;

	private static final long serialVersionUID = -8583792789381803526L;

	public AddFriend() {
		// basic setup
		super("L�gg till V�n");
		setResizable(true);
		setVisible(true);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(HIDE_ON_CLOSE);

		// size och pos
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 600;
		int height = 120;
		int xStart = getXStart(screenSize, width);
		int yStart = getYStart(screenSize, height);
		setBounds(xStart, yStart, width, height);
		setPreferredSize(new Dimension(width, height));

		// stil
		setBackground(new Color(123, 123, 123));
		Font inputFont = new Font("SansSerif", Font.BOLD, 20);
		Font labelFont = new Font("SansSerif", Font.PLAIN, 20);

		// location panels
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(600, 15));
		add(topPanel, BorderLayout.NORTH);

		JPanel botPanel = new JPanel();
		botPanel.setPreferredSize(new Dimension(600, 15));
		add(botPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(10, 120));
		add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(10, 120));
		add(eastPanel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(600, 100));
		centerPanel.setLayout(new BorderLayout());
		add(centerPanel, BorderLayout.CENTER);

		JPanel labelPanel = new JPanel();
		labelPanel.setPreferredSize(new Dimension(150, 100));
		labelPanel.setLayout(new BorderLayout());
		centerPanel.add(labelPanel, BorderLayout.WEST);

		JPanel textPanel = new JPanel();
		textPanel.setPreferredSize(new Dimension(120, 100));
		textPanel.setLayout(new BorderLayout());
		centerPanel.add(textPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(110, 70));
		buttonPanel.setLayout(new BorderLayout());
		centerPanel.add(buttonPanel, BorderLayout.EAST);

		// komponenter
		headingLabel = new JLabel();
		headingLabel.setText("Skriv in epost:");
		headingLabel.setFont(labelFont);
		labelPanel.add(headingLabel, BorderLayout.CENTER);

		textBox = new JTextField(50);
		textBox.setFont(inputFont);
		textBox.setPreferredSize(new Dimension(80, 30));
		textBox.setColumns(8);
		textPanel.add(textBox, BorderLayout.CENTER);

		JButton submitButton = new JButton();
		submitButton.setText("L�gg till");
		submitButton.setPreferredSize(new Dimension(80, 20));
		submitButton.addActionListener(this);
		buttonPanel.add(submitButton, BorderLayout.EAST);

	}

	private int getXStart(Dimension screenSize, int width) {
		int y = (((int) Math.ceil(screenSize.getWidth()) - (width)) / 2);
		return y;
	}

	private int getYStart(Dimension screenSize, int height) {
		int x = (((int) Math.ceil(screenSize.getHeight()) - (height)) / 2);
		return x;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}
}