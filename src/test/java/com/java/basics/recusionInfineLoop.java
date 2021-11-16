package com.java.basics;

public class recusionInfineLoop {
	static void p() {
		System.out.println("hello");
		p();
	}

	public static void main(String[] args) {
		p();
	}
}