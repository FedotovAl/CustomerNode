package com.example.customer.contollers;

import com.example.customer.entities.PaidType;
import com.example.customer.services.PaidTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/custserv", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaidTypeController {

    @Autowired
    private PaidTypeService paidTypeService;

    @GetMapping("/paidtypes")
    public ResponseEntity<List<PaidType>> getAllCustomers(){
        return new ResponseEntity<>(paidTypeService.getAllPaidTypes(), HttpStatus.OK);
    }

    @GetMapping("/paidtypes/{paidtypesId}")
    public ResponseEntity<PaidType> getCustomerByID(@PathVariable("paidtypesId") int id){
        if (paidTypeService.getPaidTypeByID(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(paidTypeService.getPaidTypeByID(id), HttpStatus.OK);
    }

    @PostMapping("/paidtypes")
    public ResponseEntity<PaidType> addPaidType(@RequestBody PaidType paidType){
        return new ResponseEntity<>(paidTypeService.addNewPaidType(paidType), HttpStatus.CREATED);
    }

    @PutMapping("/paidtypes/{paidtypesId}")
    public ResponseEntity<PaidType> updatePaidType(@PathVariable("paidtypesId") int id,
                                   @RequestBody PaidType paidType){
        if (paidTypeService.getPaidTypeByID(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(paidTypeService.updatePaidType(id, paidType), HttpStatus.OK);
    }

    @DeleteMapping("/paidtypes/{paidtypesId}")
    public ResponseEntity<?> deletePaidType(@PathVariable("paidtypesId") int id){
        if (paidTypeService.getPaidTypeByID(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        paidTypeService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
