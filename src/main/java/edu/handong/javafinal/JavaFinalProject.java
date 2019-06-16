package edu.handong.javafinal;

import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import edu.handong.javafinal.readers.CustomizedException;
import edu.handong.javafinal.readers.ZipReader;
import edu.handong.javafinal.readers.Writer;

public class JavaFinalProject implements Runnable{
	private String input;
	private String output;
	private boolean help;
	
	ArrayList<String> result1 = new ArrayList<String>();
	ArrayList<String> result2 = new ArrayList<String>();
	
	private String path;
	private String studentId;
	
	private int check = 0;
	
	public static void main(String[] args) {
		JavaFinalProject my = new JavaFinalProject();
		
		Options options = my.createOptions();
		
		if(my.parseOptions(options, args)){
			if (my.help){
				my.printHelp(options);
				return;
			}
			
			try {
				if(args.length<2)
					throw new CustomizedException();
			} catch (CustomizedException e) {
				System.out.println(e.getMessage());
				System.exit(0);
			}
		}
		Thread[] th1 = new Thread[5];
		Thread[] th2 = new Thread[5];
		my.result1.add("\"학생번호\",\"제목\",\"요약문 (300자 내외)\",\"핵심어 (keyword,쉽표로 구분)\",\"조회날짜\",\"실제자료조회 출처 (웹자료링크)\",\"원출처 (기관명 등)\",\"제작자 (Copyright 소유처)\"");
		my.result2.add("\"학생번호\",\"제목(반드시 요약문 양식에 입력한 제목과 같아야 함.)\",\"표/그림 일련번호\",\"자료유형(표,그림,…)\",\"자료에 나온 표나 그림 설명(캡션)\",\"자료가 나온 쪽번호\"");
				
		for (int i = 1; i <= 5; i++) {
			my.path = my.input + "/000" + i + ".zip";
			my.studentId = "000" + i;
			th1[i-1] = new Thread(my.studentId);
			th2[i-1] = new Thread(my.studentId);
			try {
				my.check = 1;
				th1[i-1].start();
				th1[i-1].join();
				//my.run();
				
				my.check = 2;
				th2[i-1].start();
				th2[i-1].join();
				my.run();
				System.out.println(my.path);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		Writer.writeAFile(my.result1, my.output+"/results1.csv");
		Writer.writeAFile(my.result2, my.output+"/results2.csv");
	}
	
	@Override
	public void run () {
		if (check == 1) {
			for (String line : ZipReader.readFirstFileInZip(path, studentId, true)) {
				result1.add(line);
			}
		} else if (check == 2) {
		
			for (String line : ZipReader.readSecondFileInZip(path, studentId, true)) {
				result2.add(line);
				System.out.println(line);
			}
		}
	}

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {
			CommandLine cmd = parser.parse(options, args);

			input = cmd.getOptionValue("i");
			output = cmd.getOptionValue("o");
			help = cmd.hasOption("h");
		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	private void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		String header = "Java FInal Project";
		String footer = ""; //"\nPlease report issues at https://github.com/lifove/CLIExample/issues";
		formatter.printHelp("JavaFinalProject", header, options, footer, true);
	}

	private Options createOptions() {
		Options options = new Options();
		
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg()
				.argName("Input path")
				.required()
				.build());
		
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output file path")
				.hasArg()
				.argName("Output path")
				.required()
				.build());
		
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Show a Help page")
				.argName("Help")
				.build());
		
		return options;
	}

}
