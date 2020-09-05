package com.example.supersubapplication.pojo;

import android.annotation.SuppressLint;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.supersubapplication.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiData {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("subcategory")
    @Expose
    private String subcategory;
    @SerializedName("illustrations")
    @Expose
    private List<Illustration> illustrations = null;
    @SerializedName("exercise")
    @Expose
    private Exercise exercise;
    @SerializedName("video")
    @Expose
    private VideoData video;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public List<Illustration> getIllustrations() {
        return illustrations;
    }

    public void setIllustrations(List<Illustration> illustrations) {
        this.illustrations = illustrations;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public VideoData getVideo() {
        return video;
    }

    public void setVideo(VideoData video) {
        this.video = video;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @BindingAdapter("displayImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions().circleCrop())
                .error(view.getContext().getResources()
                        .getDrawable(R.drawable.ic_error_image))
                .centerCrop()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(true)
                .into(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @BindingAdapter("displayVideoImage")
    public static void loadImageUrl(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions().circleCrop())
                .error(view.getContext().getResources()
                        .getDrawable(R.drawable.ic_error_image))
                .centerCrop()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(true)
                .into(view);
    }

    @Override
    public String toString() {
        return exercise.getSets() +"Sets  " + exercise.getReps()+"Reps";
    }
}
