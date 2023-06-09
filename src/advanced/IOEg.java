package advanced;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class IOEg {

	public static void main(String[] args) {
		
		File iFile = new File("./files/a.txt");
		File oFile = new File("./files/af/copy.txt");
		
		try (FileInputStream in = new FileInputStream(iFile);
				InputStreamReader isr = new InputStreamReader(in);//字节流转换字符流
//				BufferedInputStream bin = new BufferedInputStream(in);
				BufferedReader br = new BufferedReader(isr);
				FileOutputStream out = new FileOutputStream(oFile,true);
				OutputStreamWriter osw = new OutputStreamWriter(out);
//				BufferedOutputStream bout = new BufferedOutputStream(out);
				BufferedWriter bw = new BufferedWriter(osw);
				){
			
//			byte[] buffer = new byte[16];
			
			String str = br.readLine();
////		int len = in.read(buffer);
//			int len = bin.read(buffer);
			
//			while (len != -1) {
			while (str != null) {
				System.out.println(str);
////			out.write(buffer, 0, len);
//				bout.write(buffer, 0, len);
//				len = bin.read(buffer);
				bw.write(str);
				bw.newLine();
				str = br.readLine();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
	}
}
