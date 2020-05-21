package com.example.customer.security.jwt;

import com.example.customer.entities.Customer;

public final class JwtCustomerFactory {

    public JwtCustomerFactory() {
    }

    public static JwtCustomer create(Customer customer){
        return new JwtCustomer(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPassword(),
                customer.getPhoneNumber()
        );
    }
}
