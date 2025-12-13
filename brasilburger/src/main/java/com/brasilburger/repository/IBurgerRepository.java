
package com.brasilburger.repository;

import com.brasilburger.model.Burger;
import java.util.List;


public interface IBurgerRepository {
    Burger create(Burger burger);
    Burger findById(Integer id);
    List<Burger> findAll();
    List<Burger> findAllNonArchived();
    Burger update(Burger burger);
    boolean archive(Integer id);
    boolean delete(Integer id);
}

