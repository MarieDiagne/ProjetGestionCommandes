package com.brasilburger.service;

import com.brasilburger.model.Burger;
import java.util.List;

public interface IBurgerService {

    Burger create(Burger burger);
    Burger findById(Integer id);

    List<Burger> findAll();

    List<Burger> findAllNonArchived();
}