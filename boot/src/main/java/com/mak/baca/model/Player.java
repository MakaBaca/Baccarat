package com.mak.baca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	int playerId;

	double balance;

	double winings;

	double invested;

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getWinings() {
		return winings;
	}

	public void setWinings(double winings) {
		this.winings = winings;
	}

	public double getInvested() {
		return invested;
	}

	public void setInvested(double invested) {
		this.invested = invested;
	}

	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", balance=" + balance + ", winings=" + winings + ", invested="
				+ invested + "]";
	}

}
