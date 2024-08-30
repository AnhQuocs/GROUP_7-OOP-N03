package com.mycompany.app;

public class App {
	public static void main(String[] args) {
		Book myBook = new Book("AnhQuoc", "AnhTu", 74);
		System.out.println("Book title: " + myBook.title);
		System.out.println("Author: " + myBook.author);
		System.out.println("Number Page: " + myBook.numPages);

		NNCollection collection = new NNCollection();

		collection.insert(new NameNumber("AnhTu", "23010332"));
		collection.insert(new NameNumber("AnhQuoc", "23010333"));
		System.out.println("AnhTu number: " + collection.findNumber("AnhTu"));
		System.out.println("AnhQuoc number: " + collection.findNumber("AnhQuoc"));

		Recursion recursion = new Recursion();
		System.out.println("Factorial: " + recursion.factorial(4));
		System.out.println("Hello");
		Time time = new Time(7, 40, 45);
		System.out.println("Time: " + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());
	}
}
