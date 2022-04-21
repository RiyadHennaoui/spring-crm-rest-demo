package com.luv2code.springdemo.rest;


import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService costumerService;

    @GetMapping("/costumers")
    public List<Customer> getCustomers(){
        return costumerService.getCustomers();
    }

    @GetMapping("/costumers/{costumerId}")
    public Customer getCustomer(@PathVariable int costumerId){
        Customer customer = costumerService.getCustomer(costumerId);

        if (customer == null){
            throw new CustomerNotFoundException("Customer id not found - " + costumerId);
        }
        return customer;
    }

    @PostMapping("/costumers")
    public Customer addCustomer(@RequestBody Customer customer){


        customer.setId(0);

        costumerService.saveCustomer(customer);

        return customer;
    }

    @PutMapping("/costumers")
    public Customer updateCustomer(@RequestBody Customer customer){


        costumerService.saveCustomer(customer);

        return customer;
    }

    @DeleteMapping("/costumers/{customerId}")
    public String updateCustomer(@PathVariable int customerId){

        Customer customer = costumerService.getCustomer(customerId);

        if (customer == null){
            throw new CustomerNotFoundException("Customer id not found - " + customerId);
        }

        costumerService.deleteCustomer(customerId);

        return "Delete customer id - " + customerId;
    }

}
