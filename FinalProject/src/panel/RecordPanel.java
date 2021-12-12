package panel;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.*;

public class RecordPanel extends JPanel{
	
	public static RecordPanel instance = new RecordPanel();
	
	JLabel spend = new JLabel("花费");
	JLabel category = new JLabel("分类");
	JLabel comment = new JLabel("备注");
	JLabel date = new JLabel("日期");
	
	public JTextField spendText = new JTextField("0");
	public JComboBox categoryCb = new JComboBox<>();
	public JTextField commentText = new JTextField();
	public JTextField dateText = new JTextField();
	
	
	public JTextField tfSpend = new JTextField("0");
	
	JButton submitBtn = new JButton("记一笔");
	
	public RecordPanel() {
		
		
		JPanel northPanel = new JPanel(new GridLayout(4, 2, 40, 40));
		northPanel.add(spend);
		northPanel.add(spendText);
		
		northPanel.add(category);
		northPanel.add(categoryCb);
		
		northPanel.add(comment);
		northPanel.add(commentText);
		
		northPanel.add(date);
//		northPanel.add(datepick);
		
		JPanel submitPanel = new JPanel();
		submitPanel.add(submitBtn);
		
		this.setLayout(new BorderLayout());
		this.add(northPanel, BorderLayout.NORTH);
		this.add(submitPanel, BorderLayout.CENTER);
		submitBtn.addActionListener((e) -> {
			// Connect to a database
			Connection connection = null;
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:javabook.db");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.exit(0);
			}
			System.out.println("Database connected");
			// Create a statement
			Statement statement = null;
			try {
				statement = connection.createStatement();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.exit(0);
			}
			
			try {
				statement.executeUpdate("insert into Record values(null, "+ spendText.getText() + );
				JOptionPane.showMessageDialog(null,  "Inserted Successfully");
			} catch (SQLException e1) {
				
			} 
		});
	}
 

}
