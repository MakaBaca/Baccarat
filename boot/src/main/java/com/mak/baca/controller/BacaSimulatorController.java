package com.mak.baca.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mak.baca.simulator.FlatBetVinSap;
import com.mak.casino.Baccarat.Outcome;

import application.mak.btc.BtcScoreCalc;
import application.mak.btc.BtcScoreCard;

@Controller
public class BacaSimulatorController extends FlatBetVinSap {

	List<BtcScoreCard> btcList = null;
	Outcome previousOutcome = null;

	public BacaSimulatorController() {
		prepareGame(78);
		game.prepareShoe();
		btcList = new ArrayList<>();
	}

	@RequestMapping("/vinSapAction")
	public ModelAndView game(){
		return new ModelAndView("BacaVinSap.jsp","btcList",btcList);
	}

	@RequestMapping(value="/vinSapAction", params="Deal", method=RequestMethod.POST)
	public ModelAndView deal(String wager){
		Outcome outcome = null;
		Outcome systemSays = null;
		Bet bet;
		boolean canWager = false;
		if(wager.equalsIgnoreCase("bet")){
			canWager = true;
		}
		if(game.hasNextHand()){
			outcome = game.dealOneHand();
		}


		if(outcome == Outcome.tie){
			calc.recordOutcome(outcome, 0, false);
			return new ModelAndView("BacaVinSap.jsp","btcList",btcList);
		}
		bet = whatToPlay();
		if(bet == Bet.noBet){
			calc.recordOutcome(outcome, 0, false);
		}else if(bet == Bet.repeat){
			if(previousOutcome == Outcome.player){
				calc.recordOutcome(outcome, canWager ? 1:0, true);
				systemSays = Outcome.player;
			}
			else{
				calc.recordOutcome(outcome, canWager ? 1:0, false);
				systemSays = Outcome.banker;
			}
		}else if(bet == Bet.opposite){
			if(previousOutcome == Outcome.player){
				calc.recordOutcome(outcome, canWager ? 1:0, false);
				systemSays = Outcome.banker;
			}
			else{
				calc.recordOutcome(outcome, canWager ? 1:0, true);
				systemSays = Outcome.player;
			}
		}

		BtcScoreCard card = getScoreCard();
		if(bet != Bet.noBet && !canWager && outcome == systemSays ){
			if(outcome == Outcome.player){
				card.setPlayer("O  0");
			}else{
				card.setBanker("O  0");
			}
		}else if(bet != Bet.noBet && !canWager && outcome != systemSays ){
			if(outcome == Outcome.player){
				card.setBanker("0");
			}else{
				card.setPlayer("0");
			}
		}
		btcList.add(card);
		previousOutcome = outcome;
		return new ModelAndView("BacaVinSap.jsp","btcList",btcList);
	}

	@RequestMapping(value="/vinSapAction", params="New", method=RequestMethod.POST)
	public ModelAndView newShoe(){
		prepareGame(78);
		game.prepareShoe();
		calc = new BtcScoreCalc(false);
		btcList = new ArrayList<>();
		previousOutcome = null;
		return new ModelAndView("Baca.jsp","btcList",btcList);
	}
}
