package hoi4_mod_file_fixes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FixFocus {
	public static boolean addFocusLoc(File hoi4_dir, File focus_file, File loc_file) throws IOException {
		// some vars
		ArrayList<String> focus_names = new ArrayList<String>(); 
		ArrayList<String> focuses_localized = new ArrayList<String>(); 
		//ArrayList<String> focuses_nonlocalized = new ArrayList<String>(); 
		ArrayList<String> country_tags = new ArrayList<String>(); 
		System.out.println(hoi4_dir);
		File country_tags_file = new File(hoi4_dir + "\\common\\country_tags\\00_countries.txt"); 
		String loc_key = ":0"; 
		
		// more vars
		Scanner focusReader = new Scanner(focus_file); 
		Scanner locReader = new Scanner(loc_file); 
		Scanner countryTagsReader = new Scanner(country_tags_file); 
		
		// make a list of country tags
		while(countryTagsReader.hasNextLine()) {
			String data = countryTagsReader.nextLine().replaceAll("\\s", ""); 
			if (usefulData(data)) {
				// takes the defined tag at the beginning of the line
				country_tags.add(data.substring(0, data.indexOf('=')).trim());
				//System.out.println(data.substring(0, data.indexOf('='))); 
			}
		}
		countryTagsReader.close(); 
		
		// make a list of all focus names
		boolean findFocusName = false; 
		int focus_name_index;  // index of focus name in string
		while(focusReader.hasNextLine()) {
			String data = focusReader.nextLine().replaceAll("\\s", ""); 
			if (usefulData(data)) {
				if (findFocusName == false) {
					//System.out.println(data.trim()); 
					if ((data.trim().length() >= 6) && (data.trim().substring(0, 6).equals("focus="))) {
						findFocusName = true; 
					}
				} 
				else {
					if (data.trim().substring(0, 3).equals(("id="))) {
						focus_name_index = data.indexOf("id=") + 3; 
						//nl_index = data.indexOf("\n"); 
						focus_names.add(data.substring(focus_name_index, data.length()).trim()); 
						//test
						//System.out.println(data.substring(focus_name_index, data.length())); 
						findFocusName = false; 
					}
				}
			}
		}
		focusReader.close(); 
		
		// make a list of every localized focus
		boolean found_lang = false; 
		while(found_lang == false) {
			if (locReader.hasNextLine()) {
				String data = locReader.nextLine().replaceAll("\\s", "");
				if (usefulData(data)) {
					if (data.trim().contains("l_")) {
						found_lang = true; 
					}
				}
			}
			else {
				break; 
			}
		}
		while (locReader.hasNextLine()) {
			String data = locReader.nextLine().replaceAll("\\s", ""); 
			if (usefulData(data)) {
				if (data.contains(":")) {
					//test
					if (focus_names.contains(data.substring(0, data.indexOf(":")))) {
						focuses_localized.add(data.substring(0, data.indexOf(":")).trim()); 
					}
				}
			}
		}
		locReader.close(); 
		
		// do stuff with nonlocalized focuses
		// some vars
		FileWriter locWriter = new FileWriter(loc_file, true);		// true = append
		BufferedWriter locBWriter = new BufferedWriter(locWriter); 
		PrintWriter locPWriter = new PrintWriter(locBWriter); 		// for println syntax
		String focus_loc; 
		//ArrayList<String> focus_loc_array; 
		
		for (String focus : focus_names) {
			// if focus not in localized focuses
			if (!focuses_localized.contains(focus)) { 
				// write to loc file 
				// separate words in focus name
				int i = 0;	//counter
				if (country_tags.contains(focus.substring(0, 3))) {
					i+=3; 
				}
				// make a list of each word in focus_loc
				//focus_loc_array = new ArrayList<String>(Arrays.asList(focus_loc.split(" ")));
				
				focus_loc = focus + loc_key + " ";
				focus_loc += "\""; 
				focus_loc += focus.substring(i, focus.length()).replaceAll("_+", " "); // regex
				focus_loc += "\""; 
				locPWriter.println(focus_loc); 
				System.out.println("added focus to loc, focus " + focus_loc); 
			}
		}
		locPWriter.close(); 
		
		return true; 
	}
	
	// useful lines function
	private static boolean usefulData(String data) {
		if (!data.isEmpty()) {
			if (data.trim().charAt(0) == '#') {
				return false; 
			}
			else {
				return true; 
			}
		}
		else {
			return false; 
		}
	}
}
