
import java.io.*;
//(Instead of the above line, we could simply prefix the
//occurrence of 'Serializable' with 'java.io.'.)


class Person implements Serializable
{
	private String name;
	private int age;
	private String address;

	public Person(String sName, int nAge, String sAddress){
		name = sName;
		age = nAge;
		address = sAddress;
	}

	public String getName(){
		return name;
	}
	public int getAge(){
		return age;
	}

	public String getAddress(){
		return address;
	}

}
