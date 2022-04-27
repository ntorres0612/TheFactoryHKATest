package com.ntorres.thefactoryhkatest.apis;

import com.ntorres.thefactoryhkatest.models.RifType;
import com.ntorres.thefactoryhkatest.models.RifTypeResponse;

import retrofit2.Call;
import retrofit2.http.POST;

public interface RifTypeService {
    @POST("api/list-rif-type")
    Call<RifTypeResponse> listRifType();
}