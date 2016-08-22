public class CommandLineArguments {

	public static void main(String[] args) {
		String number = args[0];
		String word = args[1];
		int numberAsInt = Integer.parseInt(number);

		for(int i = 0; i < numberAsInt; i++) {
			System.out.println(word);
		}
	}

}
