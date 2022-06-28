package com.example.plantpro;

public class Plant {

    private String PlantCategory;
    private String PlantName;
    private String PlantIntro;
    private String PlantTip;

    public Plant(){}

    public Plant(String plantCategory,String plantName, String plantIntro, String plantTip) {
        this.PlantCategory = plantCategory;
        this.PlantName = plantName;
        this.PlantIntro = plantIntro;
        this.PlantTip = plantTip;

    }
    public String getPlantCategory() {
        return PlantCategory;
    }

    public void setPlantCategory(String plantCategory) {
        PlantCategory = plantCategory;
    }
    public String getPlantName() {
        return PlantName;
    }

    public void setPlantName(String plantName) {
        PlantName = plantName;
    }

    public String getPlantIntro() {
        return PlantIntro;
    }

    public void setPlantIntro(String plantIntro) {
        PlantIntro = plantIntro;
    }


    public String getPlantTip() {
        return PlantTip;
    }

    public void setPlantTip(String plantTip) {
        PlantTip = plantTip;
    }
}
