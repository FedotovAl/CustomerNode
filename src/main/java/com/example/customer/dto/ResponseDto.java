package com.example.customer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ResponseDto {
    private String email;
    private String token;
    private static List<ResponseDto> authCustomersList = new ArrayList<>();

    public ResponseDto(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public static void addToAuthCustomersList(ResponseDto responseDto){
        authCustomersList.add(responseDto);
    }

    public static ResponseDto findResponseDtoByToken(String token){
        for (ResponseDto a : authCustomersList){
            if (a.getToken().equals(token)){
                return a;
            }
        }
        return null;
    }
}
