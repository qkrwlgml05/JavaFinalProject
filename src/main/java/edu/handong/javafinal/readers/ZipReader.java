package edu.handong.javafinal.readers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

public class ZipReader extends Thread {

	public static ArrayList<String> readFirstFileInZip (String path, boolean removeHeader) {
		ArrayList<String> read = new ArrayList<String>();
		ZipFile zipFile;
		try {
			read.add("학생번호,제목,요약문 (300자 내외),핵심어 (keyword,쉽표로 구분),조회날짜,실제자료조회,출처 (웹자료링크),원출처 (기관명 등),제작자 (Copyright 소유처)");
			for (int i = 1; i <= 5; i++) {
				String zipPath = path + "/000" + i + ".zip";
				System.out.println(zipPath);
				zipFile = new ZipFile(zipPath);
				Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
				int count = 0;
	
			    while(entries.hasMoreElements()){
			    	ZipArchiveEntry entry = entries.nextElement();
			    	System.out.println(entry.getSize());
			        InputStream stream = zipFile.getInputStream(entry);
	
			        Reader myReader = new Reader();
			        
			        for(String value : myReader.getData(stream, removeHeader)) {
			        	value = "000" + i + "," + value;
			        	read.add(value);
			        	
			        	System.out.println(value);
			        }
		        	break;
			    }
				zipFile.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return read;
	}
	
	public static ArrayList<String> readSecondFileInZip (String path, boolean removeHeader) {
		ArrayList<String> read = new ArrayList<String>();
		ZipFile zipFile;
		try {
			read.add("학생번호,제목(반드시 요약문 양식에 입력한 제목과 같아야 함.),표/그림 일련번호,자료유형(표,그림,…),자료에 나온 표나 그림 설명(캡션)	자료가 나온 쪽번호");
			for (int i = 1; i <= 5; i++) {
				String zipPath = path + "/000" + i + ".zip";
				zipFile = new ZipFile(zipPath);
				Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
		        int count = 0;
		        
			    while(entries.hasMoreElements()){
			    	if (count == 1) {
				    	ZipArchiveEntry entry = entries.nextElement();
				    	System.out.println(entry.getSize());
				        InputStream stream = zipFile.getInputStream(entry);
		
				        Reader myReader = new Reader();
				        
				        for(String value : myReader.getData(stream, removeHeader)) {
				        	value = "000" + i + "," + value;
				        	read.add(value);
				        	
				        	System.out.println(value);
				        }
			    	}
		        	count++;
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