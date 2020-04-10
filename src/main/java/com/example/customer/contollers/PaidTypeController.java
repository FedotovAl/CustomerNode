package com.example.customer.contollers;

import com.example.customer.entities.PaidType;
import com.example.customer.services.PaidTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/custserv", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaidTypeController {

    @Autowired
    private PaidTypeService paidTypeService;

    @GetMapping("/paidtypes")
    public List<PaidType> getAllCustomers(){
        return paidTypeService.getAllPaidTypes();
    }

    @GetMapping("/paidtypes/{paidtypesId}")
    public PaidType getCustomerByID(@PathVariable("paidtypesId") int id){
        return paidTypeService.getPaidTypeByID(id);
    }
    @PostMapping("/paidtypes")
    @ResponseStatus(HttpStatus.CREATED)
    public PaidType addPaidType(@RequestBody PaidType paidType){
        return paidTypeService.addNewPaidType(paidType);
    }

    @PutMapping("/paidtypes/{paidtypesId}")
    public PaidType updatePaidType(@PathVariable("paidtypesId") int id,
                                   @RequestBody PaidType paidType){
        return paidTypeService.updatePaidType(id, paidType);
    }

    @DeleteMapping("/paidtypes/{paidtypesId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePaidType(@PathVariable("paidtypesId") int id){
        paidTypeService.remove(id);
    }
}
