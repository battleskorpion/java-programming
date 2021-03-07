package bus_project;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class BusProgramMenu {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BusProgramMenu window = new BusProgramMenu();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmAddCustomer = new MenuItem(menu, SWT.NONE);
		mntmAddCustomer.setText("New Item");
		
		MenuItem mntmDeleteCustomer = new MenuItem(menu, SWT.NONE);
		mntmDeleteCustomer.setText("New Item");
		
		MenuItem mntmModifyCustomers = new MenuItem(menu, SWT.NONE);
		mntmModifyCustomers.setText("New Item");
		
		MenuItem mntmListCustomersByAlphabetical = new MenuItem(menu, SWT.NONE);
		mntmListCustomersByAlphabetical.setText("New Item");
		
		MenuItem mntmListCustomersBySize = new MenuItem(menu, SWT.NONE);
		mntmListCustomersBySize.setText("New Item");
		
		MenuItem mntmListBusesByDate = new MenuItem(menu, SWT.NONE);
		mntmListBusesByDate.setText("New Item");
		
		MenuItem mntmNewItem_6 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_6.setText("New Item");
		
		MenuItem mntmNewItem_7 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_7.setText("New Item");

	}
}
