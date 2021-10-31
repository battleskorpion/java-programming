/********************************************/
/* INFORMATION SECTION 						*/
/* AbstractProgramWindow.java				*/
/* Darian Siembab 							*/
/* 											*/
/* ABSTRACT WINDOW CLASS TO BE INHERITED 	*/
/* BY WINDOW CLASSES, INCLUDES COMMON		*/
/* METHODS AND VARIABLES 					*/
/********************************************/ 

package abstractProgram.abstractProgram;

/******************/
/* IMPORT SECTION */
/******************/
import java.time.LocalDate;								// A DATE WITHOUT A TIME-ZONE IN THE ISO-8601 
														// CALENDAR SYSTEM
import java.util.ArrayList;								// RESIZABLE-ARRAY IMPLEMENTATION OF THE LIST
														// INTERFACE.
import java.util.function.Function; 					// REPRESENTS A FUNCTION THAT ACCEPTS ONE 
														// ARGUMENT AND PRODUCES A RESULT. 
import org.eclipse.swt.SWT;								// THIS CLASS PROVIDES ACCESS TO A SMALL 
														// NUMBER OF SWT SYSTEM-WIDE METHODS, AND
														// IN ADDITION DEFINES THE PUBLIC CONSTANTS 
														// PROVIDED BY SWT. 
import org.eclipse.swt.widgets.Combo;					// FOR CONTROLS THAT ALLOW THE USER TO 
														// CHOOSE AN ITEM FROM A LIST OF ITEMS,
														// OR OPTIONALLY ENTER A NEW VALUE BY
														// TYPING IT INTO AN EDITABLE TEXT FIELD.
import org.eclipse.swt.widgets.DateTime;				// FOR SELECTABLE USER INTERFACE OBJECTS THAT
														// ALLOW THE USER TO ENTER AND MODIFY DATE OR 
														// TIME VALUES. 
import org.eclipse.swt.widgets.Shell;					// REPRESENTS THE "WINDOWS" WHICH THE DESKTOP
														// OR "WINDOW MANAGER" IS MANAGING. 			
import org.eclipse.swt.widgets.Table;					// FOR A SELECTABLE USER INTERFACE OBJECT THAT 
														// DISPLAYS A LIST OF IMAGES AND STRINGS AND 
														// ISSUES NOTIFICATION WHEN SELECTED. 
import org.eclipse.swt.widgets.TableItem;				// FOR A SELECTABLE USER INTERFACE OBJECT THAT
														// REPRESENTS AN ITEM IN A TABLE. 
import org.eclipse.swt.widgets.Text;					// FOR SELECTABLE USER INTERFACE OBJECTS THAT
														// ALLOW THE USER TO ENTER AND MODIFY TEXT.

public abstract class AbstractProgramWindow 
{
	/********************/
	/* VARIABLE SECTION */
	/********************/ 
	protected Shell shell;								// SHELL WHICH REPRESENTS THIS WINDOW

	/************************************************************************************************/
	/*										METHOD SECTION 			  				  				*/
	/************************************************************************************************/

	public void open(Shell shell)
	/************************************************************************************************/
	/* PRECONDITION:  WINDOW NEEDS TO BE DISPLAYED ON SCREEN 				  						*/
	/* POSTCONDITION: SHELL OF WINDOW IS OPENED (WINDOW DISPLAYED ON SCREEN)  						*/
	/************************************************************************************************/
	{
		/***********************************************/
		/* METHOD TO OPEN SHELL 					   */
		/* (OPEN WINDOW REPRESENTED BY SHELL ON SCREEN */
		/***********************************************/
		shell.open(); 
	}
	
	public void close(Shell shell)
	/************************************************************************************************/
	/* PRECONDITION:  WINDOW NEEDS TO BE CLOSED 				  			  						*/
	/* POSTCONDITION: SHELL OF WINDOW IS CLOSED (WINDOW IS NO LONGER DISPLAYED ON SCREEN)  			*/
	/************************************************************************************************/
	{
		/***************************************/
		/* METHOD TO CLOSE SHELL 			   */
		/* (CLOSE WINDOW REPRESENTED BY SHELL) */
		/***************************************/
		shell.close(); 
	}
	
