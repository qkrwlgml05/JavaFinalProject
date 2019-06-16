package edu.handong.javafinal.readers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Writer {

	public static void writeAFile (ArrayList<String> lines, String targetFileName) {
		PrintWriter pw = null;
		try {
			File fi = new File(targetFileName);
			if (!fi.exists()) {
				fi.getParentFile().mkdirs();
			}
			pw = new PrintWriter(targetFileName);
			for (String line : lines) 
				pw.println(line);
			
			pw.close();
		}catch (IOException e) {
			System.out.println("directory making error");
			System.exit(0);
		}
	}
}
