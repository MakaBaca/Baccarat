package com.mak.baca.dao;

import org.springframework.data.repository.CrudRepository;

import com.mak.baca.model.BacaShoe;
import com.mak.baca.model.BacaShoeAndHand;

public interface BacaShoeRepo extends CrudRepository<BacaShoe, BacaShoeAndHand>{

}
