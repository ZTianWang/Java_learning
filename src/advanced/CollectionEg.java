package advanced;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionEg {
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		long a = list.get(0);
//		System.out.println(a);
		
		Set set = new HashSet();
		String s1 = "abc";
		String s2 = "abc";
		String s3 = new String("abc");
		set.add(s1);
		set.add(s2);
		set.add(s3);
		set.add(null);
//		System.out.println(s1 == s2 );
//		System.out.println(s1 == s3);
//		System.out.println(set.size());
		
		Map map = new HashMap();
		map.put(01, "a");
		System.out.println(map.get(01));
		map.put(01, "b");
		System.out.println(map.get(01));
		map.put(02, "c");
		
	}
}
