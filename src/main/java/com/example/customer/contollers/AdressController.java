package com.example.customer.contollers;

import com.example.customer.entities.Adress;
import com.example.customer.services.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/custserv", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdressController {

    @Autowired
    private AdressService adressService;

    @GetMapping("/adresses")
    public List<Adress> getAllAdresses(){
        return adressService.getAllAdresses();
    }

    @GetMapping("/adresses/{adressId}")
    public Adress getAdressByID(@PathVariable("adressId") int id){
        return adressService.getAdressByID(id);
    }

    @PostMapping("/adresses")
    @ResponseStatus(HttpStatus.CREATED)
    public Adress addAdress(@RequestBody Adress adress){

        return adressService.addNewAdress(adress);
    }

    @PutMapping("/adresses/{adressId}")
    public Adress updateAdress(@PathVariable("adressId") int id,
                               @RequestBody Adress adress){
        return adressService.updateAdress(id, adress);
    }

    @DeleteMapping("/adresses/{adressId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdress(@PathVariable("adressId") int id){
        adressService.remove(id);
    }

}
