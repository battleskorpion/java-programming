package bus_project;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public abstract class AbstractProgramWindow {
	
	protected Shell shell;

	/**
	 * Open the window.
	 */
	//public abstract void open(); 

	/**
	* Create contents of the window.
	*/
	//protected abstract void createContents(); 
	
	protected void updateTable(Table tbl, Customer nmbr) 
	/***********************************************************************/
	/* PRECONDITION:  A TABLE NEEDS TO BE UPDATED WITH AN ADDITIONAL VALUE */
	/* POSTCONDITION: ADDS A VALUE TO THE TABLE 						   */
	/***********************************************************************/
	{
		/*********************/
		/* ADD ITEM TO TABLE */
		/*********************/
		TableItem item = new TableItem(tbl, SWT.NULL); 
		item.setText(nmbr.toString()); 
	}
	
	protected void updateTable(Table tbl, ArrayList<Customer> data) 
	/*****************************************************************************/
	/* PRECONDITION:  A TABLE NEEDS TO BE UPDATED WITH AN ARRAY OF NEW VALUES 	 */
	/* POSTCONDITION: CLEARS PREVIOUS TABLE AND ADDS NEW VALUES TO TABLE		 */
	/*****************************************************************************/
	{
		/***************/
		/* RESET TABLE */
		/***************/ 
		tbl.clearAll();							
		tbl.setItemCount(0);
		
		/****************/
		/* UPDATE TABLE */
		/****************/ 
		for (int i = 0; i < data.size(); i++) 
		{
			updateTable(tbl, data.get(i)); 
		}
	}
}
