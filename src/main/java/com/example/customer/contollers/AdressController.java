package com.example.customer.contollers;

import com.example.customer.entities.Adress;
import com.example.customer.services.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/custserv", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdressController {

    @Autowired
    private AdressService adressService;

    @GetMapping("/adresses")
    public ResponseEntity<List<Adress>> getAllAdresses() {
        return new ResponseEntity<>(adressService.getAllAdresses(), HttpStatus.OK);
    }

    @GetMapping("/adresses/{adressId}")
    public ResponseEntity<Adress> getAdressByID(@PathVariable("adressId") int id) {
        if (adressService.getAdressByID(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(adressService.getAdressByID(id), HttpStatus.OK);
    }

    @PostMapping("/adresses")
    public ResponseEntity<Adress> addAdress(@RequestBody Adress adress) {
        return new ResponseEntity<>(adressService.addNewAdress(adress), HttpStatus.CREATED);
    }

    @PutMapping("/adresses/{adressId}")
    public ResponseEntity<Adress> updateAdress(@PathVariable("adressId") int id,
                               @RequestBody Adress adress) {
        if (adressService.getAdressByID(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(adressService.updateAdress(id, adress), HttpStatus.OK);
    }

    @DeleteMapping("/adresses/{adressId}")
    public ResponseEntity<?> deleteAdress(@PathVariable("adressId") int id) {
        if (adressService.getAdressByID(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        adressService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
