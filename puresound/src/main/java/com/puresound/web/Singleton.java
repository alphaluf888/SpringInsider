package com.puresound.web;

public class Singleton {
	private static Singleton instance;
	
	static {
		instance = new Singleton();
	}
	
	private Singleton() {
		System.out.println("Singleton instantiation");
	}
	
	public static Singleton getInstance() {
		return instance;
	}
	
	
}
