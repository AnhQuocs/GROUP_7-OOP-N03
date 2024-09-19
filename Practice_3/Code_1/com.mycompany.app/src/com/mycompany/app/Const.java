package com.mycompany.app;

public class Const extends Node {
	private double value;

	public Const(double value) {
		this.value = value;
	}

	@Override
	public double eval() {
		return value;
	}
}
