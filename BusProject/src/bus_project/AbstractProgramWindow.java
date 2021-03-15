package bus_project;

import java.time.LocalDate;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

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
	
	protected void updateTable(Table tbl, Object dta) 
	/***********************************************************************/
	/* PRECONDITION:  A TABLE NEEDS TO BE UPDATED WITH AN ADDITIONAL VALUE */
	/* POSTCONDITION: ADDS A VALUE TO THE TABLE 						   */
	/***********************************************************************/
	{
		/*********************/
		/* ADD ITEM TO TABLE */
		/*********************/
		TableItem item = new TableItem(tbl, SWT.NULL); 
		item.setText(dta.toString()); 
	}
	
	protected <E> void updateTable(Table tbl, ArrayList<E> data) 
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
	
	protected void setCustomerDetails(Customer customer, Text nameField, Text sizeField, Text indexField, int index, DateTime dateTime) {
		LocalDate tripDate; 
		String tripDateString; 
		int year;
		int month; 
		int day; 

		customer.setName(nameField.getText()); 
		customer.setNumPersons(Integer.parseInt(sizeField.getText())); 
		customer.setIndex(index);
		year = dateTime.getYear(); 
		month = dateTime.getMonth(); 
		day = dateTime.getDay(); 
		tripDateString = year + "-"; 
		if (month < 10) {
			tripDateString += "0";  
		}
		tripDateString += (month + 1) + "-";
		tripDateString += day; 
		
		tripDate = LocalDate.parse(tripDateString); 
		customer.setDate(tripDate); 
	}
	
	protected void clearInput(Text[] fields) {
		for (Text field : fields) {
			field.setText("");
		}
	}

	protected void updateIndex(ArrayList<Customer> cstmrs) {
		for (int i = 0; i < cstmrs.size(); i++) {
			cstmrs.get(i).setIndex(i);
		}
	}
}
