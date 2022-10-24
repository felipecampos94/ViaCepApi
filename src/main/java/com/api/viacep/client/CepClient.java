package com.api.viacep.client;

import com.api.viacep.model.response.CepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CepClient", url = "https://viacep.com.br")
public interface CepClient {

    @GetMapping(value = "/ws/{cep}/json/")
    CepResponse getCep(@PathVariable("cep") String cep);
}
