package color_generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefinitionColorsReader {

	
	static List<String[]> readCSV() throws IOException {
		List<String[]> csv_data =
				Files.lines(Paths.get("C:\\Users\\Skorpion\\Documents\\Paradox Interactive\\Hearts of Iron IV\\mod\\ROTE-Modern-Day\\map\\definition.csv"))
					.map(line -> line.split(","))
					.collect(Collectors.toList());
		
		return csv_data; 
	} 
}
