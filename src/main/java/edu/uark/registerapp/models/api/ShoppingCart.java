package edu.uark.registerapp.models.api;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;


public class ShoppingCart extends ApiResponse {

    ArrayList<Product> ShoppingCart = new ArrayList<Product>();

    public ShoppingCart() {
        super();

        ShoppingCart.clear();
    }

    
}