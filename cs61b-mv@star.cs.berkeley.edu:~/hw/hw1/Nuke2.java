import java.io.*;
class Nuke2 {
	public static void main(String[] arg) throws Exception {
		BufferedReader keyboard;
		StringBuilder inputWord;
		
		keyboard = new BufferedReader(new InputStreamReader(System.in));
		inputWord = new StringBuilder(keyboard.readLine());

		inputWord.deleteCharAt(1);
		System.out.println(inputWord);
	}
}