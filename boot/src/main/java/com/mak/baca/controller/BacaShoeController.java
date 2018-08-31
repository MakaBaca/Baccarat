package com.mak.baca.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mak.baca.dao.BacaShoeRepo;
import com.mak.baca.dao.ShoeRepo;
import com.mak.baca.model.BacaShoe;
import com.mak.baca.model.BacaShoeAndHand;
import com.mak.baca.model.Shoe;
import com.mak.casino.Baccarat.Outcome;
import com.mak.simulator.AbstractBacaSimulator;

import application.mak.btc.BtcScoreCalc;
import application.mak.btc.BtcScoreCard;

@Controller
public class BacaShoeController extends AbstractBacaSimulator {

	@Autowired
	BacaShoeRepo repo;

	@Autowired
	ShoeRepo sRepo;

	@RequestMapping("/ShoeGenerator")
	public String home(){
		return "ShoeGenerator.jsp";
	}

	List<BtcScoreCard> btcList = null;

	public BacaShoeController() {
		prepareGame(78);
		game.prepareShoe();
		btcList = new ArrayList<>();
	}

	@RequestMapping("/")
	public ModelAndView game(){
		return new ModelAndView("Baca.jsp","btcList",btcList);
	}

	@RequestMapping("/generateShoe")
	public ModelAndView generateShoe(){
		Shoe shoe = new Shoe();
		sRepo.save(shoe);
		BacaShoe result = new BacaShoe();
		prepareGame(78);
		game.prepareShoe();
		int handNumber = 0;
		while(game.hasNextHand()){
			handNumber++;
			result = new BacaShoe();
			result.setOutcome(game.dealOneHand());
			result.setShoe(shoe);
			BacaShoeAndHand sh = new BacaShoeAndHand();
			//sh.setShoeNumber(shoeNumber);
			sh.setHandNumber(handNumber);
			result.setSh(sh);
			repo.save(result);
		}
		List<BacaShoe> list= new ArrayList<>();
		for(BacaShoe bs:repo.findAll()){
			list.add(bs);
		}
		return new ModelAndView("ShoeGenerator.jsp","shoeList",list);
	}

	@RequestMapping(value="/processForm", params="Deal", method=RequestMethod.POST)
	public ModelAndView deal(String wageredOn, int unit){
		Outcome outcome = null;
		if(game.hasNextHand()){
			outcome = game.dealOneHand();
		}
		if(outcome == Outcome.tie){
			return new ModelAndView("Baca.jsp","btcList",btcList);
		}

		if(wageredOn.equalsIgnoreCase("player")){
			calc.recordOutcome(outcome, unit, true);
		}else if(wageredOn.equalsIgnoreCase("banker")){
			calc.recordOutcome(outcome, unit, false);
		}else{
			calc.recordOutcome(outcome, 0, false);
		}
		BtcScoreCard card = calc.getScoreCard();
		btcList.add(card);
		return new ModelAndView("Baca.jsp","btcList",btcList);
	}

	@RequestMapping(value="/processForm", params="New", method=RequestMethod.POST)
	public ModelAndView newShoe(){
		prepareGame(78);
		game.prepareShoe();
		calc = new BtcScoreCalc(false);
		btcList = new ArrayList<>();
		return new ModelAndView("Baca.jsp","btcList",btcList);
	}
}
