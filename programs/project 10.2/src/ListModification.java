package project_10_2;

public class ListModification 
{
	
	/******************************************************************************************************/
	/*											 METHOD SECTION 										  */
	/******************************************************************************************************/
	
	public static double[] shortenArray(double[] lst, int lngth) 
	/******************************************************************************************************/
	/* PRECONDITION:  ARRAY NEEDS TO BE SHORTENED TO A NEW SPECIFIED LENGTH 							  */					  
	/* POSTCONDITION: BEGINNING OF THE ARRAY IS COPIED INTO A NEW (SHORTER) ARRAY, 						  */
	/* 				  AND THEN SET TO THE NEW ARRAY 													  */
	/******************************************************************************************************/
	{
		double[] temp = new double[lngth]; 
		for (int i = 0; i < lngth; i++) 
		{
			temp[i] = lst[i]; 
		}
		return temp; 
	}
	
	public static void lengthenArray(double[] lst, int lngth) 
	/******************************************************************************************************/
	/* PRECONDITION:  ARRAY NEEDS TO BE LENGTHENED TO A NEW SPECIFIED LENGTH 							  */					  
	/* POSTCONDITION: ARRAY IS COPIED INTO A NEW (LONGER) ARRAY, AND THEN SET TO THE NEW ARRAY 			  */
	/******************************************************************************************************/
	{
		double[] temp = new double[lngth]; 
		for (int i = 0; i < lst.length; i++) 
		{
			temp[i] = lst[i]; 
		}
		lst = temp;
	}
	
}
