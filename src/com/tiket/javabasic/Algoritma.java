package com.tiket.javabasic;

public class Algoritma {

	static String AIUEO = "aiueo";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s1 = "Saya sedang Belajar Bahasa PemOgraman JAVA";
		String s2 = "Developer PT. Global Tiket Network";

		char[] s1Chars = s1.toCharArray();
		char[] s2Chars = s2.toCharArray();

		// upper case and lower case 
		System.out.println("=========================================");
		for (int i = 0; i < s1Chars.length; i++) {
			if (Character.isUpperCase(s1Chars[i])) {
				System.out.print(Character.toLowerCase(s1Chars[i]));
			} else {
				System.out.print(Character.toUpperCase(s1Chars[i]));
			}
		}
		System.out.println("\n=========================================");

		// remve vowel 
		for (char c : s1Chars) {
			if ("AaEeIiOoUu".indexOf(c) == -1) {
				System.out.print(c);
			}
		}
		System.out.println("\n=========================================");

		// calculate and remove vowel
		for (int i = 0; i < s2Chars.length; i++) {
			int index = -1;
			if ("aeiou".contains(String.valueOf(s2Chars[i]))) {
				index = i;
			}
			for (int j = 0; j < s2Chars.length; j++) {
				if (index > -1) {
					if (s2Chars[index] == s2Chars[j]) {
						
						shifting(s2Chars, i, j);

					}
				}
			}
		}
		
		s2 = String.copyValueOf(s2Chars);
		
		char[] aiueoArr = AIUEO.toCharArray();
		for (char c: aiueoArr) { 
			s2 = convertVowel(s2, String.valueOf(c));	
		}
		
		System.out.println(s2);

		System.out.println("=========================================");

	}

	private static String convertVowel(String str, String aiueo) { 
		if (str.contains(aiueo)) {
			int length = str.lastIndexOf(aiueo) - str.indexOf(aiueo);
			if (length > 1) { 
				str = str.replace(
							str.substring(str.indexOf(aiueo), str.lastIndexOf(aiueo)+1), 
							aiueo+(str.lastIndexOf(aiueo) - str.indexOf(aiueo) + 1)
						);	
			}
		}
		return str;
	}
	
	private static void shifting(char[] s2Chars, int i, int j) {

		char temp = s2Chars[j];
		for (int x = j; x > i; x--) {
			s2Chars[x] = s2Chars[x - 1];
		}
		s2Chars[i] = temp;
		
	}

}
