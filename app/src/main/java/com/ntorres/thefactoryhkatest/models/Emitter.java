package com.ntorres.thefactoryhkatest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Emitter implements Serializable {

    @SerializedName("document_number")
    @Expose
    private String documentNumber;
    @SerializedName("document_type")
    @Expose
    private int documentType;
    @SerializedName("business_name")
    @Expose
    private String businessName;

    public Emitter(String documentNumber, int documentType, String businessName) {
        this.documentNumber = documentNumber;
        this.documentType = documentType;
        this.businessName = businessName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public int getDocumentType() {
        return documentType;
    }

    public void setDocumentType(int documentType) {
        this.documentType = documentType;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
