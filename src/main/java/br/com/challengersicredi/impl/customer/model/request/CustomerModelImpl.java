package br.com.challengersicredi.impl.customer.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class CustomerModelImpl {
    private String id;
    private String name;
    private String cpf;
}
