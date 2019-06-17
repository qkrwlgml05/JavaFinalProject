package edu.handong.javafinal.readers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import edu.handong.javafinal.customized.CustomizedGenerics;

public class Writer {

	public static void writeAFile (CustomizedGenerics<String> lines, String targetFileName) {
		PrintWriter pw = null;
		try {
			File fi = new File(targetFileName);
			if (!fi.exists()) {
				fi.getParentFile().mkdirs();
			}
			pw = new PrintWriter(targetFileName);
			for (int i = 0; i < lines.size(); i++) 
				pw.println(lines.get(i));
			
			pw.close();
		}catch (IOException e) {
			System.out.println("directory making error");
			System.exit(0);
		}
	}
}
