package br.com.challengersicredi.impl.customer.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CustomerImplReponse {
    private String id;
    private String name;
    private String cpf;
}
