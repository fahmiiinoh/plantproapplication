package com.example.plantpro;

public class PlantUser
{
    public PlantUser(){};

    private String plantNameUser, plantCategoryUser;

    public PlantUser(String plantNameUser, String plantCategoryUser) {
        this.plantNameUser = plantNameUser;
        this.plantCategoryUser = plantCategoryUser;
    }

    public String getPlantNameUser() {
        return plantNameUser;
    }

    public void setPlantNameUser(String plantNameUser) {
        this.plantNameUser = plantNameUser;
    }

    public String getPlantCategoryUser() {
        return plantCategoryUser;
    }

    public void setPlantCategoryUser(String plantCategoryUser) {
        this.plantCategoryUser = plantCategoryUser;
    }
}
