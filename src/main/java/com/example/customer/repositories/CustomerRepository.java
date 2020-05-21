package com.example.customer.repositories;

import com.example.customer.entities.Customer;
import com.example.customer.entities.PaidType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Customer> getAll(){
        return entityManager
                .createQuery("from Customer", Customer.class)
                .getResultList();
    }

    public Customer getByID(int id){
        return entityManager.find(Customer.class, id);
    }

    public Customer add(Customer customer){
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        entityManager.persist(customer);
        return customer;
    }

    public Customer update(int id, Customer customer){
        Customer originalCustomer = entityManager.find(Customer.class, id);
        if (originalCustomer != null){
            originalCustomer.setFirstName(customer.getFirstName());
            originalCustomer.setLastName(customer.getLastName());
            originalCustomer.setEmail(customer.getEmail());
            originalCustomer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
            originalCustomer.setPhoneNumber(customer.getPhoneNumber());
            originalCustomer.setAdress(customer.getAdress());
            originalCustomer.setPaidTypeSet(customer.getPaidTypeSet());

            entityManager.merge(originalCustomer);
        }
        return originalCustomer;
    }

    public void remove(int id){
        Customer originalCustomer = entityManager.find(Customer.class, id);
        if (originalCustomer != null){
            entityManager.remove(originalCustomer);
            for (PaidType p : originalCustomer.getPaidTypeSet()){
                p.getCustomerSetP().remove(originalCustomer);
            }
        }
    }

    //security
    public Customer getByEmail(String paramName){
        List<Customer> customerList = entityManager
                .createQuery("from Customer where email = '"
                                + paramName +
                                "'",
                        Customer.class
                ).getResultList();
        if (customerList.size() != 0){
            return customerList.get(0);
        } else {
            return null;
        }
    }
}
