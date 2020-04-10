package com.example.customer.services;

import com.example.customer.entities.PaidType;
import com.example.customer.repositories.PaidTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaidTypeService {
    @Autowired
    private PaidTypeRepository paidTypeRepository;

    public List<PaidType> getAllPaidTypes(){
        return paidTypeRepository.getAll();
    }

    public PaidType getPaidTypeByID(int id){
        return paidTypeRepository.getByID(id);
    }

    public PaidType addNewPaidType(PaidType paidType){
        if (paidTypeRepository.isEmptyByName(paidType.getName())) {
            return paidTypeRepository.add(paidType);
        } else {
            return null;
        }
    }

    public PaidType updatePaidType(int id, PaidType paidType){
        if (paidTypeRepository.isEmptyByName(paidType.getName())) {
            return paidTypeRepository.update(id, paidType);
        } else {
            return null;
        }
    }

    public void remove(int id){
        paidTypeRepository.remove(id);
    }
}
