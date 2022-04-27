package com.ntorres.thefactoryhkatest.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ntorres.thefactoryhkatest.models.InvoiceRequest;
import com.ntorres.thefactoryhkatest.models.InvoiceResponse;
import com.ntorres.thefactoryhkatest.models.RifTypeResponse;
import com.ntorres.thefactoryhkatest.repositories.InvoiceRepository;
import com.ntorres.thefactoryhkatest.repositories.RifTypeRepository;

public class InvoiceViewModel extends AndroidViewModel {
    private InvoiceRepository invoiceRepository;
    private LiveData<InvoiceResponse> invoiceResponseLiveData;
    private LiveData<InvoiceResponse> listInvoiceResponseLiveData;
    private LiveData<InvoiceResponse> deleteInvoiceResponseLiveData;

    public InvoiceViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        invoiceRepository = new InvoiceRepository();
        invoiceResponseLiveData = invoiceRepository.getInvoiceLiveData();
        listInvoiceResponseLiveData = invoiceRepository.getListInvoiceLiveData();
        deleteInvoiceResponseLiveData = invoiceRepository.getDeleteInvoiceLiveData();
    }

    public void createInvoice(InvoiceRequest invoiceRequest) {
        invoiceRepository.createInvoice(invoiceRequest);
    }
    public void listInvoice() {
        invoiceRepository.listInvoices();
    }
    public void deleteInvoices() {
        invoiceRepository.deleteInvoices();
    }

    public LiveData<InvoiceResponse> getInvoiceLiveData() {
        return invoiceResponseLiveData;
    }
    public LiveData<InvoiceResponse> getListInvoiceLiveData() {
        return listInvoiceResponseLiveData;
    }
    public LiveData<InvoiceResponse> getDeleteInvoiceLiveData() {
        return deleteInvoiceResponseLiveData;
    }
}