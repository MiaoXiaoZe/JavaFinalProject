package panel;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import util.Check;

public class CategoryPanel extends WorkingPanel {

	public static CategoryPanel instance = new CategoryPanel();

	public JTextField textField = new JTextField(15);
	public JButton addBtn = new JButton("Add");
	public JButton deleteBtn = new JButton("Delete");

	JScrollPane scrollPane;
	JTextArea textArea = new JTextArea(10, 30);

	public CategoryPanel() {
		scrollPane = new JScrollPane(textArea);

		JPanel southPanel = new JPanel();
		southPanel.add(textField);
		southPanel.add(addBtn);
		southPanel.add(deleteBtn);

		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);
		add(scrollPane, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);

		addListener();
	}

	@Override
	public void updateData() {
		textArea.setText("");
		// Connect to a database
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:javabook.db");
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		// Create a statement
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery("select * from category");
		} catch (SQLException e1) {

		}
		try {
			while (resultSet.next()) {
				textArea.append(resultSet.getString(1) + "\n");
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
		textField.setText("");
	}

	@Override
	public void addListener() {
		addBtn.addActionListener((e) -> {
			if (!Check.checkEmpty(textField, "Category"))
			    return;
			// Connect to a database
			Connection connection = null;
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:javabook.db");
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.exit(0);
			}
			// Create a statement
			Statement statement = null;
			try {
				statement = connection.createStatement();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.exit(0);
			}
			try {
				statement.executeUpdate("insert into Category values('" + textField.getText() + "')");
				JOptionPane.showMessageDialog(null, "Inserted Successfully");
			} catch (SQLException e1) {
				if (textField.getText().toString().equals(""))
					JOptionPane.showMessageDialog(null, "Can not add empty category");
				else
					JOptionPane.showMessageDialog(null, "Duplicate category");
			}
			// Close the connection
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.exit(0);
			}
			updateData();
		});
		deleteBtn.addActionListener((e) -> {
			if (!Check.checkEmpty(textField, "Category"))
			    return;
			// Connect to a database
			Connection connection = null;
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:javabook.db");
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.exit(0);
			}
			// Create a statement
			Statement statement = null;
			try {
				statement = connection.createStatement();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.exit(0);
			}
			try {
				statement.executeUpdate("delete from Category where name = '" + textField.getText() + "'");
				JOptionPane.showMessageDialog(null, "Deleted Successfully");
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.exit(0);
			}
			// Close the connection
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.exit(0);
			}
			updateData();
		});
	}

}
