package panel;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ListPanel extends JPanel {
	public static ListPanel instance = new ListPanel();

	JScrollPane scrollPane;
	JTextArea textArea = new JTextArea(22, 40);

	public ListPanel() {
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);
		add(scrollPane);

		// Connect to a database
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:javabook.db");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("Database connected");
		// Create a statement
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery("select * from Record");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}

		try {
			while (resultSet.next()) {
				textArea.append("Spend： " + resultSet.getString(2) + "\t" + "Catogary: " + resultSet.getString(3) + "\t"
						+ "Comment: " + resultSet.getString(4) + "\t" + "Date: " + resultSet.getString(5) + "\t" + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		// Close the connection
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	}

}
