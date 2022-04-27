package com.ntorres.thefactoryhkatest.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ntorres.thefactoryhkatest.models.RifType;
import com.ntorres.thefactoryhkatest.models.RifTypeResponse;
import com.ntorres.thefactoryhkatest.repositories.RifTypeRepository;

public class RifTypeViewModel extends AndroidViewModel {
    private RifTypeRepository rifTypeRepository;
    private LiveData<RifTypeResponse> rifTypeResponseLiveData;

    public RifTypeViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        rifTypeRepository = new RifTypeRepository();
        rifTypeResponseLiveData = rifTypeRepository.getRifTypeLiveData();
    }

    public void listRifType() {
        rifTypeRepository.listRifTypes();
    }

    public LiveData<RifTypeResponse> getRifTypeLiveData() {
        return rifTypeResponseLiveData;
    }
}