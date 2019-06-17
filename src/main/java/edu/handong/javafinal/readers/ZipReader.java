package edu.handong.javafinal.readers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

import edu.handong.javafinal.customized.CustomizedException;
import edu.handong.javafinal.customized.CustomizedGenerics;

public class ZipReader extends Thread {
	public static CustomizedException ex = new CustomizedException(null);
	
	public static void ZipUntie (String path) {

		File zipFilePath = new File(path.split(".zip")[0]);
		ZipFile zipFile;
		if(!zipFilePath.exists())
			zipFilePath.mkdirs();

		try {
			zipFile = new ZipFile(path);
			Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
			byte[] bytes = new byte[4000];
			
		    while(entries.hasMoreElements()) {
		    	ZipArchiveEntry entry = entries.nextElement();
		        String name  = entry.getName();
		       
		        InputStream in = zipFile.getInputStream(entry);
		        FileOutputStream fi = new FileOutputStream(new File(zipFilePath, name));
		        
		        while(true) {
		        	int len = in.read(bytes);
		            if(len <= 0)
		                break;
		            fi.write(bytes, 0, len);
		        }
		        fi.close();
		    }
		    zipFile.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}

	}

	public static CustomizedGenerics<String> readFirstFileInZip (String path, String studentId, boolean removeHeader) throws CustomizedException{
		CustomizedGenerics<String> read = new CustomizedGenerics<String>();
		ZipFile zipFile;
		try {
				zipFile = new ZipFile(path);
				Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
				int count = 0;
	
			    while(entries.hasMoreElements()){
			    	System.out.println(count);
			    	ZipArchiveEntry entry = entries.nextElement();
			        InputStream stream = zipFile.getInputStream(entry);
	
			        Reader myReader = new Reader();
			        
			        CustomizedGenerics<String> reader = myReader.getData(stream, removeHeader, 1);
			        if (reader == null) throw ex;
			        for(int i = 0; i < reader.size(); i++) {
			        	String value = "\""+ studentId + "\"," + reader.get(i);
			        	read.add(value);
			        	
			        	System.out.println(value);
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
	
	public static CustomizedGenerics<String> readSecondFileInZip (String path, String studentId, boolean removeHeader) throws CustomizedException{
		CustomizedGenerics<String> read2 = new CustomizedGenerics<String>();
		ZipFile zipFile;
		try {
				zipFile = new ZipFile(path);
				Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
		        int count = 0;
		        
			    while(entries.hasMoreElements()){
			    	ZipArchiveEntry entry = entries.nextElement();
			        InputStream stream = zipFile.getInputStream(entry);
			    	if (count++ == 1) {
			    		System.out.println(count);
		
				        Reader myReader = new Reader();
				        System.out.println(entry.getName());

				        CustomizedGenerics<String> reader = myReader.getData(stream, removeHeader, 2);
				        if (reader == null) throw ex;
				        for(int i = 0; i < reader.size(); i++) {
				        	String value = "\""+ studentId + "\"," + reader.get(i);
				        	read2.add(value);
				        }
				        
				        break;
			    	}
			    }
				zipFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return read2;
	}
}