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
	
	public static void main(String[] args) {
		JavaFinalProject my = new JavaFinalProject();
		Thread t = new Thread();
		System.out.println(args[2]);
		
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
		t.start();
	}
	
	@Override
	public void run () {
		System.out.println("2");
		System.out.println("5");
			
		result1 = ZipReader.readFirstFileInZip(input, true);
		result2 = ZipReader.readSecondFileInZip(input, true);
		Writer.writeAFile(result1, output+"/results1.csv");
		Writer.writeAFile(result2, output+"/results2.csv");
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
