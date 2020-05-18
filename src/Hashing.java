import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Hashing extends JFrame {
	
	String chkbtn = "";
	JTextArea display_textArea;

	//App2 app2 = new App2();
//	Module2 mod2 = new Module2();
	
	private JPanel contentPane;
	private JTextField key_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,int bf,int gd,int ld,int m) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hashing frame = new Hashing(bf,gd,ld,m);
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
	public Hashing(int bf,int gd,int ld,int m) {
		BucketList bucketlist=new BucketList(gd,ld,bf,m);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 668, 494);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 222, 173));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		display_textArea = new JTextArea(10,30);
		display_textArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
		//display_textArea.setBounds(26, 155, 594, 258);
		//contentPane.add(display_textArea);
		JScrollPane jsp = new JScrollPane (display_textArea);
		jsp.setBounds(26, 155, 594, 258);
		contentPane.add(jsp);
		
		JLabel lblKeyValue = new JLabel("Key Value :");
		lblKeyValue.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblKeyValue.setForeground(new Color(0, 0, 0));
		lblKeyValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblKeyValue.setBounds(170, 25, 73, 16);
		contentPane.add(lblKeyValue);
		
		key_textField = new JTextField();
		key_textField.setBounds(270, 22, 116, 22);
		contentPane.add(key_textField);
		key_textField.setColumns(10);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnInsert.setForeground(new Color(250, 250, 210));
		btnInsert.setBackground(new Color(160, 82, 45));
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chkbtn = "I";
				//TODO
				//Handle error for empty fields
				//Maybe check for duplicates using searchkey here?
				//System.out.println(app2.bf_textField.getText());
				int key = Integer.parseInt(key_textField.getText());
				if(key <= 0)
				{
					JOptionPane.showMessageDialog(null, "Please enter a positive key!");
				}
				else
				{
					bucketlist.insertKey(key);
					//BucketList bucketList = mod2.module2(bf,gd,ld,key);
					display_textArea.setText("Inserted " + key + " : " + "\n");
					display_textArea.append("" + bucketlist);
				}
			}
		});
		btnInsert.setBounds(65, 75, 97, 25);
		contentPane.add(btnInsert);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnSearch.setForeground(new Color(250, 250, 210));
		btnSearch.setBackground(new Color(160, 82, 45));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	chkbtn = "S";
				int key = Integer.parseInt(key_textField.getText());
				if(key <= 0)
				{
					JOptionPane.showMessageDialog(null, "Please enter a positive key!");
				}
				else
				{
				//	BucketList bucketList = mod2.module2(bf,gd,ld,key);
					if(bucketlist.searchKey(key))
		              {
		                  System.out.println("Present");
		                  display_textArea.setText("Present" + "\n");
		              } 
		              else
		              {
		                  System.out.println("Absent");
		                  display_textArea.setText("Absent" + "\n");
		              }
					display_textArea.append("" + bucketlist);
				}
			}
		});
		btnSearch.setBounds(270, 75, 97, 25);
		contentPane.add(btnSearch);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnDelete.setForeground(new Color(250, 250, 210));
		btnDelete.setBackground(new Color(160, 82, 45));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				//Maybe check if present or not in bucketlist using searchkey here?
			//	chkbtn = "D";
				int key = Integer.parseInt(key_textField.getText());
				if(key <= 0)
				{
					JOptionPane.showMessageDialog(null, "Please enter a positive key!");
				}
				else
				{
					if(bucketlist.searchKey(key))
					{
						bucketlist.deleteKey(key);
					//	BucketList bucketList = mod2.module2(bf,gd,ld,key);
						display_textArea.setText("Deleted " + key + " : " + "\n");
						display_textArea.append(bucketlist.delete_display);		
					}
					else
					{
						display_textArea.setText("Absent or deleted " + key + " already." + "\n");
					}
					
					display_textArea.append("" + bucketlist);
				}
			}
		});
		btnDelete.setBounds(475, 75, 97, 25);
		contentPane.add(btnDelete);
		
	}

}
