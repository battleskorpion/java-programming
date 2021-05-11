package trayprogclient;

import java.io.File;
import java.io.FileWriter;
 
public class CDUtils {
 
	private CDUtils() {  }
 
	public static void open(String drive) {
 
		try {
			//File file = File.createTempFile("realhowto",".vbs");
			//File file = new File("C:\\BC5\\Projects\\KATUSHKA\\ctutils.vbs"); 
			
			// use 
			File file = new File("C:\\BC5\\Projects\\cdutil00.exe"); 
			//File file = new File("G:\\KATUSHKA2.0\\cdutil00.exe"); 
			
			//file.deleteOnExit();
			//FileWriter fw = new java.io.FileWriter(file);
			//String vbs = "Set wmp = CreateObject(\"WMPlayer.OCX\") \n"
			//		+ "Set cd = wmp.cdromCollection.getByDriveSpecifier(\""
			//		+ drive + "\") \n"
			//		+ "cd.Eject";
	        //fw.write(vbs);
	        //fw.close();
	        //Runtime.getRuntime().exec("wscript " + file.getPath()).waitFor();
			
			Runtime.getRuntime().exec(file.getPath()).waitFor();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
 
	public static void close(String drive) {
 
		try {
			File file = File.createTempFile("realhowto",".vbs");
			file.deleteOnExit();
			FileWriter fw = new FileWriter(file);
			// to close a CD, we need eject two times!
			String vbs = "Set wmp = CreateObject(\"WMPlayer.OCX\") \n"
					+ "Set cd = wmp.cdromCollection.getByDriveSpecifier(\""
					+ drive + "\") \n"
					+ "cd.Eject \n "
					+ "cd.Eject ";
			fw.write(vbs);
		    fw.close();
		    Runtime.getRuntime().exec("wscript " + file.getPath()).waitFor();
		 
		}
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	}
 
	public static void main(String[] args) {
 
		javax.swing.JOptionPane.showConfirmDialog((java.awt.Component)
				null, "Press OK to open CD", "CDUtils",
				javax.swing.JOptionPane.DEFAULT_OPTION);
		CDUtils.open("G:\\");
		//javax.swing.JOptionPane.showConfirmDialog((java.awt.Component)
		//		null, "Press OK to close CD", "CDUtils",
		//		javax.swing.JOptionPane.DEFAULT_OPTION);
		//CDUtils.close("G:\\");
		javax.swing.JOptionPane.showMessageDialog((java.awt.Component)
				null, "ok");
 
  }
}
