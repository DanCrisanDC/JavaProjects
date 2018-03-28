package BT;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditPerson extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	public EditPerson(Bank b, DefaultTableModel model, Object[] row, JTable table, JTable table2, int rowClick) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblName = new JLabel("Name:");
			lblName.setBounds(102, 67, 69, 20);
			contentPanel.add(lblName);
		}
		{
			JLabel lblAddress = new JLabel("Address:");
			lblAddress.setBounds(102, 103, 69, 20);
			contentPanel.add(lblAddress);
		}
		{
			textField = new JTextField();
			textField.setBounds(186, 64, 146, 26);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(186, 100, 146, 26);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String holder = table.getValueAt(table.getSelectedRow(), 0).toString();
						for(int i = 0; i < table2.getRowCount(); i++) {
							if((table2.getValueAt(i, 0).toString()).equals(holder))
								table2.setValueAt(textField.getText(), i, 0);
						}
						b.removePerson(b.getHm().get(table.getValueAt(table.getSelectedRow(), 0)));
						model.removeRow(rowClick);
						Person p = new Person(textField.getText(), textField_1.getText());
						b.addPerson(p);
						row[0] = p.getName();
						row[1] = p.getAddress();
						model.addRow(row);
						b.serialization();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
