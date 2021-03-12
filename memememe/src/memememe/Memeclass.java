package memememe;

public class Memeclass {
	public static void main (String args[])
	{
		int thiccInt = 1; 
		int[] thicc = new int[10]; 
		int[][] thiccMatrix = new int[2][2]; 
		
		
		for (int row = 0, ctr = 0; row < thiccMatrix.length; row++) {
			for (int col = 0; col < thiccMatrix[row].length; col++, ctr++) {
				thiccMatrix[row][col] = ctr; 
			}
		}
		
		//output
		for (int row = 0, ctr = 0; row < thiccMatrix.length; row++) {
			System.out.print("[ "); 
			for (int col = 0; col < thiccMatrix[row].length; col++, ctr++) {
				System.out.print(ctr + " "); 
			}
			System.out.print("]"); 
			System.out.println(""); 
		}
	}
}
