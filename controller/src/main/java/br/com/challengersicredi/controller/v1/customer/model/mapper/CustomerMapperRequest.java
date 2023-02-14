package br.com.challengersicredi.controller.v1.customer.model.mapper;

import br.com.challengersicredi.controller.v1.customer.model.request.CustomerModelRequest;
import br.com.challengersicredi.impl.customer.model.entity.CustomerModelEntity;
import br.com.challengersicredi.impl.customer.model.request.CustomerModelImpl;
import br.com.challengersicredi.impl.customer.model.response.CustomerImplReponse;

public class CustomerMapperRequest {

    public static CustomerModelImpl mapFromImpl (CustomerModelRequest request){
        return CustomerModelImpl.builder()
                .name(request.getName())
                .cpf(request.getCpf())
                .build();
    }


}
