package panel;

import java.awt.*;
import javax.swing.*;


public class MainPanel extends JPanel {

	public static MainPanel instance = new MainPanel();

	public JToolBar tb = new JToolBar();
	public JButton spendBtn = new JButton();
	public JButton recordBtn = new JButton();
	public JButton categoryBtn = new JButton();
	public JButton budgetBtn = new JButton();
	public JButton ListBtn = new JButton();

	private JComponent c;

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
		setLayout(new BorderLayout());
		add(tb, BorderLayout.NORTH);
	}

	private void addListener() {
		spendBtn.addActionListener((e) -> {
			show(SpendPanel.instance);
		});
		recordBtn.addActionListener((e) -> {
			show(RecordPanel.instance);
		});
		categoryBtn.addActionListener((e) -> {
			show(CategoryPanel.instance);
		});
		budgetBtn.addActionListener((e) -> {
			show(BudgetPanel.instance);
		});
		ListBtn.addActionListener((e) ->{
			show(ListPanel.instance);
		});
	}

	public void show(JComponent p) {
		this.c = p;
		Component[] cs = getComponents();
		for (Component c : cs) {
			remove(c);
		}
		p.revalidate();
		add(tb, BorderLayout.NORTH);
		add(p, BorderLayout.CENTER);
		this.updateUI();
	}

}