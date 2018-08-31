package com.mak.baca.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class PlayedHands {

	@EmbeddedId
	PlayedHandPK pk;

	@MapsId("playerId")
	@ManyToOne
	Player player;

	@MapsId("sh")
	@JoinColumns({
		@JoinColumn(name="shoeNumber", referencedColumnName="shoe_shoe_Number"),
		@JoinColumn(name="handNumber", referencedColumnName="handNumber")
	})
	@ManyToOne
	BacaShoe bacaShoe;

	long betAmount = 0;

	long amountWon = 0;

	boolean didWin = false;

	public PlayedHandPK getPk() {
		return pk;
	}

	public void setPk(PlayedHandPK pk) {
		this.pk = pk;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public BacaShoe getBacaShoe() {
		return bacaShoe;
	}

	public void setBacaShoe(BacaShoe bacaShoe) {
		this.bacaShoe = bacaShoe;
	}

	public long getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(long betAmount) {
		this.betAmount = betAmount;
	}

	public long getAmountWon() {
		return amountWon;
	}

	public void setAmountWon(long amountWon) {
		this.amountWon = amountWon;
	}

	public boolean isDidWin() {
		return didWin;
	}

	public void setDidWin(boolean didWin) {
		this.didWin = didWin;
	}

	@Override
	public String toString() {
		return "PlayedHands [pk=" + pk + ", player=" + player + ", bacaShoe=" + bacaShoe + ", betAmount=" + betAmount
				+ ", amountWon=" + amountWon + ", didWin=" + didWin + "]";
	}
}
