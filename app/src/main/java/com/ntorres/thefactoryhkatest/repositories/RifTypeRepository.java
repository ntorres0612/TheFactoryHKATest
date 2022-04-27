package com.ntorres.thefactoryhkatest.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ntorres.thefactoryhkatest.apis.RifTypeService;
import com.ntorres.thefactoryhkatest.constant.Constant;
import com.ntorres.thefactoryhkatest.models.RifTypeResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class RifTypeRepository {

    private RifTypeService rifTypeService;
    private MutableLiveData<RifTypeResponse> rifTypeResponseLiveData;

    public RifTypeRepository() {
        rifTypeResponseLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        rifTypeService = new retrofit2.Retrofit.Builder()
                .baseUrl(Constant.Host)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RifTypeService.class);
    }
    public void listRifTypes() {
        rifTypeService.listRifType()
                .enqueue(new Callback<RifTypeResponse>() {
                    @Override
                    public void onResponse(Call<RifTypeResponse> call, Response<RifTypeResponse> response) {

                        if (response.body() != null) {
                            rifTypeResponseLiveData.postValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<RifTypeResponse> call, Throwable t) {
                        rifTypeResponseLiveData.postValue(null);
                    }
                });
    }

    public LiveData<RifTypeResponse> getRifTypeLiveData() {
        return rifTypeResponseLiveData;
    }
}