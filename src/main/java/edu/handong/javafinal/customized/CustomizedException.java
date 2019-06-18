package edu.handong.javafinal.customized;
import edu.handong.javafinal.readers.Writer;

public class CustomizedException extends Exception {
	private static CustomizedGenerics<String> zipFile = new CustomizedGenerics<String>();
	
	public CustomizedException () {
		super("There is CLI error.\n");
	}
	
	public CustomizedException (String path) {
		if (path != null)
			zipFile.add(path);
	}
	
	public void add(String path) {
		boolean check = true;
		for (int i = 0; i < zipFile.size(); i++) {
			if(zipFile.get(i).equals(path)) {
				check = false;
				break;
			}
		}
		
		if (check && path != null)
			zipFile.add(path);
	}
	
	public void write(String output) {
		Writer.writeAFile(zipFile, output);
	}
}
