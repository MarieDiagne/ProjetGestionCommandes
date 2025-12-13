package com.brasilburger.repository;

import com.brasilburger.enums.TypeComplementEnum;
import com.brasilburger.model.Complement;
import java.util.List;

public interface IComplementRepository {
    Complement create(Complement complement);

    Complement findById(Integer id);

    List<Complement> findAll();

    List<Complement> findAllNonArchived();

    List<Complement> findByType(TypeComplementEnum type);

    List<Complement> findByTypeNonArchived(TypeComplementEnum type);

    Complement update(Complement complement);

    boolean archive(Integer id);

    boolean delete(Integer id);
}
