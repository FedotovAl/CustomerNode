package com.example.customer.repositories;

import com.example.customer.entities.Customer;
import com.example.customer.entities.PaidType;
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

    public List<Customer> getAll(){
        return entityManager
                .createQuery("from Customer", Customer.class)
                .getResultList();
    }

    public Customer getByID(int id){
        return entityManager.find(Customer.class, id);
    }


    //TODO
    public Customer add(Customer customer){
        entityManager.persist(customer);
        return customer;
    }

    //TODO
    public Customer update(int id, Customer customer){
        Customer originalCustomer = entityManager.find(Customer.class, id);
        if (originalCustomer != null){
            originalCustomer.setFirstName(customer.getFirstName());
            originalCustomer.setLastName(customer.getLastName());
            originalCustomer.setEmail(customer.getEmail());
            originalCustomer.setPassword(customer.getPassword());
            originalCustomer.setPhoneNumber(customer.getPhoneNumber());
//            originalCustomer.setAdress(customer.getAdress());
//            originalCustomer.setPaidTypeSet(customer.getPaidTypeSet());

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
}
