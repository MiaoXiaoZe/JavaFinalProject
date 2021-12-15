package panel;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import util.CenterPanel;


public class MainPanel extends JPanel {

	public static MainPanel instance = new MainPanel();

	public JToolBar tb = new JToolBar();
	public JButton spendBtn = new JButton();
	public JButton recordBtn = new JButton();
	public JButton categoryBtn = new JButton();
	public JButton budgetBtn = new JButton();
	public JButton ListBtn = new JButton();

	public CenterPanel workingPanel;

	public MainPanel() {
		spendBtn = new JButton("Spend summary");
		recordBtn = new JButton("Add item");
		categoryBtn = new JButton("Category");
		budgetBtn = new JButton("Budget");
		ListBtn = new JButton("Record");



		addListener();
		tb.add(spendBtn);
		tb.add(recordBtn);
		tb.add(categoryBtn);
		tb.add(budgetBtn);
		tb.add(ListBtn);
		tb.setFloatable(false);
		
		workingPanel = new CenterPanel(0.8);
		setLayout(new BorderLayout());
		add(tb, BorderLayout.NORTH);
		add(workingPanel, BorderLayout.CENTER);
	}

	private void addListener() {
		spendBtn.addActionListener((e) -> {
			workingPanel.show(SpendPanel.instance);
		});
		recordBtn.addActionListener((e) -> {
			workingPanel.show(RecordPanel.instance);
		});
		categoryBtn.addActionListener((e) -> {
			workingPanel.show(CategoryPanel.instance);
		});
		budgetBtn.addActionListener((e) -> {
			workingPanel.show(BudgetPanel.instance);
		});
		ListBtn.addActionListener((e) ->{
			workingPanel.show(ListPanel.instance);
		});
	}

}