package advanced;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExceptionEg {

	public static void main(String[] args) {
		ExceptionEg eg = new ExceptionEg();
		try {
			eg.getDate();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
	}

	public void getDate() throws FileNotFoundException {
//		FileInputStream readFile;
//		InputStreamReader ir;
//		BufferedReader in;
		try (FileInputStream readFile = new FileInputStream("a.text");
				InputStreamReader ir = new InputStreamReader(readFile);
				BufferedReader in = new BufferedReader(ir)) {
			
			String str = in.readLine();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new FileNotFoundException("new");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
