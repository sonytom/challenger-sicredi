package br.com.challengersicredi.impl.customer;

import br.com.challengersicredi.impl.customer.model.CustomerImplMapper;
import br.com.challengersicredi.impl.customer.model.repository.CustomerRepository;
import br.com.challengersicredi.impl.customer.model.request.CustomerModelImpl;
import br.com.challengersicredi.impl.customer.model.response.CustomerImplReponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Mono<CustomerImplReponse> saveCustomer(CustomerModelImpl customerModel) {
        return customerRepository.save(CustomerImplMapper.mapFromEntity(customerModel))
                .map(CustomerImplMapper::mapFromResponse)
                .switchIfEmpty(Mono.empty());
    }
}

