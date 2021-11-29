package com.java.basics;

public class recursionFactorial {
	 int factorial(int n) {
		if (n == 1)
			return 1;
		else {
			return (n * factorial(n - 1));

		}
	}

	public static void main(String[] args) {
		recursionFactorial obj = new recursionFactorial();
		obj.factorial(5);
		System.out.println("Factorial of 5 is: " + obj.factorial(5));
		//System.out.println("Factorial of 5 is: " + factorial(5));
	}
}
