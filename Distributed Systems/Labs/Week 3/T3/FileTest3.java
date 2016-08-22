import java.io.*;
import java.util.*;

public class FileTest3
{
	public static void main(String[] args) throws IOException
	{
		int mark, total=0, count=0;
		Scanner input = new Scanner(new File("marks.txt"));

		while (input.hasNext())
		{
			mark = input.nextInt();
			total += mark;
			count++;
		}
		input.close();
		System.out.println("Mean = " + (float)total/count);
	}
}
