package panel;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BudgetPanel extends JPanel {
	public static BudgetPanel instance = new BudgetPanel();

	JLabel budget = new JLabel("Budget: ");
	JLabel month = new JLabel("Month(1 - 12): ");
	JTextField budgetField = new JTextField();
	JTextField monthField = new JTextField();

	JButton addBtn = new JButton("Add Budget");

	public BudgetPanel() {
		JPanel centerPanel = new JPanel(new GridLayout(2, 2));
		JPanel southPanel = new JPanel();
		centerPanel.add(budget);
		centerPanel.add(budgetField);
		centerPanel.add(month);
		centerPanel.add(monthField);
		southPanel.add(addBtn);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);

		addListener();
	}

	private void addListener() {
		addBtn.addActionListener((e) -> {
			if(Integer.parseInt(monthField.getText()) < 1 || Integer.parseInt(monthField.getText()) > 12)
				JOptionPane.showMessageDialog(null,  "Please input month from 1 to 12");
			// Connect to a database
			Connection connection = null;
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:javabook.db");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
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
				statement.executeUpdate(
						"update budget set num = " + budgetField.getText() + " where month = " + monthField.getText());
				JOptionPane.showMessageDialog(null,  "Inserted Successfully");

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.exit(0);
			}
			// Close the connection
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.exit(0);
			}
		});

	}
}
