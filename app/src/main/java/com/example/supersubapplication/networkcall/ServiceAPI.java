package com.example.supersubapplication.networkcall;


import com.example.supersubapplication.pojo.ApiData;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ServiceAPI {

    @GET("0053d212-b891-4efe-a2db-a64770923dcf")
    Single<ApiData> getApiData();

}
