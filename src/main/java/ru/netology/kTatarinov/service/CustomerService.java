package ru.netology.kTatarinov.service;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.netology.kTatarinov.domain.Customer;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final List<Customer> storage = new ArrayList<>();

    public List<Customer> getCustomers() {
        return storage;
    }

    public Customer getCustomer(int index) {
        return storage.get(index);
    }

    public void addCustomer(Customer customer) {
        storage.add(customer);
    }

    @PostConstruct
    public void initStorage() {
        addCustomer(new Customer(1, "Spring", "password"));
        addCustomer(new Customer(2, "Boot", "password"));
    }
}