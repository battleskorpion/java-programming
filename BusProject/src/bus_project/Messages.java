package bus_project;

import java.util.ArrayList;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages
{
	private static final String BUNDLE_NAME = "bus_project.messages"; //$NON-NLS-1$
	private static Locale locale = Locale.getDefault(); 	//= new Locale("es", "ES");
	private static ArrayList<Locale> locales; 
	private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, locale);

	private Messages()
	{
		locales = programLocales(); 
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
	
	public static ArrayList<Locale> programLocales()
	{
		ArrayList<Locale> locales = new ArrayList<Locale>(); 
		locales.add(new Locale("en", "US")); 
		locales.add(new Locale("es", "ES")); 
		
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
