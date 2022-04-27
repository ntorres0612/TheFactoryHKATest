package com.ntorres.thefactoryhkatest.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ntorres.thefactoryhkatest.apis.InvoiceService;
import com.ntorres.thefactoryhkatest.apis.RifTypeService;
import com.ntorres.thefactoryhkatest.constant.Constant;
import com.ntorres.thefactoryhkatest.models.InvoiceRequest;
import com.ntorres.thefactoryhkatest.models.InvoiceResponse;
import com.ntorres.thefactoryhkatest.models.RifTypeResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class InvoiceRepository {

    private InvoiceService invoiceService;
    private MutableLiveData<InvoiceResponse> invoiceResponseMutableLiveData;
    private MutableLiveData<InvoiceResponse> listInvoiceResponseMutableLiveData;
    private MutableLiveData<InvoiceResponse> deleteInvoicesResponseMutableLiveData;

    public InvoiceRepository() {
        invoiceResponseMutableLiveData = new MutableLiveData<>();
        listInvoiceResponseMutableLiveData = new MutableLiveData<>();
        deleteInvoicesResponseMutableLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        invoiceService = new retrofit2.Retrofit.Builder()
                .baseUrl(Constant.Host)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(InvoiceService.class);
    }
    public void createInvoice(InvoiceRequest invoiceRequest) {
        invoiceService.createInvoice(invoiceRequest)
            .enqueue(new Callback<InvoiceResponse>() {
                @Override
                public void onResponse(Call<InvoiceResponse> call, Response<InvoiceResponse> response) {

                    if (response.body() != null) {
                        invoiceResponseMutableLiveData.postValue(response.body());
                    }
                }
                @Override
                public void onFailure(Call<InvoiceResponse> call, Throwable t) {
                    invoiceResponseMutableLiveData.postValue(null);
                }
            });
    }

    public void listInvoices() {
        invoiceService.listInvoices()
                .enqueue(new Callback<InvoiceResponse>() {
                    @Override
                    public void onResponse(Call<InvoiceResponse> call, Response<InvoiceResponse> response) {
                        if (response.body() != null) {
                            listInvoiceResponseMutableLiveData.postValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<InvoiceResponse> call, Throwable t) {
                        listInvoiceResponseMutableLiveData.postValue(null);
                    }
                });
    }

    public void deleteInvoices() {
        invoiceService.deleteInvoices()
                .enqueue(new Callback<InvoiceResponse>() {
                    @Override
                    public void onResponse(Call<InvoiceResponse> call, Response<InvoiceResponse> response) {
                        if (response.body() != null) {
                            deleteInvoicesResponseMutableLiveData.postValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<InvoiceResponse> call, Throwable t) {
                        deleteInvoicesResponseMutableLiveData.postValue(null);
                    }
                });
    }

    public LiveData<InvoiceResponse> getInvoiceLiveData() {
        return invoiceResponseMutableLiveData;
    }
    public LiveData<InvoiceResponse> getListInvoiceLiveData() {
        return listInvoiceResponseMutableLiveData;
    }
    public LiveData<InvoiceResponse> getDeleteInvoiceLiveData() {
        return deleteInvoicesResponseMutableLiveData;
    }
}