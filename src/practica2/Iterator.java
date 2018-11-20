package practica2;

import java.math.BigInteger;

public class Iterator {

	private int[] a;
	private int n;
	private int r;
	private BigInteger numLeft;
	private static BigInteger total;
	
	/**
	 * CONTRUCTOR
	 * 
	 * @param n, numero de elementos totales
	 * @param r, numero de la combinacion
	 */
	public Iterator (int n, int r) {
		 if (r > n) {
		   throw new IllegalArgumentException ();
		 }
		 if (n < 1) {
		   throw new IllegalArgumentException ();
		 }
		 this.n = n;
		 this.r = r;
		 a = new int[r];
		 
		 // aqui se siguen las formulas de la combinatoria
		 BigInteger nFact = getFactorial (n);
		 BigInteger rFact = getFactorial (r);
		 BigInteger nminusrFact = getFactorial (n - r);
		 total = nFact.divide (rFact.multiply (nminusrFact));
		 reset ();
	}
	
	/**
	 * 
	 * @return Devuelve el numero maximo de combinaciones posibles
	 */
	public static int getNumOfCombinations() {
		
		return Integer.parseInt(total.toString());
		
	}

	/**
	 * Reset es llamado unicamente por el constructor, y cumple la funcion
	 * de inicializar el contador "numLeft" al mayor numero de combinaciones
	 * posibles
	 */
	public void reset () {
		for (int i = 0; i < a.length; i++) {
			a[i] = i;
		 }
		 numLeft = new BigInteger (total.toString ());
	}
	

	/**
	 * Recordamos que el x.compareto(val) devuelve;
	 * -1, 0 o 1, ya que x BigInteger es 
	 * numéricamente MENOR que, IGUAL o MAYOR que val.
	 * 
	 * @return true si todavia hay mas Combinaciones por hacer
	 */
	public boolean hasMore () {
	 return numLeft.compareTo (BigInteger.ZERO) == 1;
	}
	

	/**
	 * Metodo getFactorial()
	 * 
	 * @param n, valor a calcular su factorial
	 * @return factorial del valor pasado por parametro
	 */
	private static BigInteger getFactorial (int n) {
	 BigInteger fact = BigInteger.ONE;
	 for (int i = n; i > 1; i--) {
	   fact = fact.multiply (new BigInteger (Integer.toString (i)));
	 }
	 return fact;
	}
	

	/**
	 * Metodo getNext() que devuelve un array con los indices
	 * de los numeros de la combinacion
	 * 
	 * En cada combinacion se resta el contador de Combinaciones
	 * @return array de indices
	 */
	public int[] getNext () {
	
	 if (numLeft.equals (total)) {// para 1º caso 
	   numLeft = numLeft.subtract (BigInteger.ONE);// resta 1 al numLeft
	   return a;
	 }
	
	 int i = r - 1;
	 while (a[i] == n - r + i) {
	   i--;
	 }
	 a[i] = a[i] + 1;
	 for (int j = i + 1; j < r; j++) {
	   a[j] = a[i] + j - i;
	 }
	
	 numLeft = numLeft.subtract (BigInteger.ONE);
	 return a.clone();
	
	}
}
