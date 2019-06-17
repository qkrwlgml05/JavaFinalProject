package edu.handong.javafinal.customized;

public class CustomizedException extends Exception {
	static CustomizedGenerics<String> zipFile = new CustomizedGenerics<String>();
	
	public CustomizedException () {
		super("There is CLI error.\n");
	}
	
	public CustomizedException (String path) {
		zipFile.add(path);
	}
}