	/************************************************************************************************/
	/* PRECONDITION:  SUB WINDOW NEEDS TO BE CLOSED 				  			  					*/
	/* POSTCONDITION: SHELL OF WINDOW IS CLOSED (WINDOW IS NO LONGER DISPLAYED ON SCREEN), AND THE  */
	/*				  ROOT WINDOW IS FORCED ACTIVE (DISPLAYED ON TOP) 								*/
	/************************************************************************************************/
	public void closeSubWindow(Shell rootShell, Shell shell)
	{
		/********************************************************************/
		/* METHOD TO FORCE ROOT SHELL ACTIVE   								*/
		/* (IF THE RECEIVER IS VISIBLE, MOVES IT TO THE TOP OF THE DRAWING	*/
		/*  ORDER FOR THE DISPLAY ON WHICH IT WAS CREATED (SO THAT ALL 		*/
		/*  OTHER SHELLS ON THAT DISPLAY, WHICH ARE NOTTHE RECEIVER'S 		*/
		/*  CHILDREN WILL BE DRAWN BEHIND IT) AND FORCES THE WINDOW MANAGER	*/
		/*  TO MAKE THE SHELL ACTIVE.) 										*/
		/********************************************************************/
		rootShell.forceActive(); 
		
		/***************************************/
		/* METHOD TO CLOSE SHELL 			   */
		/* (CLOSE WINDOW REPRESENTED BY SHELL) */
		/***************************************/
		shell.close(); 
	}
	
	protected void updateTable(Table tbl, Object dta) 
	/************************************************************************************************/
	/* PRECONDITION:  A TABLE NEEDS TO BE UPDATED WITH AN ADDITIONAL VALUE	 						*/
	/* POSTCONDITION: ADDS A VALUE TO THE TABLE 						   	  						*/
	/************************************************************************************************/
	{
		/*********************/
		/* ADD ITEM TO TABLE */
		/*********************/
		TableItem item = new TableItem(tbl, SWT.NULL); 
		
		/*************************/
		/* SET ITEM TEXT TO DATA */
		/*************************/
		item.setText(dta.toString()); 
	}
	
	protected void updateTable(Table tbl, String[] dta) 
	/************************************************************************************************/
	/* PRECONDITION:  A TABLE NEEDS TO BE UPDATED WITH AN ADDITIONAL VALUE 	  						*/
	/* POSTCONDITION: ADDS A VALUE TO THE TABLE 						   	  						*/
	/************************************************************************************************/
	{
		/*********************/
		/* ADD ITEM TO TABLE */
		/*********************/
		TableItem item = new TableItem(tbl, SWT.NULL); 
		
		/*************************/
		/* SET ITEM TEXT TO DATA */
		/*************************/
		item.setText(dta); 
	}
	
	protected <E> void updateTable(Table tbl, ArrayList<E> dta) 
	/************************************************************************************************/
	/* PRECONDITION:  A TABLE NEEDS TO BE UPDATED WITH AN ARRAY OF NEW VALUES 						*/
	/* POSTCONDITION: CLEARS PREVIOUS TABLE AND ADDS NEW VALUES TO TABLE	  						*/
	/************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		int columnCount = tbl.getColumnCount(); 		// NUMBER OF COLUMN OBJECTS IN TABLE 
														// (TABLE CAN HAVE ONE PHYSICAL COLUMN 
														// BUT 0 COLUMN OBJECTS)
		
		/***************/
		/* RESET TABLE */
		/***************/ 
		
		/************************************************/
		/* METHOD CALL TO CLEAR ALL ELEMENTS FROM TABLE */
		/************************************************/
		tbl.clearAll();							
		
		/********************************************/
		/* METHOD CALL TO SET TABLE ITEM COUNT TO 0 */
		/* (SO NEW DATA WILL BE ADDED AT THE TOP)   */
		/********************************************/
		tbl.setItemCount(0);
		
		/****************/
		/* UPDATE TABLE */
		/****************/ 

