package com.mycompany.app;

abstract class Glyph {
	abstract void draw();

	Glyph() {
		System.out.println("Glyph() before draw");
		draw();
		System.out.println("Glyph() after draw");
	}
}

class RoundGlyph extends Glyph {
	int radius = 1;

	RoundGlyph(int r) {
		radius = r;
		System.out.println("RoundGlyph(), radius = " + radius);
	}

	void draw() {
		System.out.println("RoundGlyph.draw(), radius = " + radius);
	}
}

public class GlyphTest {
	public static void main(String[] args) {
		new RoundGlyph(5);
	}
}
