package test;

public class ContinuousSignal implements Signal {
	private double amplitude;
	private double frequency;
	private double period;
	private double wavelength;

	public ContinuousSignal(double amplitude, double frequency, double period, double wavelength) {
		this.amplitude = amplitude;
		this.frequency = frequency;
		this.period = period;
		this.wavelength = wavelength;
	}

	public void transmit() {
		System.out.println("Transmitting continuous signal with amplitude: " + amplitude + ", frequency: " + frequency);
	}

	public void receive() {
		System.out.println("Receiving continuous signal with amplitude: " + amplitude + ", frequency: " + frequency);
	}
}