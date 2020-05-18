import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import java.util.*;
import javax.swing.JList;
import javax.swing.*;
import java.awt.*;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.ProgressBar;
public class App {
	
	static Button btnNormalize;
	static Shell shell;
	static String val;
	
	private static Text txtFunction;
	private static Text text;
	Vector<String> vec=new Vector<String>(4); 
	private static Text text_1;
	private static Text text_2;
	/**
	 * @wbp.nonvisual location=110,234
	 */

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Module1 obj=new Module1();
		Display display = Display.getDefault();
		shell = new Shell();
		shell.setSize(668, 467);
		shell.setText("Normalization Simulator");
		
		Label lblVariables = new Label(shell, SWT.NONE);
		lblVariables.setBounds(106, 22, 70, 20);
		lblVariables.setText("Variables :");
		
		txtFunction = new Text(shell, SWT.BORDER);
		//txtFunction.setText("");
		txtFunction.setBounds(193, 19, 254, 26);
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(193, 64, 443, 26);
		
		Label lblFuntionalDependencies = new Label(shell, SWT.NONE);
		lblFuntionalDependencies.setBounds(10, 67, 166, 26);
		lblFuntionalDependencies.setText("Funtional Dependencies :");
		
		Combo combo = new Combo(shell, SWT.NONE | SWT.READ_ONLY);
		combo.setBounds(37, 180, 107, 28);
		combo.setText("Show Keys");
			
		Button btnFindPrimaryKeys = new Button(shell, SWT.NONE);
		
		btnFindPrimaryKeys.addMouseListener(new MouseAdapter() {
			
			public void mouseDown(MouseEvent e) {
				//static JList list=new JList<String>();
			//	 list=new JList(vec.toString());
			//	CandKey="";
				//EMPTY COMBO BOX DROP DOWN
				combo.removeAll();
				String str=txtFunction.getText();
				String str1=text.getText();
				//TODO
				//check for empty str Variables
				//TODO
				//Exception Handling for str1 FDs (spaces not allowed!)
				Vector<String> vec=new Vector<String>(4);
				String res=obj.module1(str,str1,vec);
				Iterator value = vec.iterator();
				while (value.hasNext()) { 
		         //   System.out.println(value.next());
					combo.add(value.next().toString());
		        }
				//combo.add(vec.toString());

			}
		});
		btnFindPrimaryKeys.setBounds(24, 131, 133, 30);
		btnFindPrimaryKeys.setText("Find Super Keys");
		
		text_1 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_1.setBounds(250, 180, 95, 26);
		
		Button btnMinimumCandidateKey = new Button(shell, SWT.NONE);
		btnMinimumCandidateKey.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String str=txtFunction.getText();
				String str1=text.getText();	
				Vector<String> vec=new Vector<String>(4);
				String res=obj.module1(str,str1,vec);
				if (!str.isEmpty()) {
					text_1.setText(res);
				}	
			}
		});
		btnMinimumCandidateKey.setBounds(229, 131, 133, 30);
		btnMinimumCandidateKey.setText("Primary Key");
		
		text_2 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_2.setBounds(264, 347, 64, 26);
		
		Button btnNormalForm = new Button(shell, SWT.NONE);
		btnNormalForm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String str=txtFunction.getText();
				String str1=text.getText();
				Vector<String> vec=new Vector<String>(4);
				String res=obj.module1(str,str1,vec);
				if (!str.isEmpty()) {
					text_2.setText(val);
				}
			}
		});
		btnNormalForm.setBounds(244, 300, 107, 30);
		btnNormalForm.setText("Normal Form");
		
		btnNormalize = new Button(shell, SWT.NONE);
		btnNormalize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Normalization obj2 = new Normalization();
				
				String str=txtFunction.getText();
				String str1=text.getText();
				Vector<String> vec=new Vector<String>(4);
				
				Module1.relations = "";
				Module1.relations = "After Normalization :-";
				String res=obj.module1(str,str1,vec);
				if (!str.isEmpty()) {
					obj2.relations_textArea.setText(Module1.relations);
					//TODO
					//Disable Normalize btn if BCNF
				
					//obj2.relations_textArea.append("hello");
					obj2.setVisible(true);
				}
			}
		});
		btnNormalize.setBounds(459, 228, 145, 50);
		btnNormalize.setText("Normalize");
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(179, 131, 30, 264);
				
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
