package com.example.supersubapplication.networkcall;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.supersubapplication.utility.Utility.BASE_URL;


public class NetworkClient {
    private static Retrofit retrofit = null;
    private static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ServiceAPI getServiceApi(){
        return getRetrofitInstance().create(ServiceAPI.class);
    }
}
