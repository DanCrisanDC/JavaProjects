package BT;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;

public class AddAccount extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public AddAccount(Bank b, DefaultTableModel model2, Object[] row, JTable table) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblAmount = new JLabel("Amount:");
			lblAmount.setBounds(78, 34, 69, 20);
			contentPanel.add(lblAmount);
		}
		{
			JLabel lblAccountType = new JLabel("Account type:");
			lblAccountType.setBounds(78, 70, 118, 20);
			contentPanel.add(lblAccountType);
		}
		{
			JLabel lblTime = new JLabel("Time:");
			lblTime.setBounds(78, 141, 69, 20);
			contentPanel.add(lblTime);
		}
		{
			textField = new JTextField();
			textField.setBounds(196, 31, 146, 26);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		
		JRadioButton rdbtnSavings = new JRadioButton("Savings");
		buttonGroup.add(rdbtnSavings);
		rdbtnSavings.setBounds(196, 66, 155, 29);
		contentPanel.add(rdbtnSavings);
		
		JRadioButton rdbtnSpending = new JRadioButton("Spending");
		buttonGroup.add(rdbtnSpending);
		rdbtnSpending.setBounds(196, 102, 155, 29);
		contentPanel.add(rdbtnSpending);
		
		textField_1 = new JTextField();
		textField_1.setBounds(196, 138, 146, 26);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SavingAccount acc = new SavingAccount("RO12345", Double.parseDouble(textField.getText()), 0);
						SpendingAccount acc2 = new SpendingAccount("RO12345", Double.parseDouble(textField.getText()));
						Person p = b.findPerson(table.getValueAt(table.getSelectedRow(), 0).toString(), table.getValueAt(table.getSelectedRow(), 1).toString());
						if(rdbtnSavings.isSelected()) {
							acc.setPeriodOfTime(Integer.parseInt(textField_1.getText()));
							b.addAccount(acc, p);
						}
						if(rdbtnSpending.isSelected())
							b.addAccount(acc2, p);
						row[0] = p.getName();
						row[1] = acc.getAccountIban();
						row[2] = acc.getAmount();
						if(rdbtnSavings.isSelected())
							row[3] = "SavingAccount";
						else
							if(rdbtnSpending.isSelected())
								row[3] = "SpendingAccount";
						model2.addRow(row);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
