package br.com.challengersicredi.impl.customer.model;

import br.com.challengersicredi.impl.customer.model.entity.CustomerModelEntity;
import br.com.challengersicredi.impl.customer.model.request.CustomerModelImpl;
import br.com.challengersicredi.impl.customer.model.response.CustomerImplReponse;
import reactor.core.publisher.Mono;

public class CustomerImplMapper {
    public static CustomerModelEntity mapFromEntity(CustomerModelImpl customerModel) {
        return CustomerModelEntity.builder()
                .id(customerModel.getId())
                .name(customerModel.getName())
                .cpf(customerModel.getCpf())
                .build();
    }

    public static CustomerImplReponse mapFromResponse (CustomerModelEntity model){
        return CustomerImplReponse.builder()
                .id(model.getId())
                .name(model.getName())
                .cpf(model.getCpf())
                .build();
    }
}
