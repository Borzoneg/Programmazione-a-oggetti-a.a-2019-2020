package it.polito.po2020.esempi.base;

import javax.swing.JOptionPane;

public class EsempiString {

	public static void main(String[] args) {
		
		char[] s1;  // non viene intrepretato come stringa
		
		String s2 = "Hello";
		// EQUIVALE A
		char[] __s2 = {'H','e','l','l','o'};
		String _s2 = new String(__s2);

		String s3 = s2 + " world!";
		
		s2 += " world!";
		
		int i=42;
		
		s2 = s2 + i;
		// EQUIVALE A
		s2 = s2 + Integer.toString(i);
		// EQUIVALE A
		s2 += i;
		
		String input = JOptionPane.showInputDialog("Inserisci il tuo nome");
		
		System.out.println(input);
	}

}
