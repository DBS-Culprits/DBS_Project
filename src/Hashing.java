import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Hashing extends JFrame {
	
	String chkbtn = "";
	JTextArea display_textArea;

	private JPanel contentPane;
	private JTextField key_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hashing frame = new Hashing();
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
	public Hashing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		display_textArea = new JTextArea();
		display_textArea.setBounds(26, 155, 594, 258);
		contentPane.add(display_textArea);
		
		JLabel lblKeyValue = new JLabel("Key Value :");
		lblKeyValue.setBounds(170, 25, 73, 16);
		contentPane.add(lblKeyValue);
		
		key_textField = new JTextField();
		key_textField.setBounds(270, 22, 116, 22);
		contentPane.add(key_textField);
		key_textField.setColumns(10);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				App2 app2 = new App2();
				Module2 mod2 = new Module2();
				chkbtn = "I";
				//TODO
				//Handle error for empty fields
				//System.out.println(app2.bf_textField.getText());
				int bf = Integer.parseInt(app2.bf_textField.getText());
				int gd = Integer.parseInt(app2.gd_textField.getText());
				int ld = Integer.parseInt(app2.ld_textField.getText());
				int key = Integer.parseInt(key_textField.getText());
				BucketList bucketList = mod2.module2(bf,gd,ld,key);
				display_textArea.setText("" + bucketList);
			}
		});
		btnInsert.setBounds(65, 75, 97, 25);
		contentPane.add(btnInsert);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				App2 app2 = new App2();
				Module2 mod2 = new Module2();
				chkbtn = "S";
				int bf = Integer.parseInt(app2.bf_textField.getText());
				int gd = Integer.parseInt(app2.gd_textField.getText());
				int ld = Integer.parseInt(app2.ld_textField.getText());
				int key = Integer.parseInt(key_textField.getText());
				BucketList bucketList = mod2.module2(bf,gd,ld,key);
				display_textArea.setText("" + bucketList);
			}
		});
		btnSearch.setBounds(270, 75, 97, 25);
		contentPane.add(btnSearch);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				App2 app2 = new App2();
				Module2 mod2 = new Module2();
				chkbtn = "D";
				int bf = Integer.parseInt(app2.bf_textField.getText());
				int gd = Integer.parseInt(app2.gd_textField.getText());
				int ld = Integer.parseInt(app2.ld_textField.getText());
				int key = Integer.parseInt(key_textField.getText());
				BucketList bucketList = mod2.module2(bf,gd,ld,key);
				display_textArea.setText("" + bucketList);
			}
		});
		btnDelete.setBounds(475, 75, 97, 25);
		contentPane.add(btnDelete);
		
	}

}
