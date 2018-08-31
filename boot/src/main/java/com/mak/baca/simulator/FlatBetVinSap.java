package com.mak.baca.simulator;

import com.mak.simulator.VinSapSimulator;

import application.mak.btc.BtcScoreCard;

public class FlatBetVinSap extends VinSapSimulator{

	int disparity = 2;

	@Override
	public Bet whatToPlay() {
		int potentialEvent = calc.getPotentialEvent();
		BtcScoreCard scoreCard = getScoreCard();
		int ones = scoreCard.getOneInARow();
		int twos = scoreCard.getTwoInARow();
		int threePlus = scoreCard.getThreeOrMoreInARow();
		// Check for bias on 1's
		if (ones - (twos+threePlus) >= disparity && potentialEvent == 1) {
			return Bet.opposite;
		}
		// Check for bias on 2's against 1's or 2's and 3's against 1's.
		else if ((twos - ones >= disparity || (twos+threePlus) - ones >= disparity) && potentialEvent == 1) {
			return Bet.repeat;
		}
		// Check for bias on 2's against 1's.
		/*else if (twos - ones >= disparity && potentialEvent == 1) {
			if(twos == bbTwoEvent && getTotalHands() < bbTwob4Hand){
				negativeProgression = true;
			}
			return Bet.repeat;
		}*/
		// Check for bias on 2's against 3's
		else if (twos - threePlus >= disparity && potentialEvent == 2) {
			return Bet.opposite;
		}
		// Check for bias on 3's against 2's
		else if (threePlus - twos >= disparity && potentialEvent == 2) {
			return Bet.repeat;
		}

		return Bet.noBet;
	}
}
