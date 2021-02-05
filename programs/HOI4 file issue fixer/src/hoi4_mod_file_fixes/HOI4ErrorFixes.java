
package hoi4_mod_file_fixes;

import java.io.File;
import java.io.IOException;

public class HOI4ErrorFixes {
	
	public static void main (String args[]) throws IOException {
		String hoi4_dir_name = "C:\\\\Users\\\\Skorpion\\\\Documents\\\\Paradox Interactive\\\\Hearts of Iron IV\\\\mod\\\\nadivided-dev"; 
		File hoi4_dir = new File(hoi4_dir_name);
		File states_dir = new File(hoi4_dir_name + "\\\\history\\\\states"); 
		File strat_region_dir = new File(hoi4_dir_name + "\\\\map\\\\strategicregions"); 
		
		//test
		for( File f : hoi4_dir.listFiles()){
			System.out.println( f.getName() );
		}
		
		// fix state stuff
		FixDupProvinces.RemoveDuplicates(states_dir);
		System.out.println("\n" + "states done." + "\n"); 
		
		// fix strat region stuff
		FixDupProvinces.RemoveDuplicates(strat_region_dir);
		System.out.println("\n" + "strat regions done." + "\n"); 
	}
}
