package br.com.challengersicredi.controller.v1.customer;

import br.com.challengersicredi.controller.v1.customer.model.mapper.CustomerMapperRequest;
import br.com.challengersicredi.controller.v1.customer.model.request.CustomerModelRequest;
import br.com.challengersicredi.impl.customer.CustomerService;
import br.com.challengersicredi.impl.customer.model.response.CustomerImplReponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/save")
    public Mono<CustomerImplReponse> customerSave(@RequestBody CustomerModelRequest customerModelRequest) {
        return customerService.saveCustomer(CustomerMapperRequest.mapFromImpl(customerModelRequest));
    }

}
