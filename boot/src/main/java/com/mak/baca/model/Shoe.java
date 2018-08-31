package com.mak.baca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Shoe {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	int shoeNumber;

	public int getShoeNumber() {
		return shoeNumber;
	}

	public void setShoeNumber(int shoeNumber) {
		this.shoeNumber = shoeNumber;
	}

	@Override
	public String toString() {
		return "ShoeModel [shoeNumber=" + shoeNumber + "]";
	}
}
