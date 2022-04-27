package com.ntorres.thefactoryhkatest.models;

import java.io.Serializable;
import java.util.ArrayList;

public class InvoiceRequest implements Serializable {

    private Customer customer;
    private Emitter emitter;
    private ArrayList<Product> products;

    public InvoiceRequest(Customer customer, Emitter emitter, ArrayList<Product> products) {
        this.customer = customer;
        this.emitter = emitter;
        this.products = products;
    }
}
