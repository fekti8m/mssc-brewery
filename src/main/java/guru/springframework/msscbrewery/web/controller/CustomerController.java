package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    public CustomerService service;

    CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID customerId) {
        return new ResponseEntity<>(this.service.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerDto customerDto) {
        if (this.service.createCustomer(customerDto)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @PathVariable UUID customerId, @RequestBody CustomerDto customerDto) {
        var result = this.service.updateCustomer(customerId, customerDto);
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable UUID customerId) {
        if (this.service.deleteCustomer(customerId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
