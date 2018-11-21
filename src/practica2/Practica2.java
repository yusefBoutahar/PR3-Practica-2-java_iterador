
package practica2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


public class Practica2 {

	/**
	 * Si queremos mostrar los resultados por consola (ademas del fichero), solo
	 * hay q descomentar el array vectorConsola[]. Linea 35
	 * 
	 * Advertir que al usar ese vector para almacenar todas las combinaciones sin repeticion
	 * que la suma de sus elementos sea impar, tiene un LIMITE DE MEMORIA, es decir, si hay demasiadas
	 * combinaciones sobrepasa el limite de memoria y nos sale un;
	 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        at practica2.Practica2.main(Practica2.java:35)
        
	 * @param args
	 * @throws IOException
	 */
    public static void main(String[] args) throws IOException {

        int size = Integer.parseInt(args[0]);
        int[] arrayComb = new int[size];// combinacion
        int[] valores = vector(args[1]);// todos los elementos
        
        Iterator iter = new Iterator(valores.length,size);
        int[] vectorConsola = new int[iter.getNumOfCombinations()*size];// vector que se usara para mostrar por consola las combinaciones
        FileWriter fichero = new FileWriter(args[2]);
        PrintWriter pw = new PrintWriter(fichero);
        int i = 0;
        int suma = 0;
        
        long tiempoInicial =System.nanoTime();// comienza la cuenta
        while(iter.hasMore()){// mientras haya mas combinaciones
        	
        	arrayComb = iter.getNext();//nos devuelve las posiciones de la siguiente combinacion
        	    	
        	for(int j = 0; j < arrayComb.length; j++) {
        		suma += valores[arrayComb[j]];
        	}
        	
        	if(suma %2 != 0) {// si es impar
        		suma = 0;
        		//Guardamos las combinaciones IMPARES en el fichero y en el vectorConsola
        		vectorConsola[i] = valores[arrayComb[0]];
        		i++;
                pw.print(valores[arrayComb[0]]);
                for (int p = 1; p < size; p++) {
                    //Guardamos los valores separados por una ,
                    pw.print(" "+valores[arrayComb[p]]);
                    vectorConsola[i] = valores[arrayComb[p]];
                    i++;
                }
                pw.println("");
        	}
        }
        if(args[3].equals("-t")) {
        	System.out.println("Ha tardado: "+((System.nanoTime()-tiempoInicial)*0.000000001) + " segundos");
        }
        pw.close();

        
        // mostramos todas las combinaciones IMPARES
        i = 0; // podemos sobreescribirla por que el iterador ya termino
        if(args[3].equals("-m")){
            for (int j = 0; j < vectorConsola.length; j++,i++) {
            	if(vectorConsola[j] == 0)break;// cuando terminen se para, y no sigue imprimiendo ceros
            	if( i == arrayComb.length) {
            		System.out.println("\n");
            		i=0;
            	}
                System.out.print(vectorConsola[j] + " ");
            }
        }
  
    }
    
    
    //Metodo que cuenta las lineas del fichero de datos (cuenta los numeros q hay en el fichero)
    private static int numeroLineas(FileReader f) throws IOException {
        int result = 0;
        try (BufferedReader b = new BufferedReader(f)) {
            while ((b.readLine()) != null) {
                result++;
            }
        }
        return result;
    }
    
    //Metodo que devuelve un vector con los datos ordenados
    private static int[] vector(String direccionArchivo) throws FileNotFoundException, IOException {
        String cadena;
        int i = 0;
        int[] numeros;
        FileReader f1 = new FileReader(direccionArchivo);
        FileReader f = new FileReader(direccionArchivo);
        try (BufferedReader b = new BufferedReader(f)) {
            numeros = new int[numeroLineas(f1)];
            while ((cadena = b.readLine()) != null) {
                numeros[i] = Integer.parseInt(cadena);
                i++;
            }
            Arrays.sort(numeros);
        }
        return numeros;
    }
    
}
