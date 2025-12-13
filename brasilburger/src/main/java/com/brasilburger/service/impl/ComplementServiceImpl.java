package com.brasilburger.service.impl;

import com.brasilburger.enums.TypeComplementEnum;
import com.brasilburger.model.Complement;
import com.brasilburger.repository.IComplementRepository;
import com.brasilburger.repository.impl.ComplementRepositoryImpl;
import com.brasilburger.service.IComplementService;
import com.brasilburger.utils.ValidationHelper;
import java.util.List;

public class ComplementServiceImpl implements IComplementService {

    private final IComplementRepository complementRepository;

    public ComplementServiceImpl() {
        this.complementRepository = new ComplementRepositoryImpl();
    }

    @Override
    public Complement create(Complement complement) {
        if (!validateComplement(complement)) {
            return null;
        }

        complement.setNom(ValidationHelper.cleanString(complement.getNom()));

        Complement created = complementRepository.create(complement);
        if (created != null) {
            System.out.println("✅ Complément créé avec succès ! (ID: " + created.getId() + ")");
        }
        return created;
    }
        private boolean validateComplement(Complement complement) {
        boolean valid = true;

        if (!ValidationHelper.validateNotEmpty(complement.getNom(), "Nom")) {
            valid = false;
        } else if (!ValidationHelper.validateLength(complement.getNom(), 3, 100, "Nom")) {
            valid = false;
        }

        if (complement.getType() == null) {
            System.err.println("❌ Le type de complément est obligatoire.");
            valid = false;
        }

        if (!ValidationHelper.validatePrice(complement.getPrix(), "Prix")) {
            valid = false;
        }

        if (complement.getImage() != null && !complement.getImage().trim().isEmpty()) {
            if (!ValidationHelper.validateCloudinaryUrl(complement.getImage(), "Image")) {
                valid = false;
            }
        }

        return valid;
    }
}