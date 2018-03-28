package polynomial;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

  private JPanel contentPane;
  private JTextField textField;
  private JTextField textField_1;
  private JTextField textField_2;

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
    setBounds(100, 100, 921, 541);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JLabel lblPolynomial = new JLabel("Polynomial 1:");
    lblPolynomial.setFont(new Font("Arial", Font.PLAIN, 50));
    lblPolynomial.setBounds(26, 28, 311, 60);
    contentPane.add(lblPolynomial);

    JLabel lblPolynomial_1 = new JLabel("Polynomial 2:");
    lblPolynomial_1.setFont(new Font("Arial", Font.PLAIN, 50));
    lblPolynomial_1.setBounds(26, 96, 311, 60);
    contentPane.add(lblPolynomial_1);

    textField = new JTextField();
    textField.setFont(new Font("Arial", Font.PLAIN, 50));
    textField.setBounds(338, 32, 537, 52);
    contentPane.add(textField);
    textField.setColumns(10);

    textField_1 = new JTextField();
    textField_1.setFont(new Font("Arial", Font.PLAIN, 50));
    textField_1.setBounds(338, 100, 537, 52);
    contentPane.add(textField_1);
    textField_1.setColumns(10);

    JButton btnAdd = new JButton("+");
    btnAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        Polynomial rez = new Polynomial();
        Polynomial poly1 = new Polynomial();
        Polynomial poly2 = new Polynomial();
        String s1 = textField.getText();
        String s2 = textField_1.getText();
        String out = new String();
        poly1.processPoly(s1);
        poly2.processPoly(s2);
        rez = poly1.addPoly(poly2);
        out = rez.printP();
        if (poly1.equals(poly2)) {
          System.out.println(true);
        }
        textField_2.setText(out);
      }
    });
    btnAdd.setFont(new Font("Arial", Font.PLAIN, 50));
    btnAdd.setBounds(26, 265, 250, 60);
    contentPane.add(btnAdd);

    JButton btnSubstract = new JButton("-");
    btnSubstract.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        Polynomial rez = new Polynomial();
        Polynomial poly1 = new Polynomial();
        Polynomial poly2 = new Polynomial();
        String s1 = textField.getText();
        String s2 = textField_1.getText();
        String out = new String();
        poly1.processPoly(s1);
        poly2.processPoly(s2);
        rez = poly1.subPoly(poly2);
        out = rez.printP();
        textField_2.setText(out);
      }
    });
    btnSubstract.setFont(new Font("Arial", Font.PLAIN, 50));
    btnSubstract.setBounds(26, 364, 250, 61);
    contentPane.add(btnSubstract);

    textField_2 = new JTextField();
    textField_2.setFont(new Font("Arial", Font.PLAIN, 50));
    textField_2.setBounds(338, 175, 537, 52);
    contentPane.add(textField_2);
    textField_2.setColumns(10);

    JLabel lblResult = new JLabel("Result:");
    lblResult.setFont(new Font("Arial", Font.PLAIN, 50));
    lblResult.setBounds(173, 175, 164, 52);
    contentPane.add(lblResult);
    
    JButton btnX = new JButton("X");
    btnX.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Polynomial rez = new Polynomial();
        Polynomial poly1 = new Polynomial();
        Polynomial poly2 = new Polynomial();
        String s1 = textField.getText();
        String s2 = textField_1.getText();
        String out = new String();
        poly1.processPoly(s1);
        poly2.processPoly(s2);
        rez = poly1.mulPoly(poly2);
        out = rez.printP();
        textField_2.setText(out);
      }
    });
    btnX.setFont(new Font("Arial", Font.PLAIN, 50));
    btnX.setBounds(326, 265, 250, 60);
    contentPane.add(btnX);

    JButton button = new JButton("/");
    button.setFont(new Font("Arial", Font.PLAIN, 50));
    button.setBounds(326, 364, 250, 61);
    contentPane.add(button);

    JButton btnDerivate = new JButton("Derivate");
    btnDerivate.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Polynomial rez = new Polynomial();
        Polynomial poly1 = new Polynomial();
        String s1 = textField.getText();
        String out = new String();
        poly1.processPoly(s1);
        rez = poly1.derivePoly();
        out = rez.printP();
        textField_2.setText(out);
      }
    });
    btnDerivate.setFont(new Font("Arial", Font.PLAIN, 50));
    btnDerivate.setBounds(625, 265, 250, 60);
    contentPane.add(btnDerivate);

    JButton btnNewButton = new JButton("Integrate");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Polynomial rez = new Polynomial();
        Polynomial poly1 = new Polynomial();
        String s1 = textField.getText();
        String out = new String();
        poly1.processPoly(s1);
        rez = poly1.integratePoly();
        out = rez.printP();
        textField_2.setText(out);
      }
    });
    btnNewButton.setFont(new Font("Arial", Font.PLAIN, 50));
    btnNewButton.setBounds(625, 364, 250, 61);
    contentPane.add(btnNewButton);
  }
}
