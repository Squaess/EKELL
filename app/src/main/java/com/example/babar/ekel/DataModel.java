package com.example.babar.ekel;

/**
 * Created by silentnauscopy on 11/19/17
 */

public class DataModel {

    Boolean available;
    int mealId;
    String name;
    double price;
    int ImageID;

    public DataModel(Boolean available, int mealId, String name, double price){
        this.available = available;
        this.mealId = mealId;
        this.name = name;
        this.price = price;
    }

    public void setImageId(int id){
        this.ImageID = id;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public int getMealId(){
        return mealId;
    }

    public boolean isAvailable(){
        return available;
    }
}
