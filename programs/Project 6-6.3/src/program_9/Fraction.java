package program_9;

public class Fraction {
	private int numerator; 
	private int denominator; 
	
	public Fraction () {
		this (1, 1); 
	}
	
	public Fraction (int nmtr, int dnmtr) { 
		numerator = nmtr; 
		denominator = dnmtr; 
	}
	
	public Fraction (int dnmtr) {
		this (0, dnmtr); 
	}
	
	public Fraction(Fraction frctn) {
		this(frctn.getNumerator(), frctn.getDenominator()); 
	}
	
	public void setNumerator(int nmtr) {
		numerator = nmtr; 
	}
	
	public void setDenominator(int dnmtr) {
		denominator = dnmtr; 
	}
	
	public void setFraction (int nmtr, int dnmtr) {
		numerator = nmtr; 
		denominator = dnmtr;
	}
	
	public int getNumerator() {
		return numerator; 
	}
	
	public int getDenominator() {
		return denominator; 
	}
	
	public String toString() {
		return numerator + "/" + denominator; 
	}
	
	public Fraction add(Fraction f) { 
		Fraction result = new Fraction(); 
		
		int n1 = numerator; 
		int d1 = denominator; 
		int n2 = f.getNumerator(); 
		int d2 = f.getDenominator(); 
		
		result.setNumerator((n1 * d2) + (n2 * d1));
		result.setDenominator(d1 * d2); 
		return result; 
	}
	
	public Fraction subtract(Fraction f) {
		Fraction result = new Fraction(); 
		
		int n1 = numerator; 
		int d1 = denominator; 
		int n2 = f.getNumerator(); 
		int d2 = f.getDenominator(); 
		
		result.setNumerator((n1 * d2) - (n2 * d1));
		result.setDenominator(d1 * d2); 
		return result; 
	} 
	
	public Fraction multiply(Fraction f) {
		Fraction result = new Fraction(); 
		
		int n1 = numerator; 
		int d1 = denominator; 
		int n2 = f.getNumerator(); 
		int d2 = f.getDenominator(); 
		
		result.setFraction(n1 * n2, d1 * d2); 
		return result; 
	}
	
	public Fraction divide(Fraction f) {
		Fraction result = new Fraction(); 
		
		int n1 = numerator; 
		int d1 = denominator; 
		int n2 = f.getNumerator(); 
		int d2 = f.getDenominator(); 
		
		result.setFraction(n1 * d2, d1 * n2);
		return result; 
	}
}
