package br.com.challengersicredi.impl.customer.model.repository;

import br.com.challengersicredi.impl.customer.model.entity.CustomerModelEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<CustomerModelEntity, String> {

}
