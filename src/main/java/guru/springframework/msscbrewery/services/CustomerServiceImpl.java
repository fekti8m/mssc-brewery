package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final ArrayList<CustomerDto> customerDtos = new ArrayList<>();

    @Override
    public CustomerDto getCustomerById(UUID id) {
        return CustomerDto.builder()
                .ID(id)
                .name("Max Muster")
                .build();
    }

    @Override
    public boolean createCustomer(CustomerDto customerDto) {
        return this.customerDtos.add(customerDto);
    }

    @Override
    public Optional<CustomerDto> updateCustomer(UUID id, CustomerDto customerDto) {
        var foundObj = this.customerDtos.stream().filter(obj -> obj.getID() == id).findFirst();
        if(foundObj.isEmpty()) {
            return Optional.empty();
        } else {
            var index = this.customerDtos.indexOf(foundObj.get());
            this.customerDtos.set(index, customerDto);
            return Optional.ofNullable(this.customerDtos.get(index));
        }
    }

    @Override
    public boolean deleteCustomer(UUID id) {
        return this.customerDtos.removeIf((obj) -> obj.getID() == id);
    }
}
