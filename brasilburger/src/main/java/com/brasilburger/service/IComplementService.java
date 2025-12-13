package com.brasilburger.service;

import com.brasilburger.enums.TypeComplementEnum;
import com.brasilburger.model.Complement;
import java.util.List;

public interface IComplementService {
    Complement create(Complement complement);
    List<Complement> findAll();
    List<Complement> findAllNonArchived();
    List<Complement> findByType(TypeComplementEnum type);
    List<Complement> findByTypeNonArchived(TypeComplementEnum type);
    Complement update(Complement complement);
    Complement findById(Integer id);
    

}