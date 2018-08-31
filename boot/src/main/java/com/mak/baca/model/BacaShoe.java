package com.mak.baca.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.mak.casino.Baccarat.Outcome;

@Entity
public class BacaShoe {

	@EmbeddedId
	BacaShoeAndHand sh;

	Outcome outcome;

	@ManyToOne
	@MapsId("shoeNumber")
	Shoe shoe;

	public Shoe getShoe() {
		return shoe;
	}

	public void setShoe(Shoe shoe) {
		this.shoe = shoe;
	}

	public Outcome getOutcome() {
		return outcome;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}

	public BacaShoeAndHand getSh() {
		return sh;
	}

	public void setSh(BacaShoeAndHand sh) {
		this.sh = sh;
	}

	@Override
	public String toString() {
		return "BacaShoe [sh=" + sh + ", outcome=" + outcome + "]";
	}

}
