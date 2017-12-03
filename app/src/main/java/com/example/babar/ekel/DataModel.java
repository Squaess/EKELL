package com.example.babar.ekel;

/**
 * Created by silentnauscopy on 11/19/17
 */

public class DataModel {

    private Boolean available;
    private int mealId;
    private String name;
    private double price;
    private int ImageID;

    DataModel(Boolean available, int mealId, String name, double price){
        this.available = available;
        this.mealId = mealId;
        this.name = name;
        this.price = price;
    }

    void setImageId(int id){
        this.ImageID = id;
    }

    public String getName(){
        return name;
    }

    double getPrice(){
        return price;
    }

    int getMealId(){
        return mealId;
    }

    boolean isAvailable(){
        return available;
    }

    int getImageID(){return ImageID;}
}
