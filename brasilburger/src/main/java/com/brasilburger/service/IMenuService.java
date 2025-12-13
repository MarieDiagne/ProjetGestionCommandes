package com.brasilburger.service;

import com.brasilburger.model.Menu;
import java.util.List;

public interface IMenuService {

    Menu create(Menu menu);

    Menu findById(Integer id);

    Menu findByIdWithComposition(Integer id);
}