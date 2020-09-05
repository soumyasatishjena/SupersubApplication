package com.example.supersubapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.supersubapplication.pojo.ApiData;
import com.example.supersubapplication.repository.HomeRepository;

public class HomeViewModel extends ViewModel {

    private HomeRepository homeRepository;

    public HomeViewModel() {
        this.homeRepository = new HomeRepository();
    }

    public LiveData<ApiData> getApiData(){
        return homeRepository.getApiData();
    }

    @Override
    protected void onCleared() {
        homeRepository.onReset();
        super.onCleared();
    }

    public void reset() {
        onCleared();
    }
}
