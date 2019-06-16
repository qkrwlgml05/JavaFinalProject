package edu.handong.javafinal.readers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

public class ZipReader extends Thread {

	public static ArrayList<String> readFileInZip(String path, boolean removeHeader) {
		ArrayList<String> read = new ArrayList<String>();
		ZipFile zipFile;
		try {
			read.add("학생번호,제목,요약문 (300자 내외),핵심어 (keyword,쉽표로 구분),조회날짜,실제자료조회,출처 (웹자료링크),원출처 (기관명 등),제작자 (Copyright 소유처)");
			for (int i = 1; i <= 5; i++) {
				String zipPath = path + "/000" + i + ".zip";
				zipFile = new ZipFile(zipPath);
				Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
	
			    while(entries.hasMoreElements()){
			    	ZipArchiveEntry entry = entries.nextElement();
			        InputStream stream = zipFile.getInputStream(entry);
	
			        Reader myReader = new Reader();
			        
			        for(String value : myReader.getData(stream, removeHeader)) {
			        	value = "000" + i + "," + value;
			        	read.add(value);
			        }
			    }
				zipFile.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return read;
	}
}