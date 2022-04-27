package com.ntorres.thefactoryhkatest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Invoice {

    @SerializedName("data")
    @Expose
    private String date;
    @SerializedName("emitter")
    @Expose
    private Emitter emitter;
    @SerializedName("customer")
    @Expose
    private Customer customer;

    @SerializedName("products")
    @Expose
    private ArrayList<Product> products;

    public Invoice(String date, Emitter emitter, Customer customer) {
        this.date = date;
        this.emitter = emitter;
        this.customer = customer;
        this.products = new ArrayList();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Emitter getEmitter() {
        return emitter;
    }

    public void setEmitter(Emitter emitter) {
        this.emitter = emitter;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
