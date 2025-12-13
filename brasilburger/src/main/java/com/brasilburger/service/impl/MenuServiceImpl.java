package com.brasilburger.service.impl;

import com.brasilburger.model.Menu;
import com.brasilburger.model.Composition;
import com.brasilburger.repository.IMenuRepository;
import com.brasilburger.repository.ICompositionRepository;
import com.brasilburger.repository.impl.MenuRepositoryImpl;
import com.brasilburger.repository.impl.CompositionRepositoryImpl;
import com.brasilburger.service.IMenuService;
import com.brasilburger.utils.ValidationHelper;

import java.util.List;

public class MenuServiceImpl implements IMenuService {

    private final IMenuRepository menuRepository;
    private final ICompositionRepository compositionRepository;

    public MenuServiceImpl() {
        this.menuRepository = new MenuRepositoryImpl();
        this.compositionRepository = new CompositionRepositoryImpl();
    }

    @Override
    public Menu create(Menu menu) {
        if (!validateMenu(menu)) {
            return null;
        }

        menu.setNom(ValidationHelper.cleanString(menu.getNom()));
        return menuRepository.create(menu);
    }

    @Override
    public Menu findById(Integer id) {
        if (!ValidationHelper.isValidId(id)) {
            return null;
        }
        return menuRepository.findById(id);
    }

    @Override
    public Menu findByIdWithComposition(Integer id) {
        Menu menu = findById(id);
        if (menu == null) {
            return null;
        }

        Composition composition = compositionRepository.findByMenuId(id);
        if (composition != null) {
            menu.setComposition(composition);
            menu.calculerPrix();
        }

        return menu;
    }

    private boolean validateMenu(Menu menu) {
        if (!ValidationHelper.validateNotEmpty(menu.getNom(), "Nom")) {
            return false;
        }

        if (!ValidationHelper.validateLength(menu.getNom(), 3, 100, "Nom")) {
            return false;
        }

        if (menu.getImage() != null && !menu.getImage().trim().isEmpty()) {
            return ValidationHelper.validateCloudinaryUrl(menu.getImage(), "Image");
        }

        return true;
    }

    @Override
    public Menu update(Menu menu) {
        if (!ValidationHelper.isValidId(menu.getId())) {
            return null;
        }

        if (!validateMenu(menu)) {
            return null;
        }

        menu.setNom(ValidationHelper.cleanString(menu.getNom()));
        return menuRepository.update(menu);
    }

    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public List<Menu> findAllNonArchived() {
        return menuRepository.findAllNonArchived();
    }

    @Override
    public List<Menu> findAllWithComposition() {
        List<Menu> menus = menuRepository.findAll();

        for (Menu menu : menus) {
            Composition composition = compositionRepository.findByMenuId(menu.getId());
            if (composition != null) {
                menu.setComposition(composition);
                menu.calculerPrix();
            }
        }
        return menus;
    }
    @Override
    public boolean archive(Integer id) {
        if (!ValidationHelper.isValidId(id)) {
            return false;
        }

        Menu menu = menuRepository.findById(id);
        if (menu == null || menu.isArchive()) {
            return false;
        }

        return menuRepository.archive(id);
    }
 

}