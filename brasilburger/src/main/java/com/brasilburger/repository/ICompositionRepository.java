
package com.brasilburger.repository;

import com.brasilburger.model.Composition;
import java.util.List;

public interface ICompositionRepository {
    Composition create(Composition composition);
    Composition findById(Integer id);
    Composition findByMenuId(Integer menuId);
    List<Composition> findAll();
    Composition update(Composition composition);
    boolean delete(Integer id);
    boolean deleteByMenuId(Integer menuId);
}

