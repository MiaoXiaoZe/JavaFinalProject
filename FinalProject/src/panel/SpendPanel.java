package panel;

import java.awt.*;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SpendPanel extends JPanel {
	public static SpendPanel instance = new SpendPanel();

	public JLabel monthlyBudget1 = new JLabel("预算");
	public JLabel monthSpend1 = new JLabel("本月消费");
	public JLabel MonthLeft1 = new JLabel("本月剩余");
	public JLabel monthLeftDay1 = new JLabel("距离月末");
	public JLabel todaySpend1 = new JLabel("今日消费");

	public JLabel monthlyBudget2 = new JLabel("$10000");
	public JLabel monthSpend2 = new JLabel("$2300");
	public JLabel MonthLeft2 = new JLabel("$7000");
	public JLabel monthLeftDay2 = new JLabel("15days");
	public JLabel todaySpend2 = new JLabel("$100");

	int budget = 0;
	int mspend = 0;
	int left = 0;
	int dayLeft = 0;
	int tdspend = 0;

	String startDate;
	String endDate;
	String todayDate;

	private SpendPanel() {
		getdata();
		this.setLayout(new BorderLayout());
		this.add(center(), BorderLayout.CENTER);

	}

	private void getdata() {

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

		// date
		ResultSet dateResultSet = null;
		try {
			dateResultSet = statement.executeQuery("select date('now')");
			todayDate = dateResultSet.getString(1);
			dateResultSet = statement.executeQuery("select date('now', 'start of month')");
			startDate = dateResultSet.getString(1);
			dateResultSet = statement.executeQuery("select date('now', 'start of month', '+1 month', '-1 day')");
			endDate = dateResultSet.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}

		// Month spend
		ResultSet monthSpendResultSet = null;
		try {
			monthSpendResultSet = statement
					.executeQuery("select * from Record where date between '" + startDate + "' and '" + endDate + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}

		try {
			while (monthSpendResultSet.next()) {
				mspend += monthSpendResultSet.getInt(3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		left = budget - mspend;

		// Today spend
		ResultSet todayResultSet = null;
		try {
			todayResultSet = statement
					.executeQuery("select * from Record where date = '" + todayDate + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		try {
			while (todayResultSet.next()) {
				tdspend += todayResultSet.getInt(3);
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

	private JPanel center() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(6, 2));

		monthlyBudget2.setText("" + budget);
		monthSpend2.setText("" + mspend);
		MonthLeft2.setText("" + left);
		Calendar cal = Calendar.getInstance();
		dayLeft = cal.getActualMaximum(Calendar.DATE) - cal.get((Calendar.DAY_OF_MONTH));
		monthLeftDay2.setText("" + dayLeft);
		todaySpend2.setText("" + tdspend);

		centerPanel.add(monthlyBudget1);
		centerPanel.add(monthlyBudget2);

		centerPanel.add(monthSpend1);
		centerPanel.add(monthSpend2);

		centerPanel.add(MonthLeft1);
		centerPanel.add(MonthLeft2);

		centerPanel.add(monthLeftDay1);
		centerPanel.add(monthLeftDay2);

		centerPanel.add(todaySpend1);
		centerPanel.add(todaySpend2);

		return centerPanel;

	}

}
