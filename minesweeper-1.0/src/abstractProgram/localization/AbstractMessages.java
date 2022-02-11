package abstractProgram.localization;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public abstract class AbstractMessages {
	private static String BUNDLE_NAME; // = "example.messages"; 	// $NON-NLS-1$		
	private static Locale locale = Locale.getDefault();				// PROGRAM LOCALE
	private static ResourceBundle RESOURCE_BUNDLE; 					// RESOURCE BUNDLE
																	// (STORES LOCALE-
																	// SPECIFIC STRINGS)
	/***********************/
	/* CONSTRUCTOR SECTION */
	/***********************/
	protected AbstractMessages(String BUNDLE_NAME)
	{
		AbstractMessages.BUNDLE_NAME = BUNDLE_NAME; 
		AbstractMessages.RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, locale); 
	}
	
	/************************************************************************************************/
	/*											METHOD SECTION 										*/
	/************************************************************************************************/
	
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
	
	public abstract ArrayList<Locale> programLocales(); 
	/************************************************************************************************/
	/* PRECONDITION:  LIST OF SUPPORTED LOCALES IS NEEDED			  								*/
	/* POSTCONDITION: RETURNS  LIST OF SUPPORTED LOCALES 											*/
	/************************************************************************************************/
	/* example implementation */
	/*
	{
		ArrayList<Locale> locales = new ArrayList<Locale>(); 
		locales.add(new Locale("en", "US")); 
		return locales; 
	}
	*/ 
	
	public static void setLocale(Locale locale)
	/************************************************************************************************/
	/* PRECONDITION:  LOCALE NEEDS TO BE SET TO SPECIFIED LOCALE		  							*/
	/* POSTCONDITION: SETS LOCALE TO SPECIFIED LOCALE 												*/
	/************************************************************************************************/
	{
		AbstractMessages.locale = locale; 
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
