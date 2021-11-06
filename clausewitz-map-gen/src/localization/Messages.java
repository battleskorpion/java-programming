/********************************************/
/* INFORMATION SECTION 						*/
/* Messages.java							*/
/* Darian Siembab 							*/
/* 											*/
/* CLASS FOR OBTAINING MESSAGES TO BE 		*/
/* DISPLAYED BY OBTAINING THEIR KEY			*/
/* AND ACCESSING THE APPROPIATE MESSAGES 	*/
/* FILE DEPENDING ON THE CURRENT LOCALE		*/
/* (ALLOWS THE PROGRAM TO BE LOCALIZED AND  */
/* TEXT TO CHANGE LANGUAGE, ETC. DEPENDING  */
/* ON THE SPECIFIED LOCALE)					*/
/********************************************/ 
package localization;

/******************/
/* IMPORT SECTION */
/******************/
import java.util.ArrayList;								// RESIZABLE-ARRAY IMPLEMENTATION OF THE LIST
														// INTERFACE.
import java.util.Locale;								// A LOCALE OBJECT REPRESENTS A SPECIFIC 
														// GEOGRAPHICAL, POLITICAL, OR CULTURAL 
														// REGION.
import abstractProgram.localization.*; 					// PARENT CLASS/ETC. 
														
public class Messages extends AbstractMessages
{
	/***********************/
	/* CONSTRUCTOR SECTION */
	/***********************/
	public Messages()
	{
		super("localization.messages"); 
	}
	
	@Override
	public ArrayList<Locale> programLocales()
	/************************************************************************************************/
	/* PRECONDITION:  LIST OF SUPPORTED LOCALES IS NEEDED			  								*/
	/* POSTCONDITION: RETURNS  LIST OF SUPPORTED LOCALES 											*/
	/************************************************************************************************/
	{
		ArrayList<Locale> locales = new ArrayList<Locale>(); 
		locales.add(new Locale("en", "US")); 
		//locales.add(new Locale("es", "ES")); 
		//locales.add(new Locale("pt", "PT")); 
		return locales; 
	}
	
}
