package com.mak.baca.dao;

import org.springframework.data.repository.CrudRepository;

import com.mak.baca.model.PlayedHandPK;
import com.mak.baca.model.PlayedHands;

public interface PlayedHandsRepo extends CrudRepository<PlayedHands, PlayedHandPK>{

}
