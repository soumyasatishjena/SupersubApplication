package com.example.supersubapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.supersubapplication.R;
import com.example.supersubapplication.databinding.ActivityHomeBinding;
import com.example.supersubapplication.pojo.ApiData;
import com.example.supersubapplication.viewmodel.HomeViewModel;
import com.google.android.material.snackbar.Snackbar;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.example.supersubapplication.utility.Utility.isInternetAvailable;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityHomeBinding binding;
    private HomeViewModel homeViewModel;
    private ApiData apiData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        onClicks();
    }

    private void onClicks() {
        binding.backButton.setOnClickListener(this);
        binding.cardBookMark.setOnClickListener(this);
        binding.bookMark.setOnClickListener(this);
        binding.imagePlay.setOnClickListener(this);
        binding.cardEquipment.setOnClickListener(this);
        binding.textEquipment.setOnClickListener(this);
        binding.arrowGo.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        if(isInternetAvailable(this)) {
            binding.mainLayout.setVisibility(GONE);
            binding.progressBar.setVisibility(VISIBLE);
            getDataFromApi();
        }else {
            displaySnackBar(binding.mainLayout, "No Internet !!!");
        }
        super.onResume();
    }

    private void getDataFromApi() {
        homeViewModel.getApiData().observe(this, apiData -> {
            binding.progressBar.setVisibility(GONE);
            this.apiData = new ApiData();
            this.apiData = apiData;
            if(this.apiData!= null){
                binding.mainLayout.setVisibility(VISIBLE);
                binding.setApiData(this.apiData);
            }else {
                binding.mainLayout.setVisibility(GONE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backButton:
                onBackPressed();
                break;
            case R.id.cardBookMark:
            case R.id.bookMark:
                displaySnackBar(view, "BookMarked...");
                break;
            case R.id.imagePlay:
                if(this.apiData!= null){
                    startActivity(new Intent(this, VideoPlayActivity.class)
                            .putExtra("video",apiData.getVideo().getUrl()));
                }else
                    displaySnackBar(view,"Error !!, Cannot display the video...");
                break;
            case R.id.cardEquipment:
            case R.id.textEquipment:
            case R.id.arrowGo:
                displaySnackBar(view,"Equipment clicked...");
                break;

        }
    }

    private void displaySnackBar(View view, String text){
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }
}