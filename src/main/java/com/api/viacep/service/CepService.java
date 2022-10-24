package com.api.viacep.service;

import com.api.viacep.client.CepClient;
import com.api.viacep.model.CepModel;
import com.api.viacep.model.response.CepResponse;
import com.api.viacep.repository.CepRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CepService {

    private final CepRepository cepRepository;

    private final CepClient cepClient;

    public CepService(CepRepository cepRepository, CepClient cepClient) {
        this.cepRepository = cepRepository;
        this.cepClient = cepClient;
    }

    public CepResponse getCep(String cep) {
        CepResponse response = cepClient.getCep(cep);
        if (response == null) {
            throw new ObjectNotFoundException(CepModel.class,
                    "Object Not Found! CEP: " + response.getCep());
        }
        insert(response);
        return response;
    }

    public CepModel findByCep(String cep) {
        CepModel object = cepRepository.findByCep(cep);
        if (object != null) {
            throw new DataIntegrityViolationException("Cep Já Cadastrado! CEP: " + cep);
        }
        return object;
    }

    public CepModel insert(CepResponse response) {
        CepModel model = new CepModel();
        model.setId(null);

        findByCep(response.getCep());
        model.setCep(response.getCep());
        model.setUf(response.getUf());
        model.setBairro(response.getBairro());
        model.setLocalidade(response.getLocalidade());
        model.setComplemento(response.getComplemento());
        model.setLogradouro(response.getLogradouro());

        return cepRepository.save(model);
    }

}
