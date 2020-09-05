package com.example.supersubapplication.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.supersubapplication.networkcall.NetworkClient;
import com.example.supersubapplication.networkcall.ServiceAPI;
import com.example.supersubapplication.pojo.ApiData;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class HomeRepository {

    private ServiceAPI serviceAPI;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public HomeRepository(){
        serviceAPI = NetworkClient.getServiceApi();
    }


    public LiveData<ApiData> getApiData() {

        MutableLiveData<ApiData> getLiveData = new MutableLiveData<>();
        Disposable disposable = serviceAPI.getApiData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApiData>() {
                    @Override
                    public void onSuccess(ApiData apiData) {
                        getLiveData.postValue(apiData);
                    }
                    @Override
                    public void onError(Throwable e) {
                        getLiveData.postValue(null);
                    }
                });
        compositeDisposable.add(disposable);
        return getLiveData;
    }

    public void onReset(){
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
            compositeDisposable.dispose();
        }
    }
}
