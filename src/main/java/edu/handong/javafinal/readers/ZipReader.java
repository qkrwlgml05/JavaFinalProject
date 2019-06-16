package edu.handong.javafinal.readers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

public class ZipReader extends Thread {

	public static ArrayList<String> readFirstFileInZip (String path, String studentId, boolean removeHeader) {
		ArrayList<String> read = new ArrayList<String>();
		ZipFile zipFile;
		try {
				zipFile = new ZipFile(path);
				Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
				int count = 0;
	
			    while(entries.hasMoreElements()){
			    	ZipArchiveEntry entry = entries.nextElement();
			        InputStream stream = zipFile.getInputStream(entry);
	
			        Reader myReader = new Reader();
			        
			        System.out.println(entry.getName());
			        for(String value : myReader.getData(stream, removeHeader)) {
			        	value = "\""+ studentId + "\"," + value;
			        	read.add(value);
			        	
			        }
		        	break;
			    }
				zipFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return read;
	}
	
	public static ArrayList<String> readSecondFileInZip (String path, String studentId, boolean removeHeader) {
		ArrayList<String> read2 = new ArrayList<String>();
		ZipFile zipFile;
		try {
				zipFile = new ZipFile(path);
				Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
		        int count = 0;
		        
			    while(entries.hasMoreElements()){
			    	if (count == 1) {
				    	ZipArchiveEntry entry = entries.nextElement();
				        InputStream stream = zipFile.getInputStream(entry);
		
				        Reader myReader = new Reader();
				        System.out.println(entry.getName());
				        for(String value : myReader.getData(stream, removeHeader)) {
				        	value = "\""+ studentId + "\"," + value;
				        	read2.add(value);
				        	
				        }
			    	}
		        	count++;
			    }
				zipFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return read2;
	}
}