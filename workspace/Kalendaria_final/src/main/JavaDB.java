package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class JavaDB {
	public static final String db = null;
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
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(SQL);
			ResultSetMetaData md = rs.getMetaData();
			int column = md.getColumnCount();
			int rows = 0;

			while (rs.next()) {
				rows++;
			}
			rs.beforeFirst();
			data = new Object[rows][column];
			int row = 0;
			while (rs.next()) {
				for (int i = 0; i < column; i++) {
					data[row][i] = rs.getString(i + 1);
				}
				row++;
			}
		} catch (Exception error) {
			JOptionPane.showMessageDialog(null, error);
		}

		return data;
	}

	public void execute(String SQL) {
		try {
			Statement stmt = con.createStatement();
			stmt.execute(SQL);
		} catch (Exception error) {
			JOptionPane.showMessageDialog(null, error);
		}
	}

	public int executeReturn(String SQL) {
		try {
			Statement stmt = con.createStatement();
			int id = 0;
			stmt.executeUpdate(SQL, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			// System.out.println(id.getInt(0));
			// TODO returner insert id
			return id;
		} catch (Exception error) {
			System.out.println(error);
			JOptionPane.showMessageDialog(null, error);
		}
		return 0;
	}
}
