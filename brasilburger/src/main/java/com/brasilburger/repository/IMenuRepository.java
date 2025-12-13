package com.brasilburger.repository;

import com.brasilburger.model.Menu;
import java.util.List;

public interface IMenuRepository {
    Menu create(Menu menu);
    Menu findById(Integer id);
    List<Menu> findAll();
    List<Menu> findAllNonArchived();
    Menu update(Menu menu);
    boolean archive(Integer id);
    boolean delete(Integer id);
}

