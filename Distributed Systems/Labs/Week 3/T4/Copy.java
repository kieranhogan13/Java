import java.io.*;

public class Copy
{
	public static void main(String[] arg) throws IOException
	{
		//First check that 2 file names have been supplied...
		if (arg.length < 2)
		{
			System.out.println(
					"You must supply TWO file names.");
			System.out.println("Syntax:");
			System.out.println(
					"    java Copy <source> <destination>");
			return;
		}

		BufferedReader source = 
					new BufferedReader(new FileReader(arg[0]));
		PrintWriter destination = 
					new PrintWriter(new FileWriter(arg[1]));

		String input = source.readLine();
		while (input!=null) 
		{
			destination.println(input);
			input = source.readLine();
		}

		source.close();
		destination.close();
	}
}
