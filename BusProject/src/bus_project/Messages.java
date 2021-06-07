package bus_project;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.swt.widgets.DateTime;

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
	{
		String result; 
		
		MessageFormat formatter = new MessageFormat(""); 
		formatter.setLocale(locale); 
		formatter.applyPattern(RESOURCE_BUNDLE.getString(key)); 
		result = formatter.format(dta); 
		return result; 
	}
	
	public static ArrayList<Locale> programLocales()
	{
		ArrayList<Locale> locales = new ArrayList<Locale>(); 
		locales.add(new Locale("en", "US")); 
		locales.add(new Locale("es", "ES")); 
		locales.add(new Locale("pt", "PT")); 
		return locales; 
	}
	
	public static void setLocale(Locale locale)
	{
		Messages.locale = locale; 
		RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, locale); 		
	}
	
	public static Locale getLocale() 
	{
		return locale; 
	}
	
}
