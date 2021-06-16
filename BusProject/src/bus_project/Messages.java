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
package bus_project;

/******************/
/* IMPORT SECTION */
/******************/
import java.text.MessageFormat;							// MESSAGE FORMAT PROVIDES A MEANS TO PRODUCE 
														// CONCATENATED MESSAGES IN A LANGUAGE-NEUTRAL
														// WAY. USE THIS TO CONSTRUCT MESSAGES 
														// DISPLAYED FOR END USERS. 
import java.time.LocalDate;								// FOR STORING DATES
import java.util.ArrayList;								// RESIZABLE-ARRAY IMPLEMENTATION OF THE LIST
														// INTERFACE.
import java.util.Locale;								// A LOCALE OBJECT REPRESENTS A SPECIFIC 
														// GEOGRAPHICAL, POLITICAL, OR CULTURAL 
														// REGION.
import java.util.MissingResourceException;				// SIGNALS THAT A RESOURCE IS MISSING.
import java.util.ResourceBundle;						// RESOURCE BUNDLES CONTAIN LOCALE-SPECIFIC 
														// OBJECTS. (EX: STRINGS)
public class Messages
{
	private static final String BUNDLE_NAME = "bus_project.messages"; 					//$NON-NLS-1$
	private static Locale locale = Locale.getDefault();			
	private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, locale); 
	
	/***********************/
	/* CONSTRUCTOR SECTION */
	/***********************/
	public Messages()
	{
		
	}

	public static String getString(String key) 
	/************************************************************************************************/
	/* PRECONDITION:  LOCALIZED STRING IS NEEDED WHICH RELATES TO KEY				  				*/
	/* POSTCONDITION: RETURNS CORRESPONDING STRING OF KEY IN CURRENT LOCALE			  				*/
	/************************************************************************************************/
	{
		try 
		{
			return RESOURCE_BUNDLE.getString(key);
		}
		catch (MissingResourceException e) 
		{
			return '!' + key + '!';
		}
	}
	
	public static String getCompoundString(String key, Object[] dta)
	/************************************************************************************************/
	/* PRECONDITION:  LOCALIZED COMPOUND STRING IS NEEDED WHICH RELATES TO KEY				  		*/
	/* POSTCONDITION: RETURNS CORRESPONDING COMPOUND STRING OF KEY IN CURRENT LOCALE			  	*/
	/************************************************************************************************/
	{
		String result; 
		
		MessageFormat formatter = new MessageFormat(""); 
		formatter.setLocale(locale); 
		formatter.applyPattern(RESOURCE_BUNDLE.getString(key)); 
		result = formatter.format(dta); 
		return result; 
	}
	
	public static ArrayList<Locale> programLocales()
	/************************************************************************************************/
	/* PRECONDITION:  LIST OF SUPPORTED LOCALES IS NEEDED			  								*/
	/* POSTCONDITION: RETURNS  LIST OF SUPPORTED LOCALES 											*/
	/************************************************************************************************/
	{
		ArrayList<Locale> locales = new ArrayList<Locale>(); 
		locales.add(new Locale("en", "US")); 
		locales.add(new Locale("es", "ES")); 
		locales.add(new Locale("pt", "PT")); 
		return locales; 
	}
	
	public static void setLocale(Locale locale)
	/************************************************************************************************/
	/* PRECONDITION:  LOCALE NEEDS TO BE SET TO SPECIFIED LOCALE		  							*/
	/* POSTCONDITION: SETS LOCALE TO SPECIFIED LOCALE 												*/
	/************************************************************************************************/
	{
		Messages.locale = locale; 
		RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, locale); 		
	}
	
	public static Locale getLocale() 
	/************************************************************************************************/
	/* PRECONDITION:  CURRENT LOCALE IS NEEDED		  												*/
	/* POSTCONDITION: RETURNS LOCALE																*/
	/************************************************************************************************/
	{
		return locale; 
	}
	
}
