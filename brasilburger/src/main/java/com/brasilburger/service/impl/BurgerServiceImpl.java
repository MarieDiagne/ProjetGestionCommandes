package com.brasilburger.service.impl;

import com.brasilburger.model.Burger;
import com.brasilburger.repository.IBurgerRepository;
import com.brasilburger.repository.impl.BurgerRepositoryImpl;
import com.brasilburger.service.IBurgerService;
import com.brasilburger.utils.ValidationHelper;

import java.math.BigDecimal;
import java.util.List;

public class BurgerServiceImpl implements IBurgerService {

    private final IBurgerRepository burgerRepository;

    public BurgerServiceImpl() {
        this.burgerRepository = new BurgerRepositoryImpl();
    }

    @Override
    public Burger create(Burger burger) {
        if (!validateBurger(burger)) {
            return null;
        }

        cleanBurger(burger);
        return burgerRepository.create(burger);
    }
    private boolean validateBurger(Burger burger) {

        if (!ValidationHelper.validateNotEmpty(burger.getNom(), "Nom")) {
            return false;
        }

        if (!ValidationHelper.validateLength(burger.getNom(), 3, 100, "Nom")) {
            return false;
        }

        if (!ValidationHelper.validateNotEmpty(burger.getDescription(), "Description")) {
            return false;
        }

        if (!ValidationHelper.validateLength(burger.getDescription(), 5, 255, "Description")) {
            return false;
        }

        BigDecimal prix = burger.getPrix();
        if (prix == null || prix.compareTo(BigDecimal.ZERO) <= 0) {
            System.err.println("❌ Prix invalide");
            return false;
        }

        if (burger.getImage() != null && !burger.getImage().trim().isEmpty()) {
            if (!ValidationHelper.validateCloudinaryUrl(burger.getImage(), "Image")) {
                return false;
            }
        }

        return true;
    }

    private void cleanBurger(Burger burger) {
        burger.setNom(ValidationHelper.cleanString(burger.getNom()));
        burger.setDescription(ValidationHelper.cleanString(burger.getDescription()));
    }
        @Override
    public Burger findById(Integer id) {
        if (!ValidationHelper.isValidId(id)) {
            return null;
        }
        return burgerRepository.findById(id);
    }

    @Override
    public List<Burger> findAll() {
        return burgerRepository.findAll();
    }

    @Override
    public List<Burger> findAllNonArchived() {
        return burgerRepository.findAllNonArchived();
    }
        @Override
    public Burger update(Burger burger) {
        if (!ValidationHelper.isValidId(burger.getId())) {
            return null;
        }

        if (!validateBurger(burger)) {
            return null;
        }

        cleanBurger(burger);
        return burgerRepository.update(burger);
    }

}