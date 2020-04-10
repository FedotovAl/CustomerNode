package com.example.customer.repositories;

import com.example.customer.entities.PaidType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class PaidTypeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<PaidType> getAll(){
        return entityManager
                .createQuery("from PaidType", PaidType.class)
                .getResultList();
    }

    public PaidType getByID(int id){
        return entityManager.find(PaidType.class, id);
    }

    public PaidType add(PaidType paidType){
        entityManager.persist(paidType);
        return paidType;
    }

    public PaidType update(int id, PaidType paidType){
        PaidType originalPaidType = entityManager.find(PaidType.class, id);
        if (originalPaidType != null){
            originalPaidType.setName(paidType.getName());
            entityManager.merge(originalPaidType);
        }
        return originalPaidType;
    }

    public void remove(int id){
        PaidType originalPaidType = entityManager.find(PaidType.class, id);
        if (originalPaidType != null && originalPaidType.getCustomerSetP().isEmpty()){
            entityManager.remove(originalPaidType);
        }
    }

    public boolean isEmptyByName(String paramName) throws NullPointerException{
        List<PaidType> originalPaidType = entityManager
                .createQuery("from PaidType where name = '"
                                + paramName +
                                "'",
                        PaidType.class
                )
                .getResultList();
        if (originalPaidType.isEmpty()){
            return true;
        } else{
            return false;
        }
    }

    //?
    public PaidType getByName(String paramName){
        List<PaidType> paidTypeList = entityManager
                .createQuery("from PaidType where name = '"
                                + paramName +
                                "'",
                        PaidType.class
                ).getResultList();
        if (paidTypeList.size() != 0){
            return paidTypeList.get(0);
        } else {
            return null;
        }
    }
}
