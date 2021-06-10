package test;

import java.util.ArrayList;
import javax.swing.JOptionPane;										// FOR JOPTIONPANE DIALOG WINDOWS

public class Test 
{
	public static ArrayList<String> windowArgList = new ArrayList<String>(); 
	
	public static void main(String[] args) 
	{
		initArgList(); 
			
		if(args.length > 0)
		{
			if (!createWindow(args))
			{
				JOptionPane.showMessageDialog(null, "Error, argument 1 may have been wrong: " 
						+ args[0] + "\nList of window type arguments: " + windowArgList.toString());
			}
		}
	
	}
	
	private static void initArgList()
	{
		windowArgList.add("JOptionPane_error_window");
		windowArgList.add("JOptionPane_message_window"); 
		windowArgList.add("JOptionPane_option_window");
	}
	
	private static boolean createWindow(String[] args)
	{
		switch(args[0])
		{
		case "JOptionPane_error_window": 				//windowArgList.get(0)
			//createErrorWindow(args); 
			break; 
		case "JOptionPane_message_window": 				//windowArgList.get(1)
			createMessageWindow(args); 
			break; 
		case "JOptionPane_option_window":  				//windowArgList.get(2)
			createOptionWindow(args); 
			break; 
		default: 
			return false; 
		}
			
		return true;
	}
	
	/*
	 * messageWindow argument style: (minimum of first argument required) 
	 * 0: JOptionPane_message_window
	 * 1: text to display
	 * 2: window title
	 * 3: message type
	 */
	private static void createMessageWindow(String[] args)
	{
		if (args.length == 1) 	// by this point length will be at least 1
		{
			JOptionPane.showMessageDialog(null, "default message window message");
		}
		else if (args.length == 2)
		{
			JOptionPane.showMessageDialog(null, args[1]);
		}
		else if (args.length == 3)
		{
			JOptionPane.showMessageDialog(null, args[1], args[2], 0);
		}
		else if (args.length == 4)
		{
			try 
			{
				JOptionPane.showMessageDialog(null, args[1], args[2], Integer.parseInt(args[3]));
			}
			catch (Exception exc)
			{
				JOptionPane.showMessageDialog(null, "Error, argument 4 may have been wrong: " 
						+ args[3] + "\nargument 4 was assumed to be an integer.");
			}
		}
	}
	
	private static void createOptionWindow(String[] args)
	{
		if (args.length == 1) 	// by this point length will be at least 1
		{
			//JOptionPane.showOptionDialog(null, "Program Window", "default option window message");
		}
		else 
		{
			//JOptionPane.showMessageDialog(null, args[1]);
		}
		
	}
}
