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
        @Override
    public Complement findById(Integer id) {
        if (!ValidationHelper.isValidId(id)) {
            System.err.println("❌ ID invalide.");
            return null;
        }

        Complement complement = complementRepository.findById(id);
        if (complement == null) {
            System.err.println("❌ Aucun complément trouvé avec l'ID: " + id);
        }
        return complement;
    }

    @Override
    public List<Complement> findAll() {
        return complementRepository.findAll();
    }

    @Override
    public List<Complement> findAllNonArchived() {
        return complementRepository.findAllNonArchived();
    }

    @Override
    public List<Complement> findByType(TypeComplementEnum type) {
        if (type == null) {
            System.err.println("❌ Type de complément invalide.");
            return List.of();
        }
        return complementRepository.findByType(type);
    }

    @Override
    public List<Complement> findByTypeNonArchived(TypeComplementEnum type) {
        if (type == null) {
            System.err.println("❌ Type de complément invalide.");
            return List.of();
        }
        return complementRepository.findByTypeNonArchived(type);
    }
    @Override
    public Complement update(Complement complement) {
        if (!ValidationHelper.isValidId(complement.getId())) {
            System.err.println("❌ ID du complément invalide.");
            return null;
        }

        Complement existing = complementRepository.findById(complement.getId());
        if (existing == null) {
            System.err.println("❌ Complément introuvable (ID: " + complement.getId() + ")");
            return null;
        }

        if (!validateComplement(complement)) {
            return null;
        }

        complement.setNom(ValidationHelper.cleanString(complement.getNom()));

        Complement updated = complementRepository.update(complement);
        if (updated != null) {
            System.out.println("✅ Complément modifié avec succès !");
        }
        return updated;
    }
        @Override
    public boolean archive(Integer id) {
        if (!ValidationHelper.isValidId(id)) {
            System.err.println("❌ ID invalide.");
            return false;
        }

        Complement complement = complementRepository.findById(id);
        if (complement == null) {
            System.err.println("❌ Complément introuvable (ID: " + id + ")");
            return false;
        }

        if (complement.isArchive()) {
            System.out.println("⚠️  Ce complément est déjà archivé.");
            return true;
        }

        boolean archived = complementRepository.archive(id);
        if (archived) {
            System.out.println("✅ Complément archivé avec succès !");
        }
        return archived;
    }


}