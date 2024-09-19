package com.mycompany.app;

class CellPhone {
	CellPhone() {

	}

	public void ring(Tune t) {
		t.play();
	}
}

class Tune {
	public void play() {
		System.out.println("Tune.play()");
	}
}

class ObnoxiousTune extends Tune {
	ObnoxiousTune() {

	}

}

public class DisruptLecture {
	public static void main(String[] args) {
		CellPhone noiseMaker = new CellPhone();
		ObnoxiousTune ot = new ObnoxiousTune();
		noiseMaker.ring(ot);
	}
}
