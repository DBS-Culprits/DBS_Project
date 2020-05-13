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
import org.eclipse.swt.widgets.Combo;
public class App {
	private static Text txtFunction;
	private static Text text;
	Vector<String> vec=new Vector<String>(4); 
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
		Shell shell = new Shell();
		shell.setSize(668, 467);
		shell.setText("SWT Application");
		
		Label lblVariables = new Label(shell, SWT.NONE);
		lblVariables.setBounds(23, 22, 70, 20);
		lblVariables.setText("Variables");
		
		txtFunction = new Text(shell, SWT.BORDER);
		//txtFunction.setText("");
		txtFunction.setBounds(115, 16, 254, 26);
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(178, 67, 443, 26);
		
		Label lblFuntionalDependencies = new Label(shell, SWT.NONE);
		lblFuntionalDependencies.setBounds(10, 67, 162, 26);
		lblFuntionalDependencies.setText("Funtional Dependencies");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(65, 167, 97, 28);;
		
		Button btnFindPrimaryKeys = new Button(shell, SWT.NONE);
		
		btnFindPrimaryKeys.addMouseListener(new MouseAdapter() {
			
			public void mouseDown(MouseEvent e) {
				//static JList list=new JList<String>();
			//	 list=new JList(vec.toString());
				String str=txtFunction.getText();
				String str1=text.getText();
				Vector<String> vec=new Vector<String>(4);
				obj.module1(str,str1,vec);
				Iterator value = vec.iterator();
				while (value.hasNext()) { 
		         //   System.out.println(value.next());
					
					combo.add(value.next().toString());
		        }
				//combo.add(vec.toString());
			}
		});
		btnFindPrimaryKeys.setBounds(65, 131, 133, 30);
		btnFindPrimaryKeys.setText("Find Primary Keys");
		
		

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
