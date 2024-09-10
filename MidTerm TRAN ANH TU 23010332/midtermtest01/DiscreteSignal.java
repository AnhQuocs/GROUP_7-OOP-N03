package test;

public class DiscreteSignal implements Signal {
	private int amplitude;
	private int frequency;
	private int period;
	private int wavelength;

	public DiscreteSignal(int amplitude, int frequency, int period, int wavelength) {
		this.amplitude = amplitude;
		this.frequency = frequency;
		this.period = period;
		this.wavelength = wavelength;
	}

	public void transmit() {
		System.out.println("Transmitting discrete signal with amplitude: " + amplitude + ", frequency: " + frequency);
	}

	public void receive() {
		System.out.println("Receiving discrete signal with amplitude: " + amplitude + ", frequency: " + frequency);
	}
}