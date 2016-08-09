package edu.softserveinc.healthbody.controller;

public class Pair<LEFT, RIGHT> {
	private LEFT leftObject;
	private RIGHT rightObject;

	public Pair(LEFT leftObject, RIGHT rightObject) {
		this.leftObject= leftObject;
		this.rightObject = rightObject;
	}

	public LEFT getLeftObject() {
		return leftObject;
	}

	public RIGHT getRightObject() {
		return rightObject;
	}

	public void setLeftObject(LEFT leftObject) {
		this.leftObject = leftObject;
	}

	public void setRightObject(RIGHT rightObject) {
		this.rightObject = rightObject;
	}
}
