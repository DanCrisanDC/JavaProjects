package presentation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Client;
import model.Order;
import model.Product;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
					frame.setResizable(true);
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

		Object[] columns = { "idClient", "name", "age", "address", "phone" };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		Font font = new Font("", 1, 14);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1466, 1599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(676, 15, 326, 126);
		contentPane.add(scrollPane);

		JTable table = new JTable();
		scrollPane.setViewportView(table);

		table.setModel(model);
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.black);
		table.setFont(font);
		table.setRowHeight(30);
		table.setVisible(true);

		Object[] columns2 = { "idProduct", "name", "price", "quantity", "producer" };
		DefaultTableModel model2 = new DefaultTableModel();
		model2.setColumnIdentifiers(columns2);
		Font font2 = new Font("", 1, 14);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 599);

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(676, 157, 326, 126);
		contentPane.add(scrollPane2);

		JTable table2 = new JTable();
		scrollPane2.setViewportView(table2);

		table2.setModel(model2);
		table2.setBackground(Color.LIGHT_GRAY);
		table2.setForeground(Color.black);
		table2.setFont(font2);
		table2.setRowHeight(30);
		table2.setVisible(true);

		Object[] columns3 = { "idOrder", "quantity", "idClient", "idProduct" };
		DefaultTableModel model3 = new DefaultTableModel();
		model3.setColumnIdentifiers(columns3);
		Font font3 = new Font("", 1, 14);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 599);

		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(676, 299, 326, 126);
		contentPane.add(scrollPane3);

		JTable table3 = new JTable();
		scrollPane3.setViewportView(table3);

		table3.setModel(model3);
		table3.setBackground(Color.LIGHT_GRAY);
		table3.setForeground(Color.black);
		table3.setFont(font3);
		table3.setRowHeight(30);
		table3.setVisible(true);

		Object[] row = new Object[5];

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(501, 472, 501, 55);
		contentPane.add(scrollPane_1);

		JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));

		JButton btnAddClient = new JButton("Add Client");
		btnAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientDAO c = new ClientDAO();
				Client c1 = new Client(textField.getText(), Integer.parseInt(textField_1.getText()),
						textField_2.getText(), textField_3.getText());
				c.insert(c1);
			}
		});
		btnAddClient.setBounds(15, 16, 141, 29);
		contentPane.add(btnAddClient);

		JButton btnUpdateClient = new JButton("Update Client");
		btnUpdateClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientDAO c = new ClientDAO();
				c.update(Integer.parseInt(textField_11.getText()), textField.getText(),
						Integer.parseInt(textField_1.getText()), textField_2.getText(), textField_3.getText());
			}
		});
		btnUpdateClient.setBounds(15, 61, 141, 29);
		contentPane.add(btnUpdateClient);

		JButton btnFindClient = new JButton("Find Client");
		btnFindClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientDAO c = new ClientDAO();
				Client c1 = c.findById(Integer.parseInt(textField_11.getText()));
				for (int i = model.getRowCount() - 1; i >= 0; i--)
					model.removeRow(i);
				row[0] = c1.getId();
				row[1] = c1.getName();
				row[2] = c1.getAge();
				row[3] = c1.getAddress();
				row[4] = c1.getPhone();
				model.addRow(row);
			}
		});
		btnFindClient.setBounds(15, 102, 141, 29);
		contentPane.add(btnFindClient);

		JButton btnDeleteClient = new JButton("Delete Client");
		btnDeleteClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientDAO c = new ClientDAO();
				c.delete(Integer.parseInt(textField_11.getText()));
			}
		});
		btnDeleteClient.setBounds(15, 147, 141, 29);
		contentPane.add(btnDeleteClient);

		JLabel lblClientName = new JLabel("Client Name:");
		lblClientName.setBounds(179, 50, 108, 20);
		contentPane.add(lblClientName);

		JLabel lblClientAge = new JLabel("Client Age:");
		lblClientAge.setBounds(179, 83, 108, 20);
		contentPane.add(lblClientAge);

		JLabel lblClientAddress = new JLabel("Client Address:");
		lblClientAddress.setBounds(179, 118, 108, 20);
		contentPane.add(lblClientAddress);

		JLabel lblClientPhone = new JLabel("Client Phone:");
		lblClientPhone.setBounds(179, 151, 108, 20);
		contentPane.add(lblClientPhone);

		textField = new JTextField();
		textField.setBounds(335, 47, 146, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(335, 80, 146, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(335, 115, 146, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(335, 148, 146, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblProductName = new JLabel("Product Name:");
		lblProductName.setBounds(179, 231, 141, 20);
		contentPane.add(lblProductName);

		JLabel lblProductPrice = new JLabel("Product Price:");
		lblProductPrice.setBounds(179, 265, 141, 20);
		contentPane.add(lblProductPrice);

		JLabel lblProductQuantity = new JLabel("Product Quantity:");
		lblProductQuantity.setBounds(179, 299, 141, 20);
		contentPane.add(lblProductQuantity);

		JLabel lblProductProducer = new JLabel("Product Producer:");
		lblProductProducer.setBounds(179, 334, 141, 20);
		contentPane.add(lblProductProducer);

		textField_4 = new JTextField();
		textField_4.setBounds(335, 228, 146, 26);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(335, 262, 146, 26);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setBounds(336, 296, 146, 26);
		contentPane.add(textField_6);
		textField_6.setColumns(10);

		textField_7 = new JTextField();
		textField_7.setBounds(335, 331, 146, 26);
		contentPane.add(textField_7);
		textField_7.setColumns(10);

		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductDAO p = new ProductDAO();
				Product p1 = new Product(textField_4.getText(), Double.parseDouble(textField_5.getText()),
						Integer.parseInt(textField_6.getText()), textField_7.getText());
				p.insert(p1);
			}
		});
		btnAddProduct.setBounds(15, 197, 141, 29);
		contentPane.add(btnAddProduct);

		JButton btnUpdateProduct = new JButton("Update Product");
		btnUpdateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductDAO p = new ProductDAO();
				p.update(Integer.parseInt(textField_12.getText()), textField_4.getText(),
						Double.parseDouble(textField_5.getText()), Integer.parseInt(textField_6.getText()),
						textField_7.getText());
			}
		});
		btnUpdateProduct.setBounds(15, 242, 141, 29);
		contentPane.add(btnUpdateProduct);

		JButton btnFindProduct = new JButton("Find Product");
		btnFindProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductDAO p = new ProductDAO();
				Product p1 = p.findById(Integer.parseInt(textField_12.getText()));
				for (int i = model2.getRowCount() - 1; i >= 0; i--)
					model2.removeRow(i);
				row[0] = p1.getId();
				row[1] = p1.getName();
				row[2] = p1.getPrice();
				row[3] = p1.getQuantity();
				row[4] = p1.getProducer();
				model2.addRow(row);
			}
		});
		btnFindProduct.setBounds(15, 283, 141, 29);
		contentPane.add(btnFindProduct);

		JButton btnDeleteProduct = new JButton("Delete Product");
		btnDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductDAO p = new ProductDAO();
				p.delete(Integer.parseInt(textField_12.getText()));
			}
		});
		btnDeleteProduct.setBounds(15, 322, 141, 29);
		contentPane.add(btnDeleteProduct);

		JButton btnAddOrder = new JButton("Add Order");
		btnAddOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderDAO o = new OrderDAO();
				ClientDAO c = new ClientDAO();
				ProductDAO p = new ProductDAO();
				Order o1 = new Order(Integer.parseInt(textField_8.getText()), Integer.parseInt(textField_9.getText()),
						Integer.parseInt(textField_10.getText()));
				o.insert(o1);
				try {
					Client c1 = c.findById(o1.getIdClient());
					Product p1 = p.findById(o1.getIdProduct());
					PrintWriter writer = new PrintWriter("receipt.txt", "UTF-8");
					writer.println("Total price for " + c1.getName() + ": " + o1.getQuantity() * p1.getPrice());
					writer.close();
				} catch (IOException e1) {
					System.out.println("Something went wrong! Please try again.");
				}
			}
		});
		btnAddOrder.setBounds(15, 377, 141, 29);
		contentPane.add(btnAddOrder);

		JButton btnUpdateOrder = new JButton("Update Order");
		btnUpdateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderDAO o = new OrderDAO();
				o.update(Integer.parseInt(textField_13.getText()), Integer.parseInt(textField_8.getText()),
						Integer.parseInt(textField_9.getText()), Integer.parseInt(textField_10.getText()));
			}
		});
		btnUpdateOrder.setBounds(14, 417, 141, 29);
		contentPane.add(btnUpdateOrder);

		JButton btnFindOrder = new JButton("Find Order");
		btnFindOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderDAO o = new OrderDAO();
				Order o1 = o.findById(Integer.parseInt(textField_13.getText()));
				for (int i = model3.getRowCount() - 1; i >= 0; i--)
					model3.removeRow(i);
				row[0] = o1.getId();
				row[1] = o1.getQuantity();
				row[2] = o1.getIdClient();
				row[3] = o1.getIdProduct();
				model3.addRow(row);
			}
		});
		btnFindOrder.setBounds(15, 458, 141, 29);
		contentPane.add(btnFindOrder);

		JButton btnDeleteOrder = new JButton("Delete Order");
		btnDeleteOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderDAO o = new OrderDAO();
				o.delete(Integer.parseInt(textField_13.getText()));
			}
		});
		btnDeleteOrder.setBounds(15, 498, 141, 29);
		contentPane.add(btnDeleteOrder);

		JLabel lblOrderQuantity = new JLabel("Order Quantity:");
		lblOrderQuantity.setBounds(179, 415, 141, 20);
		contentPane.add(lblOrderQuantity);

		JLabel lblOrderClientId = new JLabel("Order Client ID:");
		lblOrderClientId.setBounds(179, 450, 141, 20);
		contentPane.add(lblOrderClientId);

		JLabel lblOrderProductId = new JLabel("Order Product ID:");
		lblOrderProductId.setBounds(179, 485, 141, 20);
		contentPane.add(lblOrderProductId);

		textField_8 = new JTextField();
		textField_8.setBounds(335, 412, 146, 26);
		contentPane.add(textField_8);
		textField_8.setColumns(10);

		textField_9 = new JTextField();
		textField_9.setBounds(335, 447, 146, 26);
		contentPane.add(textField_9);
		textField_9.setColumns(10);

		textField_10 = new JTextField();
		textField_10.setBounds(335, 482, 146, 26);
		contentPane.add(textField_10);
		textField_10.setColumns(10);

		textField_11 = new JTextField();
		textField_11.setBounds(335, 12, 146, 26);
		contentPane.add(textField_11);
		textField_11.setColumns(10);

		JButton btnRefreshClients = new JButton("RefreshClients");
		btnRefreshClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientDAO c = new ClientDAO();
				for (int i = model.getRowCount() - 1; i >= 0; i--)
					model.removeRow(i);
				List<Client> clients = new ArrayList<Client>();
				clients = c.findAll();
				for (Client c1 : clients) {
					row[0] = c1.getId();
					row[1] = c1.getName();
					row[2] = c1.getAge();
					row[3] = c1.getAddress();
					row[4] = c1.getPhone();
					model.addRow(row);
				}
			}
		});
		btnRefreshClients.setBounds(496, 16, 160, 29);
		contentPane.add(btnRefreshClients);

		JLabel lblClientId = new JLabel("Client ID:");
		lblClientId.setBounds(179, 15, 108, 20);
		contentPane.add(lblClientId);

		textField_12 = new JTextField();
		textField_12.setBounds(335, 193, 146, 26);
		contentPane.add(textField_12);
		textField_12.setColumns(10);

		JLabel lblProductId = new JLabel("Product ID:");
		lblProductId.setBounds(179, 196, 108, 20);
		contentPane.add(lblProductId);

		textField_13 = new JTextField();
		textField_13.setBounds(335, 377, 146, 26);
		contentPane.add(textField_13);
		textField_13.setColumns(10);

		JLabel lblOrderId = new JLabel("Order ID:");
		lblOrderId.setBounds(179, 380, 141, 20);
		contentPane.add(lblOrderId);

		JButton btnRefreshProducts = new JButton("Refresh Products");
		btnRefreshProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductDAO p = new ProductDAO();
				for (int i = model2.getRowCount() - 1; i >= 0; i--)
					model2.removeRow(i);
				List<Product> products = new ArrayList<Product>();
				products = p.findAll();
				for (Product p1 : products) {
					row[0] = p1.getId();
					row[1] = p1.getName();
					row[2] = p1.getPrice();
					row[3] = p1.getQuantity();
					row[4] = p1.getProducer();
					model2.addRow(row);
				}
			}
		});
		btnRefreshProducts.setBounds(501, 158, 160, 29);
		contentPane.add(btnRefreshProducts);

		JButton btnRefreshOrders = new JButton("Refresh Orders");
		btnRefreshOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderDAO o = new OrderDAO();
				for (int i = model3.getRowCount() - 1; i >= 0; i--)
					model3.removeRow(i);
				List<Order> orders = new ArrayList<Order>();
				orders = o.findAll();
				for (Order o1 : orders) {
					row[0] = o1.getId();
					row[1] = o1.getQuantity();
					row[2] = o1.getIdClient();
					row[3] = o1.getIdProduct();
					model3.addRow(row);
				}
			}
		});
		btnRefreshOrders.setBounds(501, 300, 160, 29);
		contentPane.add(btnRefreshOrders);

		JLabel lblNotificationMessage = new JLabel("Notification Message");
		lblNotificationMessage.setBounds(676, 441, 172, 20);
		contentPane.add(lblNotificationMessage);
		
		JLabel lblQuantity = new JLabel("Quantity > 5");
		lblQuantity.setBounds(521, 345, 102, 20);
		contentPane.add(lblQuantity);
		
		JButton btnFilter = new JButton("Filter");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderDAO o = new OrderDAO();
				List<Order> o1 = o.findAll();
				for (int i = model3.getRowCount() - 1; i >= 0; i--)
					model3.removeRow(i);
				for(int i = 0; i < o1.size(); i++) {
					if(o1.get(i).getQuantity() > 5) {
						row[0]=o1.get(i).getId();
						row[1]=o1.get(i).getQuantity();
						row[2]=o1.get(i).getIdClient();
						row[3]=o1.get(i).getIdProduct();
						model3.addRow(row);
					}
				}
			}
		});
		btnFilter.setBounds(501, 377, 160, 29);
		contentPane.add(btnFilter);
		System.setOut(printStream);
		System.setErr(printStream);
	}
}
