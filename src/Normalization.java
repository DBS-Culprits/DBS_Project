import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;

public class Normalization extends JFrame {

	private JPanel contentPane;
	
	JTextArea relations_textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Normalization frame = new Normalization();
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
	public Normalization() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 684, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		relations_textArea = new JTextArea();
		relations_textArea.setFont(new Font("SansSerif", Font.PLAIN, 18));
		relations_textArea.setBounds(64, 60, 538, 306);
		relations_textArea.setEditable(false);
		contentPane.add(new JScrollPane(relations_textArea),BorderLayout.CENTER);
	}

}
