package edu.handong.javafinal.readers;

public class CustomizedException extends Exception {
	public CustomizedException () {
		super("There is CLI error.\n");
	}
	
	public CustomizedException (String message) {
		super(message);
	}
}
