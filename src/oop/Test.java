package oop;

import java.util.Comparator;
import java.util.TreeSet;

public class Test {
	public static void main(String[] args) {
		
//		TreeSet<Person> set = new TreeSet<>(new PersonComparator());
//		
//		Person Lee = new Person("Lee",25);
//		Person leo = new Person("Leo",23);
//		Person wnwn = new Person("wnwn",21);
//		
//		set.add(Lee);
//		set.add(leo);
//		set.add(wnwn);
//		
//		System.out.println(set);
		
		Son[] sons = new Son[10];
		System.out.println(sons.getClass().getName());
		
	}
}

class Person{
	
	public String name;
	public int age;
	
	Person(String name,int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

}

class PersonComparator implements Comparator<Person>{

	@Override
	public int compare(Person o1, Person o2) {
		int sum = o1.age - o2.age;
		return sum == 0 ? o1.name.compareTo(o2.name) : sum;
	}
	
	
}
