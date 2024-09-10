package midtermtest02;

public class DiscreteSignal {

	public double x(int n, double[] a) {
		double result = 0;
		for (int k = 0; k < a.length; k++) {
			result += a[k] * d(n - k);
		}
		return result;
	}

	private int d(int n) {
		return (n == 0) ? 1 : 0;
	}
}
