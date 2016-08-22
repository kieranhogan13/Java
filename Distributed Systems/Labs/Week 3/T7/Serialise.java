import java.io.*;

public class Serialise
{
	public static void main(String[] args)
		throws IOException, ClassNotFoundException
	{
		ObjectOutputStream outStream =
			new ObjectOutputStream(
				new FileOutputStream("personnel.dat"));

		Personnel[] staff =
			{new Personnel(123456,"Smith", "John"),
			 new Personnel(234567,"Jones", "Sally Ann"),
			 new Personnel(999999,"Black", "James Paul")};

		for (int i=0; i<staff.length; i++)
			outStream.writeObject(staff[i]);
		outStream.close();

		ObjectInputStream inStream =
			new ObjectInputStream(
				new FileInputStream("personnel.dat"));

		int staffCount = 0;

		try
		{
			do
			{
				Personnel person =
					(Personnel)inStream.readObject();//Typecast.

				staffCount++;

				System.out.println(
				   		"\nStaff member " + staffCount);
				System.out.println(
						"Payroll number: " + person.getPayNum());
				System.out.println(
						"Surname: " + person.getSurname());
				System.out.println(
						"First names: " + person.getFirstNames());
			}while (true);
		}
		catch (EOFException eofEx)
		{
			System.out.println(
				"\n\n*** End of file ***\n");
			inStream.close();
		}
	}
}

class Personnel implements Serializable
//No action required by Serializable interface.
{
	private long payrollNum;
	private String surname;
	private String firstNames;

	public Personnel(long payNum,String sName, String fNames)
	{
		payrollNum = payNum;
		surname = sName;
		firstNames = fNames;
	}

	public long getPayNum()
	{
		return payrollNum;
	}

	public String getSurname()
	{
		return surname;
	}

	public String getFirstNames()
	{
		return firstNames;
	}

	public void setSurname(String sName)
	{
		surname = sName;
	}
}
