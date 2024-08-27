package week1;

public class Assignment1 {
	public static void main(String[] args) {
		Number n1 = new Number();
		Number n2 = new Number();
		n1.i = 2;
		n2.i = 5;
		n1.i = n2.i;
		n2.i = 10;
		System.out.println("n1.i = " + n1.i); 
	}
}
