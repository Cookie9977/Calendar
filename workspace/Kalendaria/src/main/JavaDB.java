package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class JavaDB {
	private Connection con;

	public JavaDB(String host, String user, String password, String db) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://" + host + "/" + db, user, password);
		} catch (Exception error) {
			JOptionPane.showMessageDialog(null, error);
		}
	}

	public Object[][] getData(String SQL) {
		Object[][] data = null;
		try {
			Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = statement.executeQuery(SQL);
			ResultSetMetaData md = rs.getMetaData();
			int Kolumn = md.getColumnCount();
			int Rader = 0;

			while (rs.next()) {
				Rader++;
			}
			rs.beforeFirst();
			data = new Object[Rader][Kolumn];
			int rad = 0;
			while (rs.next()) {
				for (int i = 0; i <	Kolumn; i++) {
					data[rad][i] = rs.getString(i+1);
				}
				rad++;
			}
		} catch (Exception error) {
			JOptionPane.showMessageDialog(null, error);
		}

		return data;
	}
	
	public void execute(String SQL){
		try {
			Statement statement = con.createStatement();
			statement.execute(SQL);
		} catch (Exception error) {
			JOptionPane.showMessageDialog(null, error);
		}
	}
}
