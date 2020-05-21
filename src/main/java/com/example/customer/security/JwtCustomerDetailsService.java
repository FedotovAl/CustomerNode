package com.example.customer.security;

import com.example.customer.entities.Customer;
import com.example.customer.repositories.CustomerRepository;
import com.example.customer.security.jwt.JwtCustomer;
import com.example.customer.security.jwt.JwtCustomerFactory;
import com.example.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtCustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Customer customer = customerRepository.getByEmail(s);
        if (customer == null){
             throw new UsernameNotFoundException("User with email(username): " + s + " not found!");
        }

        JwtCustomer jwtCustomer = JwtCustomerFactory.create(customer);

        return jwtCustomer;
    }
}
