package com.api.viacep.controller;

import com.api.viacep.model.response.CepResponse;
import com.api.viacep.service.CepService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cep")
public class CepController {

    private CepService cepService;

    @Autowired
    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    @ResponseStatus(HttpStatus.OK)
    public CepResponse findByCep(@PathVariable String cep) {
        return cepService.getCep(cep);
    }
}
