import java.io.*;

public class FileTest1
{
	public static void main(String[] args) throws IOException
	{
		PrintWriter output =
		  	new PrintWriter(new File("test1.txt"));
		output.println("Single line of text!");
		output.close();
	}
}
