package com.api.viacep.repository;

import com.api.viacep.model.CepModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CepRepository extends JpaRepository<CepModel, Long> {

    CepModel findByCep(String cep);
}
