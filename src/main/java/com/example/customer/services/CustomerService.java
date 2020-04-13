package com.example.customer.services;

import com.example.customer.entities.Adress;
import com.example.customer.entities.Customer;
import com.example.customer.entities.PaidType;
import com.example.customer.repositories.AdressRepository;
import com.example.customer.repositories.CustomerRepository;
import com.example.customer.repositories.PaidTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdressRepository adressRepository;

    @Autowired
    private PaidTypeRepository paidTypeRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.getAll();
    }

    public Customer getCustomerByID(int id){
        return customerRepository.getByID(id);
    }

    public Customer addNewCustomer(Customer customer){
        Adress adress = adressRepository.getByCity(customer.getAdress().getCity());
        if (adress != null){
            customer.setAdress(adress);
        }
        Set<PaidType> ptSet = new HashSet<>();
        for (PaidType p : customer.getPaidTypeSet()){
            PaidType paidType = paidTypeRepository.getByName(p.getName());
            if (paidType != null){
                ptSet.add(paidType);
            }
        }
        customer.setPaidTypeSet(ptSet);
        return customerRepository.add(customer);
    }

    public Customer updateCustomer(int id, Customer customer){
        Adress adress = adressRepository.getByCity(customer.getAdress().getCity());
        Customer originalCustomer = customerRepository.getByID(id);
        originalCustomer.getAdress().getCustomerSetA().remove(originalCustomer);
        adressRepository.remove(originalCustomer.getAdress().getId());
        originalCustomer.setAdress(null);
        if (adress != null) {
            customer.setAdress(adress);
        }

        Set<PaidType> ptSet = new HashSet<>();
        for (PaidType p : customer.getPaidTypeSet()){
            PaidType paidType = paidTypeRepository.getByName(p.getName());
            if (paidType != null){
                ptSet.add(paidType);
            }
        }
        customer.setPaidTypeSet(ptSet);
        return customerRepository.update(id, customer);
    }

    public void remove(int id){
        Customer customer = customerRepository.getByID(id);
        customerRepository.remove(id);
        adressRepository.remove(customer.getAdress().getId());
    }
}
