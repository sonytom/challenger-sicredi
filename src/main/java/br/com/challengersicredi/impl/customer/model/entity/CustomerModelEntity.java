package br.com.challengersicredi.impl.customer.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@AllArgsConstructor
@Data
@Document(collection = "customer")
public class CustomerModelEntity {
    private String id;
    private String name;
    @Indexed(unique = true)
    private String cpf;
}
