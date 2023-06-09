package advanced;

import java.io.File;
import java.io.FilenameFilter;

public class FileEg {

	public static void main(String[] args) {
		File dir = new File("./filers");
		Filter filter = new Filter("b");
		String[] strlist = dir.list(filter);
		for (String fileName : strlist) {
			File file = new File(dir, fileName);
			if (file.isFile()) {
				System.out.println("ÎÄ¼þ£º" + fileName);
			} else {
				System.out.println("Ä¿Â¼£º" + fileName);
			}
		}
	}
}

class Filter implements FilenameFilter {

	String str;

	public Filter(String str) {
		// TODO Auto-generated constructor stub
		this.str = str;
	}

	@Override
	public boolean accept(File dir, String name) {
		// TODO Auto-generated method stub
		return name.contains(str);
	}

}