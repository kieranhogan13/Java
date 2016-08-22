import java.io.*;

public class Reader {

	// We'll use a BufferedReader to read from a file now, where as previously
	// we used it to read from standard input.
	private BufferedReader fromFile = null;

	public Reader() {
		try {
			// Instantiate the BufferedReader, using the file named 'myfile'.
			fromFile = new BufferedReader(new FileReader("myfile"));

			// Read a single line from the file.
			String lineOfText = fromFile.readLine();

			// Loop through all the lines of text until we reach the end of the file.
			// At the end of the file, readLine() will simply return null.
			for(int i = 1; lineOfText != null; i++) {
				System.out.println("LINE " + i + ": " + lineOfText);
				lineOfText = fromFile.readLine();
			}

		} catch (IOException ioe) {
			System.out.println("Problem with IO: " + ioe.getMessage());
		}
	}

	public static void main(String args[]) {
		new Reader();
	}
}
