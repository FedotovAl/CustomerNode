package com.example.customer.repositories;

import com.example.customer.entities.Adress;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class AdressRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Adress> getAll(){
        return entityManager
                .createQuery("from Adress", Adress.class)
                .getResultList();
    }

    public Adress getByID(int id){
        return entityManager.find(Adress.class, id);
    }

    public Adress add(Adress adress){
        entityManager.persist(adress);
        return adress;
    }

    public Adress update(int id, Adress adress){
        Adress originalAdress = entityManager.find(Adress.class, id);
        if (originalAdress != null){
            originalAdress.setCountry(adress.getCountry());
            originalAdress.setState(adress.getState());
            originalAdress.setCity(adress.getCity());

            entityManager.merge(originalAdress);
        }
        return originalAdress;
    }

    public void remove(int id){
        Adress originalAdress = entityManager.find(Adress.class, id);
        if (originalAdress != null && originalAdress.getCustomerSetA().isEmpty()){
            entityManager.remove(originalAdress);
        }
    }

    public boolean isEmptyByCity(String paramName) throws NullPointerException{
        List<Adress> originalAdress = entityManager
                .createQuery("from Adress where city = '"
                                + paramName +
                                "'",
                        Adress.class
                )
                .getResultList();
        if (originalAdress.isEmpty()){
            return true;
        } else{
            return false;
        }
    }

    //?
    public Adress getByCity(String paramName){
        List<Adress> adressList = entityManager
                .createQuery("from Adress where city = '"
                                + paramName +
                                "'",
                        Adress.class
                ).getResultList();
        if (adressList.size() != 0){
            return adressList.get(0);
        } else {
            return null;
        }
    }
}
