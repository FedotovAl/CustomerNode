package com.example.customer.services;

import com.example.customer.entities.Adress;
import com.example.customer.repositories.AdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdressService {

    @Autowired
    private AdressRepository adressRepository;

    public List<Adress> getAllAdresses(){
        return adressRepository.getAll();
    }

    public Adress getAdressByID(int id){
        return adressRepository.getByID(id);
    }

    public Adress addNewAdress(Adress adress){
        if (adressRepository.isEmptyByCity(adress.getCity())) {
            return adressRepository.add(adress);
        } else {
            return null;
        }
    }

    //Уникальный по City
    public Adress updateAdress(int id, Adress adress){
        if (adressRepository.isEmptyByCity(adress.getCity())) {
            return adressRepository.update(id, adress);
        } else {
            return null;
        }
    }

    public void remove(int id){
        adressRepository.remove(id);
    }

}
