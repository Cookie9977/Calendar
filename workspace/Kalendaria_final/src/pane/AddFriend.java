package pane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Storage;

public class AddFriend extends JFrame implements ActionListener {

	private JLabel headingLabel;
	private JTextField textBox;

	private static final long serialVersionUID = -8583792789381803526L;

	public AddFriend() {
		// basic setup
		super("Lägg till Vän");
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
		setBackground(new Color(238, 238, 238));
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
		submitButton.setText("Lägg till");
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
	public void actionPerformed(ActionEvent e) {
		String Email = textBox.getText();
		boolean regex = Pattern.matches("^[a-zA-Z0-9\\_\\-]+\\@[a-zA-Z0-9\\_\\-]+\\.[a-zA-Z]+", Email);
		if (regex == true) {
			try {
				String receiverSQL = "SELECT id FROM user WHERE email='" + Email + "'";
				Object[][] receiverID = Storage.db.getData(receiverSQL);
				String friendsql = "INSERT INTO friend_link(`requester`, `reciver`, `accepted`) VALUES (" + Storage.id
						+ ", " + receiverID[0][0] + ", 0)";
				Storage.db.execute(friendsql);
				dispose();
			} catch (Exception errorAdd) {
				System.out.println("Vi har fångat ett fel!: " + errorAdd);
				JOptionPane.showMessageDialog(null, "Emailen du försökte lägga till hittas inte.");
			}

		} else {
			JOptionPane.showMessageDialog(null, "Eposten är inte korrekt angiven");
		}
	}
}
