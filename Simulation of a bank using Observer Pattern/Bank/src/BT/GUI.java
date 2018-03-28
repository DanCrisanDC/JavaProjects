package BT;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_2;
	int rowClick;
	int rowClick2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Bank b = new Bank();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		
		Object[] row = new Object[4];
		
		
		b.deserialization();
		
		List<Account> a= new ArrayList<Account>();
		Person per = new Person();
		for(String p : b.getHm().keySet()) {
			a = b.getHm().get(p).getAcc();
			per = b.getHm().get(p);
			for (Account ac : a) {
				ac.addObserver(per);
			}
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1060, 643);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Object[] columns = { "Name", "Address" };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		Font font = new Font("", 1, 14);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(375, 15, 627, 231);
		contentPane.add(scrollPane_3);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane_3.setViewportView(scrollPane);

		JTable table = new JTable();
		scrollPane.setViewportView(table);

		table.setModel(model);
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.black);
		table.setFont(font);
		table.setRowHeight(30);
		table.setVisible(true);
		
		Object[] columns2 = { "Holder", "IBAN", "Amount", "Type" };
		DefaultTableModel model2 = new DefaultTableModel();
		model2.setColumnIdentifiers(columns2);
		Font font2 = new Font("", 1, 14);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 599);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(375, 275, 627, 222);
		contentPane.add(scrollPane_2);

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane_2.setViewportView(scrollPane2);

		JTable table2 = new JTable();
		scrollPane2.setViewportView(table2);

		table2.setModel(model2);
		table2.setBackground(Color.LIGHT_GRAY);
		table2.setForeground(Color.black);
		table2.setFont(font2);
		table2.setRowHeight(30);
		table2.setVisible(true);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(46, 45, 112, 20);
		contentPane.add(lblAmount);
		
		textField_2 = new JTextField();
		textField_2.setBounds(173, 42, 146, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnAddPerson = new JButton("Add Person");
		btnAddPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddPerson addp = new AddPerson(b, model, row);
				addp.setVisible(true);
			}
		});
		btnAddPerson.setBounds(14, 120, 155, 29);
		contentPane.add(btnAddPerson);
		
		JButton btnRemovePerson = new JButton("Remove Person");
		btnRemovePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b.removePerson(b.getHm().get(table.getValueAt(table.getSelectedRow(), 0)));
				model.removeRow(rowClick);
			}
		});
		btnRemovePerson.setBounds(14, 165, 155, 29);
		contentPane.add(btnRemovePerson);
		
		JButton btnEditPerson = new JButton("Edit Person");
		btnEditPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditPerson editp = new EditPerson(b, model, row, table, table2, rowClick);
				editp.setVisible(true);
			}
		});
		btnEditPerson.setBounds(14, 210, 155, 29);
		contentPane.add(btnEditPerson);
		
		JButton btnAddAccount = new JButton("Add Account");
		btnAddAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddAccount adda = new AddAccount(b, model2, row, table);
				adda.setVisible(true);
			}
		});
		btnAddAccount.setBounds(188, 120, 155, 29);
		contentPane.add(btnAddAccount);
		
		JButton btnRemoveAccount = new JButton("Remove Account");
		btnRemoveAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String holder = table2.getValueAt(table2.getSelectedRow(), 0).toString();
				String IBAN = table2.getValueAt(table2.getSelectedRow(), 1).toString();
				int i = 0, index = 0;
				Account a = null;
				for (Account acc : b.getHm().get(holder).getAcc()) {
					if (acc.getAccountIban().equals(IBAN)) {
						a = acc;
						index = i;
						break;
					}
					i++;
				}
				b.getHm().get(table2.getValueAt(table2.getSelectedRow(), 0)).getAcc().remove(a);
				model2.removeRow(table2.getSelectedRow());
			}
		});
		btnRemoveAccount.setBounds(188, 165, 155, 29);
		contentPane.add(btnRemoveAccount);
		
		JButton btnDepositMoney = new JButton("Deposit money");
		btnDepositMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String holder = table2.getValueAt(table2.getSelectedRow(), 0).toString();
				String IBAN = table2.getValueAt(table2.getSelectedRow(), 1).toString();
				int i = 0, index = 0;
				Account a= new Account();
				for (Account acc : b.getHm().get(holder).getAcc()) {
					if (acc.getAccountIban().equals(IBAN)) {
						a = acc;
						index = i;
						break;
					}
					i++;
				}
				if (table2.getValueAt(table2.getSelectedRow(), 3).equals("SpendingAccount")) {
					b.getHm().get(holder).getAcc().get(index).addAmount(Double.parseDouble(textField_2.getText()));
					Double money = Double.parseDouble(table2.getValueAt(table2.getSelectedRow(), 2).toString()) + Double.parseDouble(textField_2.getText());
					row[2] = money.toString();
					model2.setValueAt(Double.parseDouble(table2.getValueAt(table2.getSelectedRow(), 2).toString()) + Double.parseDouble(textField_2.getText()), table2.getSelectedRow(), 2);
				} else {
					System.out.println("Cannot make more than one deposit per saving account!\n");
				}
			}
		});
		btnDepositMoney.setBounds(188, 210, 155, 29);
		contentPane.add(btnDepositMoney);
		
		JButton btnWithdrawMoney = new JButton("Withdraw money");
		btnWithdrawMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String holder = table2.getValueAt(table2.getSelectedRow(), 0).toString();
				String IBAN = table2.getValueAt(table2.getSelectedRow(), 1).toString();
				int i = 0, index = 0;
				Account a= new Account();
				for (Account acc : b.getHm().get(holder).getAcc()) {
					if (acc.getAccountIban().equals(IBAN)) {
						a = acc;
						index = i;
						break;
					}
					i++;
				}
				if (table2.getValueAt(table2.getSelectedRow(), 3).equals("SpendingAccount")) {
					((SpendingAccount)b.getHm().get(holder).getAcc().get(index)).removeAmount(Double.parseDouble(textField_2.getText()));
					model2.setValueAt(b.getHm().get(holder).getAcc().get(index).getAmount(), table2.getSelectedRow(), 2);
				} else {
					if(Double.parseDouble(table2.getValueAt(table2.getSelectedRow(), 2).toString()) != 0) {
						((SavingAccount)b.getHm().get(holder).getAcc().get(index)).withdrawMoney();
						model2.setValueAt(0, table2.getSelectedRow(), 2);
					}
				}
			}
		});
		btnWithdrawMoney.setBounds(188, 255, 155, 29);
		contentPane.add(btnWithdrawMoney);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = model.getRowCount()-1; i >= 0; i--)
					model.removeRow(i);
				for(int i = model2.getRowCount()-1; i >= 0; i--)
					model2.removeRow(i);
				for(String s : b.getHm().keySet()) {
					row[0] = b.getHm().get(s).getName();
					row[1] = b.getHm().get(s).getAddress();
					model.addRow(row);
				}
				for(String s : b.getHm().keySet()) {
					for(Account a : b.getHm().get(s).getAcc()) {
						row[0] = s;
						row[1] = a.getAccountIban();
						row[2] = a.getAmount();
						row[3] = a.getType();
						model2.addRow(row);
					}
				}
			}
		});
		btnRefresh.setBounds(14, 255, 155, 29);
		contentPane.add(btnRefresh);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(17, 319, 326, 178);
		contentPane.add(scrollPane_4);
		
		JTextArea textArea = new JTextArea();
		scrollPane_4.setViewportView(textArea);
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		
		table.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseClicked(java.awt.event.MouseEvent evt){
				int row = table.getSelectedRow();
				int row2 = table2.getSelectedRow();
				rowClick = table.getSelectedRow();
				rowClick2 = table2.getSelectedRow();
			}
		});
		
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				b.serialization();
				System.exit(0);
			}
		});
		System.setOut(printStream);
		System.setErr(printStream);
	}
}
