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

import java.util.List;

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

    //TODO Работает для Adress и одного PaidType
    public Customer addNewCustomer(Customer customer){
        Adress adress = adressRepository.getByCity(customer.getAdress().getCity());
        if (adress != null){
            customer.setAdress(adress);
        }
        for (PaidType p : customer.getPaidTypeSet()){
            PaidType paidType = paidTypeRepository.getByName(p.getName());
            if (paidType != null){
                customer.getPaidTypeSet().remove(p);
                customer.getPaidTypeSet().add(paidType);
            }
        }
        return customerRepository.add(customer);
    }

    //TODO Работает для Adress и одного PaidType
    public Customer updateCustomer(int id, Customer customer){
        Adress adress = adressRepository.getByCity(customer.getAdress().getCity());
        if (adress != null){
            customer.setAdress(adress);
        }
        for (PaidType p : customer.getPaidTypeSet()){
            PaidType paidType = paidTypeRepository.getByName(p.getName());
            if (paidType != null){
                customer.getPaidTypeSet().remove(p);
                customer.getPaidTypeSet().add(paidType);
            }
        }
        return customerRepository.update(id, customer);
    }

    public void remove(int id){
        customerRepository.remove(id);
    }
}
