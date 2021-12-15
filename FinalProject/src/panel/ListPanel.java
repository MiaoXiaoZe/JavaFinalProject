package panel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ListPanel extends WorkingPanel {
	public static ListPanel instance = new ListPanel();

	JScrollPane scrollPane;
	JTextArea textArea = new JTextArea(15, 30);

	public ListPanel() {
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);
		add(scrollPane);

	}

	@Override
	public void updateData() {
		textArea.setText("");
		// Connect to a database
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:javabook.db");
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
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
			resultSet = statement.executeQuery("select * from Record order by date asc");
		} catch (SQLException e) {
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
			e.printStackTrace();
			System.exit(0);
		}		
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		
	}

}
