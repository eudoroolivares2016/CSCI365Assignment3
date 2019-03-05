import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class UltimateAdHocScanner {

	public static void main(String args[]) {
		if (args.length != 1) {
			System.out.println(
					"Check the number of arguments this program requies 1 arguemnt: the name of the file you want to run the ad hoc in");
			System.exit(1); // exit the program if the user is specifiying the wrong number of arguments
		}
		String argumentFileName = args[0];
		try {

			File testFile = new File(argumentFileName);
			Scanner scan = new Scanner(testFile);
			String word;
			String entireFile = "";

			while (scan.hasNext()) {
				word = scan.next();
				if (word.contains("#")) {
					scan.nextLine(); // skip the line if there is a comment ignorning everything else on that line
				} else {

					entireFile = entireFile + word + " ";
				}

				
				
				Pattern idPattern = Pattern.compile("([a-zA-Z])([a-zA-Z0-9])*");
				Pattern numberPattern = Pattern.compile("(\\d)+[.]?(\\d)*");
				Pattern addopPattern = Pattern.compile("([+]|[-])");
				Pattern multopPattern = Pattern.compile("[*]|[%]|[/]([/])?");

				Pattern relPattern = Pattern.compile("[<](=)?|[>](=)?|[!](=)?|[=]{2}");
				Pattern rparen = Pattern.compile("[(]");
				Pattern lparen = Pattern.compile("[)]");

				Matcher idMatch = idPattern.matcher(entireFile);
				Matcher numberMatch = numberPattern.matcher(entireFile);
				Matcher addopMatch = addopPattern.matcher(entireFile);
				Matcher multopMatch = multopPattern.matcher(entireFile);
				Matcher relopMatch = relPattern.matcher(entireFile);

				// System.out.println(idMatch.find())

				if (idMatch.find()) {

					System.out.println("<id>, " + idMatch.group()); // this is the token it matched
					entireFile = entireFile.substring(idMatch.end(), entireFile.length());
					entireFile = entireFile.trim();
					// System.out.println(entireFile);

					// findMatch(entireFile);
					// System.exit(1);

				} 

				if (numberMatch.find()) {
					System.out.println("<number>, " + numberMatch.group()); // this is the token it matched
					// System.out.println(numberMatch.end());
					// System.out.println("Length of file after we have removed the first id" +
					// entireFile.length());
					entireFile = entireFile.substring(numberMatch.end(), entireFile.length());
					entireFile = entireFile.trim();

				}

				if (addopMatch.find()) {
					System.out.println("<add_op>, " + addopMatch.group()); // this is the token it matched
					// System.out.println(addopMatch.end());
					entireFile = entireFile.substring(addopMatch.end(), entireFile.length()); // entireFile =
					entireFile.trim();

				}
				
				

				/*
				 * if (multopMatch.find()) { System.out.println("<mult_op>, " +
				 * multopMatch.group()); // this is the token it matched //
				 * System.out.println(numberMatch.end()); entireFile =
				 * entireFile.substring(multopMatch.end(), entireFile.length()); // entireFile =
				 * entireFile.trim();
				 * 
				 * }
				 * 
				 * if (relopMatch.find()) { System.out.println("<rel_op> , >, " +
				 * relopMatch.group()); // this is the token it matched //
				 * System.out.println(numberMatch.end()); entireFile =
				 * entireFile.substring(relopMatch.end(), entireFile.length()); entireFile =
				 * entireFile.trim();
				 * 
				 * }
				 */

				 System.out.println(entireFile);

			}

			// if (findMatch(word) == false) { // calls findMatch for all the lines in the
			// program
			// System.out.println("<error>, " + word); // if the find match function was
			// unable to find anything
			// that matches that text it would retrun that the boolean // find value was
			// false and we print error
			// }

			scan.close();
			// System.out.println(entireFile);

		} catch (IOException io) {

			System.out.println("The specified file could not be found");
			System.exit(1); // exit if the specified file was not able to be found on the system
		}

	}
}
