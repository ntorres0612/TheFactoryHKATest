package com.ntorres.thefactoryhkatest.models;

import android.text.Editable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Customer implements Serializable {

    public Customer(String document_number, String name, String lastname) {
     this.document_number = document_number;
     this.name = name;
     this.lastname = lastname;
    }
    @SerializedName("document_number")
    @Expose
    private String document_number;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("lastname")
    @Expose
    private String lastname;

    public String getDocument_number() {
        return document_number;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return name.concat(lastname);
    }

    public String getLastname() {
            return lastname;
        }
}
