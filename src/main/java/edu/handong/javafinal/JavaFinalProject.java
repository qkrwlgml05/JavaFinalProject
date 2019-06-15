package edu.handong.javafinal;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import edu.handong.javafinal.readers.CustomizedException;
import edu.handong.javafinal.readers.Reader;

public class JavaFinalProject extends Thread {
	private String input;
	private String output;
	private boolean help;
	
	public void run (String[] args) {
		Options options = createOptions();
		
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}
			
			try {
				// when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.
				if(args.length<2)
					throw new CustomizedException();
			} catch (CustomizedException e) {
				System.out.println(e.getMessage());
				System.exit(0);
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
		// automatically generate the help statement
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
