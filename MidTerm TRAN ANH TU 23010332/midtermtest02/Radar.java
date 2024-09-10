package midtermtest02;

public class Radar {

	public double y(int n) {
		if (n >= 0 && n <= 15) {
			return 1 - (double) n / 15;
		} else {
			return 0;
		}
	}

	public void printSignal(int n) {
		for (int i = 0; i <= n; i++) {
			System.out.println("y(" + i + ") = " + y(i));
		}
	}
}