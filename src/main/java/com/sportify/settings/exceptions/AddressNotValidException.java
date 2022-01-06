package com.sportify.settings.exceptions;

public class AddressNotValidException extends IllegalArgumentException{

    public AddressNotValidException(){
        super("Address geolocation didn't produce any result");
    }
}