		/****************************************************/
		/* FOR LOOP TO UPDATE TABLE BY ROW WITH DATA IN dta */
		/****************************************************/
		for (int i = 0; i < dta.size(); i++) 
		{
			/************************************************************/
			/* IF STATEMENT TO UPDATE TABLE DIFFERENTLY DEPENDING ON IF */
			/* IT IS MADE UP OF MUTIPLE COLUMNS 						*/
			/************************************************************/ 
			if (columnCount <= 1) 
			{
				/*************************************/
				/* METHOD CALL TO UPDATE TABLE WITH  */
				/* DATA AT i IN dta					 */
				/*************************************/
				updateTable(tbl, dta.get(i)); 
			}
			else 
			{
				/************************************/
				/* METHOD CALL TO UPDATE TABLE WITH */
				/* ARRAY OF DATA AT i IN dta		*/
				/************************************/
				updateTable(tbl, dta.get(i).toString().split("\n")); 
			}
		}
	}
	
	protected <E> void updateTable(Table tbl, ArrayList<E> dta, 
			Function<E, String> addtlDataFunction) 
	/************************************************************************************************/
	/* PRECONDITION:  A TABLE NEEDS TO BE UPDATED WITH AN ARRAY OF NEW VALUES 						*/
	/* POSTCONDITION: CLEARS PREVIOUS TABLE AND ADDS NEW VALUES TO TABLE	  						*/
	/************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/ 
		int columnCount = tbl.getColumnCount(); 		// NUMBER OF COLUMN OBJECTS IN TABLE 
														// (TABLE CAN HAVE ONE PHYSICAL COLUMN 
														// BUT 0 COLUMN OBJECTS)
		
		/***************/
		/* RESET TABLE */
		/***************/ 
		
		/************************************************/
		/* METHOD CALL TO CLEAR ALL ELEMENTS FROM TABLE */
		/************************************************/
		tbl.clearAll();							
		
		/********************************************/
		/* METHOD CALL TO SET TABLE ITEM COUNT TO 0 */
		/* (SO NEW DATA WILL BE ADDED AT THE TOP)   */
		/********************************************/
		tbl.setItemCount(0);
		
		/****************/
		/* UPDATE TABLE */
		/****************/ 

		/****************************************************/
		/* FOR LOOP TO UPDATE TABLE BY ROW WITH DATA IN dta */
		/****************************************************/
		for (int i = 0; i < dta.size(); i++) 
		{
			if (columnCount <= 1) 
			{
				/*************************************/
				/* METHOD CALL TO UPDATE TABLE WITH  */
				/* DATA AT i IN dta					 */
				/* AS WELL AS ADDITIONAL DATA 		 */
				/*************************************/
				updateTable(tbl, dta.get(i) + ", " 
						+ addtlDataFunction.apply(dta.get(i))); 
			}
			else 
			{ 
				/************************************/
				/* METHOD CALL TO UPDATE TABLE WITH */
				/* ARRAY OF DATA AT i IN dta		*/
				/* AS WELL AS ADDITIONAL DATA		*/
				/************************************/
				updateTable(tbl, (dta.get(i).toString() + "\n" 
						+ addtlDataFunction.apply(dta.get(i))).split("\n")); 
			}
		}
	}
	
	protected <E> void updateComboBox(Combo bx, ArrayList<E> dta) 
	/************************************************************************************************/
	/* PRECONDITION:  A COMBO BOX NEEDS TO BE UPDATED WITH AN ARRAY OF NEW VALUES 					*/
	/* POSTCONDITION: CLEARS PREVIOUS VALUES AND ADDS NEW VALUES TO COMBO BOX	  					*/
	/************************************************************************************************/
	{
		/************************************************************/
		/* IF STATEMENT TO UPDATE COMBO BOX IF THERE IS DATA IN dta */
		/************************************************************/ 
		if (dta.size() > 0) 
		{	
			/**************************************/
			/* METHOD CALL TO FILL COMBO BOX WITH */
			/* DATA IN dta (SPLITS DATA IN dta BY */
			/* COMMA AND REMOVES [] FROM toString */
			/**************************************/
			bx.setItems(dta.toString().substring(1, dta.toString().length() - 1).split(", ")); 	
		}
	}

	protected void clearInput(Text[] flds) 
	/************************************************************************************************/
	/* PRECONDITION:  TEXT FIELDS NEED TO BE CLEARED 												*/
	/* POSTCONDITION: CLEARS ALL INPUT IN TEXT FIELDS 												*/
	/************************************************************************************************/
	{
		/***********************************************************/
		/* FOR LOOP TO CLEAR TEXT FIELDS (SET TEXT TO EMPTY STRING */
		/***********************************************************/
		for (int i = 0; i < flds.length; i++) 
		{
			flds[i].setText("");
		}
	}

	protected String dateTimeToLocalDateStringFormat(int dy, int mnth, int yr)
	/************************************************************************************************/
	/* PRECONDITION:  A DAY, MONTH, AND YEAR IN LocalDate FORMAT NEED TO BE CONVERTED TO A STRING 	*/
	/*				  IN DateTime FORMAT															*/
	/* POSTCONDITION: CONVERTS A DAY, MONTH, AND YEAR IN LocalDate FORMAT TO A STRING IN DateTime 	*/
	/*				  PARSEABLE FORMAT																*/																
	/************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		String dateString; 
		
		/****************************************************/
		/* START DATE STRING AS THE YEAR + A DASH SEPARATOR */
		/****************************************************/ 
		dateString = yr + "-"; 
		
		/****************************************************************/
		/* INCREMENT MONTH (DateTime FORMAT IS 0-11, LocalDate is 1-12) */
		/****************************************************************/ 
		mnth++; 
		
		/*****************************************************/
		/* IF MONTH IS LESS THAN 10 ADD A 0 TO DATE STRING   */
		/* (LocalDate REQUIRES A 0 FOR MONTHS AND DAYS < 10) */
		/*****************************************************/ 
		if (mnth < 10) 
		{
			dateString += "0";  
		}
		
		/***********************************************/
		/* ADD MONTH AND DASH SEPARATOR TO DATE STRING */
		/***********************************************/ 
		dateString += mnth + "-";
		
		/*****************************************************/
		/* IF DAY IS LESS THAN 10 ADD A 0 TO DATE STRING     */
		/* (LocalDate REQUIRES A 0 FOR MONTHS AND DAYS < 10) */
		/*****************************************************/ 
		if (dy < 10) 
		{
			dateString += "0"; 
		}
		
		/**************************/
		/* ADD DAY TO DATE STRING */
		/**************************/
		dateString += dy; 

		/**********************/
		/* RETURN DATE STRING */
		/**********************/
		return dateString; 
	}
	
	protected boolean vaildDate(DateTime dateTime) 		
	/************************************************************************************************/
	/* PRECONDITION:  A DATE NEEDS TO BE DETERMINED TO BE A VALID DATE 								*/
	/* POSTCONDITION: DETERMINES IF THE DATE IS A VALID DATE (DATE IS AFTER TODAY) 					*/															
	/************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		LocalDate date = LocalDate.parse(dateTimeToLocalDateStringFormat		// DATE REPRESENTED 
				(dateTime.getDay(), dateTime.getMonth(), dateTime.getYear()));	// BY dateTime OBJECT
																				// (CONVERTED TO 
																				// LocalDate FORMAT) 
		
		/***********************************************************/
		/* IF STATEMENT TO RETURN TRUE IF THE DATE IS AFTER TODAY, */
		/* FALSE IF THE DATE IS TODAY'S DATE OR BEFORE TODAY 	   */
		/***********************************************************/ 
		if (date.isAfter(LocalDate.now()))	
		{			
			return true;
		}
		else 
		{
			return false; 
		}
	}
	
	protected void openSubWindow(AbstractProgramWindow wndw, Shell shl) 
	/************************************************************************************************/
	/* PRECONDITION:  A SUB WINDOW OF THE WINDOW REPRESENTED BY THE SHELL OBJECT NEEDS TO BE OPENED */
	/*				  (shl SHOULD BE A SHELL REPRESENTING AN EXISTING WINDOW)						*/
	/* POSTCONDITION: A SPECIFIED WINDOW (wndw) IS OPENED USING shl AS ITS' SHELL					*/													
	/************************************************************************************************/
	{
		/**********************************************/
		/* DISABLE ROOT SHELL WHILE PERFORMING ACTION */
		/**********************************************/
		shl.setEnabled(false);
		
		/***************/
		/* OPEN WINDOW */
		/***************/
		wndw.open(shl);
			
		/*********************************************/
		/* ENABLE ROOT SHELL AFTER PERFORMING ACTION */
		/*********************************************/
		shl.setEnabled(true);
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shl.forceActive();	
	}

	/**
	 * To fix issue with reorganization, should work. Calls open(shell) 
	 */
	public void open() {
		// TODO Auto-generated method stub
		open(shell); 
	}

	/**
	 * To fix issue with reorganization, should work. Calls close(shell) 
	 */
	public void close() {
		// TODO Auto-generated method stub
		close(shell); 
	}
	
}
