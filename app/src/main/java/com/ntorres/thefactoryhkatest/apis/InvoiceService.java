package com.ntorres.thefactoryhkatest.apis;

import com.ntorres.thefactoryhkatest.models.InvoiceRequest;
import com.ntorres.thefactoryhkatest.models.InvoiceResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InvoiceService {
    @POST("api/create-invoice")
    Call<InvoiceResponse> createInvoice(@Body InvoiceRequest invoice);

    @POST("api/list-invoices")
    Call<InvoiceResponse> listInvoices();

    @POST("api/delete-invoices")
    Call<InvoiceResponse> deleteInvoices();
}
