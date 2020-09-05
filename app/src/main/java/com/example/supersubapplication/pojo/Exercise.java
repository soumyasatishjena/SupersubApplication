package com.example.supersubapplication.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exercise {

    @SerializedName("restTime")
    @Expose
    private int restTime;
    @SerializedName("sets")
    @Expose
    private int sets;
    @SerializedName("reps")
    @Expose
    private int reps;
    @SerializedName("difficulty")
    @Expose
    private int difficulty;
    @SerializedName("calories")
    @Expose
    private int calories;
    @SerializedName("increments")
    @Expose
    private Increments increments;


    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Increments getIncrements() {
        return increments;
    }

    public void setIncrements(Increments increments) {
        this.increments = increments;
    }
}
