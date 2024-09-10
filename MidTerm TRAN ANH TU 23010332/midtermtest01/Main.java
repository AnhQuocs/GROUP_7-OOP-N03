package test;

public class Main {
	public static void main(String[] args) {

		DiscreteSignal discreteSignal = new DiscreteSignal(10, 50, 20, 10);
		discreteSignal.transmit();
		discreteSignal.receive();

		ContinuousSignal continuousSignal = new ContinuousSignal(5.5, 60.0, 0.016, 8.0);
		continuousSignal.transmit();
		continuousSignal.receive();
	}
}