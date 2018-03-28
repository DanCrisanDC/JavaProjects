package queue;

import java.awt.EventQueue;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	public int minArr;
	public int maxArr;
	public int minSer;
	public int maxSer;
	public int nrOfQ;
	public int simTime;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1004, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(223, 49, 603, 416);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Arial", Font.PLAIN, 18));
		textArea.setEditable(false);
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));

		JLabel lblNewLabel = new JLabel("Min. Arrival Time");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel.setBounds(15, 16, 193, 20);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 18));
		textField.setBounds(15, 49, 146, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblMaxArrivalTime = new JLabel("Max. Arrival Time");
		lblMaxArrivalTime.setFont(new Font("Arial", Font.PLAIN, 18));
		lblMaxArrivalTime.setBounds(15, 91, 193, 20);
		contentPane.add(lblMaxArrivalTime);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.PLAIN, 18));
		textField_1.setBounds(15, 127, 146, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblMinServiceTime = new JLabel("Min. Service Time");
		lblMinServiceTime.setFont(new Font("Arial", Font.PLAIN, 18));
		lblMinServiceTime.setBounds(15, 169, 193, 20);
		contentPane.add(lblMinServiceTime);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial", Font.PLAIN, 18));
		textField_2.setBounds(15, 205, 146, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblMaxServiceTime = new JLabel("Max. Service Time");
		lblMaxServiceTime.setFont(new Font("Arial", Font.PLAIN, 18));
		lblMaxServiceTime.setBounds(15, 247, 193, 20);
		contentPane.add(lblMaxServiceTime);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Arial", Font.PLAIN, 18));
		textField_3.setBounds(15, 283, 146, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNoOfQueues = new JLabel("No. of Queues");
		lblNoOfQueues.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNoOfQueues.setBounds(15, 325, 193, 20);
		contentPane.add(lblNoOfQueues);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("Arial", Font.PLAIN, 18));
		textField_4.setBounds(15, 361, 146, 26);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblSimulationTime = new JLabel("Simulation Interval (ms)");
		lblSimulationTime.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSimulationTime.setBounds(15, 403, 193, 20);
		contentPane.add(lblSimulationTime);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("Arial", Font.PLAIN, 18));
		textField_5.setBounds(15, 439, 146, 26);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Arial", Font.PLAIN, 18));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.setOut(printStream);
				System.setErr(printStream);
				try {
					minArr = Integer.parseInt(textField.getText());
					maxArr = Integer.parseInt(textField_1.getText());
					minSer = Integer.parseInt(textField_2.getText());
					maxSer = Integer.parseInt(textField_3.getText());
					nrOfQ = Integer.parseInt(textField_4.getText());
					simTime = Integer.parseInt(textField_5.getText());
					Simulation manager = new Simulation(minArr, maxArr, minSer, maxSer, nrOfQ, simTime);
					Thread newManager = new Thread(manager);
					newManager.start();
				} catch (NumberFormatException e) {
					System.out.println("Invalid input");
				}
			}
		});
		btnStart.setBounds(852, 49, 115, 29);
		contentPane.add(btnStart);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textArea.setText("");
			}
		});
		btnReset.setFont(new Font("Arial", Font.PLAIN, 18));
		btnReset.setBounds(852, 126, 115, 29);
		contentPane.add(btnReset);
	}
}
