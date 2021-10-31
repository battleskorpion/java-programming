package abstractProgram.localization;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public abstract class AbstractMessages {
	private String BUNDLE_NAME; // = "example.messages"; 			// $NON-NLS-1$		
	private Locale locale = Locale.getDefault();					// PROGRAM LOCALE
	private ResourceBundle RESOURCE_BUNDLE 							// RESOURCE BUNDLE
			= ResourceBundle.getBundle(BUNDLE_NAME, locale); 		// (STORES LOCALE-
																	// SPECIFIC STRINGS)

	/***********************/
	/* CONSTRUCTOR SECTION */
	/***********************/
	public AbstractMessages(String BUNDLE_NAME)
	{
		this.BUNDLE_NAME = BUNDLE_NAME; 
	}
	
	/************************************************************************************************/
	/*											METHOD SECTION 										*/
	/************************************************************************************************/
	
	public String getString(String key) 
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
	
	public String getCompoundString(String key, Object[] dta)
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
	/* note: function can be defined as static
	 * example implementation 
	{
		ArrayList<Locale> locales = new ArrayList<Locale>(); 
		locales.add(new Locale("en", "US")); 
		locales.add(new Locale("es", "ES")); 
		locales.add(new Locale("pt", "PT")); 
		return locales; 
	}
	*/ 
	
	public void setLocale(Locale locale)
	/************************************************************************************************/
	/* PRECONDITION:  LOCALE NEEDS TO BE SET TO SPECIFIED LOCALE		  							*/
	/* POSTCONDITION: SETS LOCALE TO SPECIFIED LOCALE 												*/
	/************************************************************************************************/
	{
		this.locale = locale; 
		RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, locale); 		
	}
	
	public Locale getLocale() 
	/************************************************************************************************/
	/* PRECONDITION:  CURRENT LOCALE IS NEEDED		  												*/
	/* POSTCONDITION: RETURNS LOCALE																*/
	/************************************************************************************************/
	{
		return locale; 
	}
}
