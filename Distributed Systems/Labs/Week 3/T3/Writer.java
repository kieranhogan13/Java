import java.io.*;

public class Writer {

	// We will use a PrintWriter to write character strings to a file.
	private PrintWriter outToFile = null;
	// We will read from standard input as usual.
	private BufferedReader userInput = null;

	// Constructor
	public Writer() {
		try {
			// Instantiate the PrintWriter, passing in the filename. The second argument
			// to the FileWriter constructor expresses our wish to append new data to
			// the file rather than overwriting the contents of the file.
			outToFile = new PrintWriter(new FileWriter("myfile", true));
			userInput = new BufferedReader(new InputStreamReader(System.in));

			// Prompt the user to enter text.
			System.out.print("Enter a line of text :/> ");

			// Read the text...
			String lineOfText = userInput.readLine();

			// ...and write it to the file.
			outToFile.println(lineOfText);

			// If you don't close the output stream, you should at least call the 'flush()'
			// method to ensure that all data in the buffer is written to the destination
			// before the process exits.
			outToFile.close();

		} catch (IOException ioe) {
			System.out.println("Problem with IO: " + ioe.getMessage());
		}
	}

	public static void main(String args[]) {
		// Instantiate the main class as ususal.
		new Writer();
	}
}
