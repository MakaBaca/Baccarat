package com.mak.baca.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class BacaShoeAndHand implements Serializable{

	int shoeNumber;

	int handNumber;

	public int getShoeNumber() {
		return shoeNumber;
	}

	public void setShoeNumber(int shoeNumber) {
		this.shoeNumber = shoeNumber;
	}

	public void setHandNumber(int handNumber) {
		this.handNumber = handNumber;
	}

	public BacaShoeAndHand() {
	}

	public int getHandNumber() {
		return handNumber;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
        if (!(obj instanceof BacaShoeAndHand)) return false;
        BacaShoeAndHand that = (BacaShoeAndHand) obj;
        return Objects.equals(getShoeNumber(), that.getShoeNumber()) &&
                Objects.equals(getHandNumber(), that.getHandNumber());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getShoeNumber(), getHandNumber());
	}
}
