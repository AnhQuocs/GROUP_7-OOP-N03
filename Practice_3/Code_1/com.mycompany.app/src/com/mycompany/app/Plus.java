package com.mycompany.app;

public class Plus extends Node {
	private Node left, right;

	public Plus(Node left, Node right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public double eval() {
		return left.eval() + right.eval();
	}
}