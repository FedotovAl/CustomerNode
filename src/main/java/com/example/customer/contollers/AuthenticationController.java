package com.example.customer.contollers;

import com.example.customer.dto.AuthenticationRequestDto;
import com.example.customer.dto.ResponseDto;
import com.example.customer.entities.Customer;
import com.example.customer.security.jwt.JwtTokenProvider;
import com.example.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/custserv/customers/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomerService customerService;

    //получение токена
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody AuthenticationRequestDto requestDto) {
        String email = requestDto.getEmail();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                email, requestDto.getPassword()
        ));
        Customer customer = customerService.getCustomerByEmail(email);
        if (customer == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        String token = jwtTokenProvider.createToken(email);

        ResponseDto response = new ResponseDto(email, token);
        ResponseDto.addToAuthCustomersList(response);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //получение customer по токену
    @GetMapping("token")
    public ResponseEntity<Customer> getId(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer_", "");
        ResponseDto response = ResponseDto.findResponseDtoByToken(token);
        if (response == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Customer customer = customerService.getCustomerByEmail(response.getEmail());
        return new ResponseEntity(customer, HttpStatus.OK);
    }
}
