package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerById(UUID id);
    boolean createCustomer(CustomerDto customerDto);
    Optional<CustomerDto> updateCustomer(UUID id, CustomerDto customerDto);
    boolean deleteCustomer(UUID id);
}
