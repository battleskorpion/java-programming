
package hoi4_mod_file_fixes;

import java.awt.*; 
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

public class HOI4ErrorFixes {
	
	public static void main (String args[]) throws IOException {
		String hoi4_dir_name; //"C:\\Users\\daria\\Documents\\Paradox Interactive\\Hearts of Iron IV\\mod\\nadivided-dev"; 
		File hoi4_dir;
		File states_dir;
		File strat_region_dir;
		
		// get directories
		ShowDirectoryDialog selectModDir = new ShowDirectoryDialog("Select directory of the mod:"); 
		selectModDir.run();
		hoi4_dir_name = selectModDir.getDir(); 
		hoi4_dir = new File(hoi4_dir_name);
		states_dir = new File(hoi4_dir_name + "\\\\history\\\\states");
		strat_region_dir = new File(hoi4_dir_name + "\\\\map\\\\strategicregions"); 
		
		//System.out.println("test after dir"); 
		HOI4ModTasksGUI tasksGUI = new HOI4ModTasksGUI(hoi4_dir, states_dir, strat_region_dir); 
		tasksGUI.run(); 
		
	}
}

