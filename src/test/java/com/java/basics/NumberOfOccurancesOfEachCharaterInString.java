package com.java.basics;

import java.util.HashMap;
import java.util.Map;

public class NumberOfOccurancesOfEachCharaterInString {

	/*{ =3, a=4, c=1, e=1, g=1, h=1, i=1, J=1, L=1, m=1, n=3, o=1, r=2, 2=1, v=1, 6=1}
	*/
	static {
		System.out.println("i am inside static block");
	}
	public static void main(String[] args) {
		String str = "LearningJava on march 26";
		char[] chArr = str.toCharArray();
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		// checking for each character inside char array
		for (char ch : chArr) {
			if (map.containsKey(ch)) {
				map.put(ch, map.get(ch) + 1);
			} else {
				map.put(ch, 1);
			}

		}
		
		 // Iterating over hashmap
		  for (Map.Entry<Character, Integer> entry : map.entrySet()) {
		   Character key = entry.getKey();
		   Integer value = entry.getValue();
		  // System.out.println("----------------------");
		   System.out.println(key + " " + value);
		  // System.out.println("----------------------");
		  }
		System.out.println(map);
	}

}
