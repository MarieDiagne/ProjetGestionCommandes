package com.brasilburger.service.impl;

import com.brasilburger.model.Composition;
import com.brasilburger.model.Menu;
import com.brasilburger.model.Burger;
import com.brasilburger.model.Complement;
import com.brasilburger.enums.TypeComplementEnum;
import com.brasilburger.repository.ICompositionRepository;
import com.brasilburger.repository.IMenuRepository;
import com.brasilburger.repository.IBurgerRepository;
import com.brasilburger.repository.IComplementRepository;
import com.brasilburger.repository.impl.CompositionRepositoryImpl;
import com.brasilburger.repository.impl.MenuRepositoryImpl;
import com.brasilburger.repository.impl.BurgerRepositoryImpl;
import com.brasilburger.repository.impl.ComplementRepositoryImpl;
import com.brasilburger.service.ICompositionService;
import com.brasilburger.utils.ValidationHelper;
import java.util.List;

public class CompositionServiceImpl implements ICompositionService {

    private final ICompositionRepository compositionRepository;
    private final IMenuRepository menuRepository;
    private final IBurgerRepository burgerRepository;
    private final IComplementRepository complementRepository;

    public CompositionServiceImpl() {
        this.compositionRepository = new CompositionRepositoryImpl();
        this.menuRepository = new MenuRepositoryImpl();
        this.burgerRepository = new BurgerRepositoryImpl();
        this.complementRepository = new ComplementRepositoryImpl();
    }

    @Override
    public Composition create(Composition composition) {
        if (!validateComposition(composition)) {
            return null;
        }

        
        Composition existing = compositionRepository.findByMenuId(composition.getMenuId());
        if (existing != null) {
            System.err.println("❌ Une composition existe déjà pour ce menu.");
            System.out.println("   Veuillez la modifier ou la supprimer d'abord.");
            return null;
        }

        Composition created = compositionRepository.create(composition);
        if (created != null) {
            System.out.println("✅ Composition créée avec succès ! (ID: " + created.getId() + ")");
        }
        return created;
    }

    @Override
    public Composition findById(Integer id) {
        if (!ValidationHelper.isValidId(id)) {
            System.err.println("❌ ID invalide.");
            return null;
        }

        Composition composition = compositionRepository.findById(id);
        if (composition == null) {
            System.err.println("❌ Aucune composition trouvée avec l'ID: " + id);
        }
        return composition;
    }

    @Override
    public Composition findByMenuId(Integer menuId) {
        if (!ValidationHelper.isValidId(menuId)) {
            System.err.println("❌ ID du menu invalide.");
            return null;
        }

        return compositionRepository.findByMenuId(menuId);
    }

    @Override
    public List<Composition> findAll() {
        return compositionRepository.findAll();
    }

    @Override
    public Composition update(Composition composition) {
        if (!ValidationHelper.isValidId(composition.getId())) {
            System.err.println("❌ ID de la composition invalide.");
            return null;
        }

        Composition existing = compositionRepository.findById(composition.getId());
        if (existing == null) {
            System.err.println("❌ Composition introuvable (ID: " + composition.getId() + ")");
            return null;
        }

        // Valider la nouvelle composition
        if (!validateCompositionElements(composition.getBurgerId(), composition.getBoissonId(),
                composition.getFritesId())) {
            return null;
        }

        Composition updated = compositionRepository.update(composition);
        if (updated != null) {
            System.out.println("✅ Composition modifiée avec succès !");
        }
        return updated;
    }

    @Override
    public boolean delete(Integer id) {
        if (!ValidationHelper.isValidId(id)) {
            System.err.println("❌ ID invalide.");
            return false;
        }

        Composition composition = compositionRepository.findById(id);
        if (composition == null) {
            System.err.println("❌ Composition introuvable (ID: " + id + ")");
            return false;
        }

        boolean deleted = compositionRepository.delete(id);
        if (deleted) {
            System.out.println("✅ Composition supprimée avec succès !");
        }
        return deleted;
    }

    
    private boolean validateComposition(Composition composition) {
        if (composition == null) {
            System.err.println("❌ La composition ne peut pas être nulle.");
            return false;
        }

        
        if (!ValidationHelper.validateId(composition.getMenuId(), "Menu")) {
            return false;
        }

        
        Menu menu = menuRepository.findById(composition.getMenuId());
        if (menu == null) {
            System.err.println("❌ Le menu avec l'ID " + composition.getMenuId() + " n'existe pas.");
            return false;
        }

        
        return validateCompositionElements(composition.getBurgerId(), composition.getBoissonId(),
                composition.getFritesId());
    }

  
    private boolean validateCompositionElements(Integer burgerId, Integer boissonId, Integer fritesId) {
        boolean valid = true;

        // Validation du burger
        if (!ValidationHelper.validateId(burgerId, "Burger")) {
            valid = false;
        } else {
            Burger burger = burgerRepository.findById(burgerId);
            if (burger == null) {
                System.err.println("❌ Le burger avec l'ID " + burgerId + " n'existe pas.");
                valid = false;
            } else if (burger.isArchive()) {
                System.err.println("⚠️  Attention : Le burger sélectionné est archivé.");
            }
        }

        
        if (!ValidationHelper.validateId(boissonId, "Boisson")) {
            valid = false;
        } else {
            Complement boisson = complementRepository.findById(boissonId);
            if (boisson == null) {
                System.err.println("❌ Le complément avec l'ID " + boissonId + " n'existe pas.");
                valid = false;
            } else if (boisson.getType() != TypeComplementEnum.BOISSON) {
                System.err.println("❌ Le complément sélectionné n'est pas une boisson.");
                valid = false;
            } else if (boisson.isArchive()) {
                System.err.println("⚠️  Attention : La boisson sélectionnée est archivée.");
            }
        }

        
        if (!ValidationHelper.validateId(fritesId, "Frites")) {
            valid = false;
        } else {
            Complement frites = complementRepository.findById(fritesId);
            if (frites == null) {
                System.err.println("❌ Le complément avec l'ID " + fritesId + " n'existe pas.");
                valid = false;
            } else if (frites.getType() != TypeComplementEnum.FRITES) {
                System.err.println("❌ Le complément sélectionné n'est pas des frites.");
                valid = false;
            } else if (frites.isArchive()) {
                System.err.println("⚠️  Attention : Les frites sélectionnées sont archivées.");
            }
        }

        return valid;
    }
}