import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class App2 extends JFrame {
	
	//Hashing hashing = new Hashing();

	private JPanel contentPane;
	JTextField gd_textField;
	JTextField ld_textField;
	JTextField bf_textField;
	private JTextField m_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App2 frame = new App2();
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
	public App2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHashingSimulator = new JLabel("HASHING SIMULATOR");
		lblHashingSimulator.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHashingSimulator.setBounds(191, 33, 196, 27);
		contentPane.add(lblHashingSimulator);
		
		JLabel lblGlobalDepth = new JLabel("Global Depth GD :");
		lblGlobalDepth.setBounds(153, 214, 103, 16);
		contentPane.add(lblGlobalDepth);
		
		gd_textField = new JTextField();
		gd_textField.setBounds(283, 211, 116, 22);
		contentPane.add(gd_textField);
		gd_textField.setColumns(10);
		
		JLabel lblLocalDepth = new JLabel("Local Depth LD :");
		lblLocalDepth.setBounds(153, 161, 103, 16);
		contentPane.add(lblLocalDepth);
		
		ld_textField = new JTextField();
		ld_textField.setColumns(10);
		ld_textField.setBounds(283, 158, 116, 22);
		contentPane.add(ld_textField);
		
		JLabel lblGlobalDepth_1_1 = new JLabel("Blocking Factor Bfr :");
		lblGlobalDepth_1_1.setBounds(140, 110, 116, 16);
		contentPane.add(lblGlobalDepth_1_1);
		
		bf_textField = new JTextField();
		bf_textField.setColumns(10);
		bf_textField.setBounds(283, 107, 116, 22);
		contentPane.add(bf_textField);
		
		JLabel lblGlobalDepth_1_1_1 = new JLabel("Hash Function m :");
		lblGlobalDepth_1_1_1.setBounds(140, 266, 116, 16);
		contentPane.add(lblGlobalDepth_1_1_1);
		
		m_textField = new JTextField();
		m_textField.setColumns(10);
		m_textField.setBounds(283, 263, 116, 22);
		contentPane.add(m_textField);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gd_textField.setText("");
				ld_textField.setText("");
				bf_textField.setText("");
				m_textField.setText("");
			}
		});
		btnReset.setBounds(159, 341, 97, 25);
		contentPane.add(btnReset);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//OPENING HASHING WINDOW
				//TODO
				//check for EMPTY fields and WARN!!!
				String str1 = bf_textField.getText();
				String str2 = gd_textField.getText();
				String str3 = ld_textField.getText();
				String str4=m_textField.getText();
				//int bf=Integer.parseInt(str1);
				Hashing hashing = new Hashing(Integer.parseInt(str1),Integer.parseInt(str2),Integer.parseInt(str3),Integer.parseInt(str4));
				if (str1.isEmpty() || str2.isEmpty() || str3.isEmpty()||str4.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please fill the parameter fields!");
				}
				else
				{
					//TODO
					//Disable any editing on APP2 Window during HASHING Window open. => for avoiding errors because we are taking "initial parameters" input continuously as arguments to execute module2 on every click of button in HASHING
					//setVisible(false);
					if(hashing.isVisible() == true)
					{
						gd_textField.setEditable(false);
						ld_textField.setEditable(false);
						bf_textField.setEditable(false);
						m_textField.setEditable(false);
					}
										
					hashing.setVisible(true);
				}
			}
		});
		btnConfirm.setBounds(302, 341, 97, 25);
		contentPane.add(btnConfirm);
	}
}
