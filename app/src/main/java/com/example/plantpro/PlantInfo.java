package com.example.plantpro;

public class PlantInfo {

    String plantCategory, plantIntro, plantName, plantTip, plantImg;

    PlantInfo()
    {

    }
    public PlantInfo(String plantCategory, String plantIntro, String plantName, String plantTip, String plantImg) {
        this.plantCategory = plantCategory;
        this.plantIntro = plantIntro;
        this.plantName = plantName;
        this.plantTip = plantTip;
        this.plantImg = plantImg;
    }

    public String getPlantCategory() {
        return plantCategory;
    }

    public void setPlantCategory(String plantCategory) {
        this.plantCategory = plantCategory;
    }

    public String getPlantIntro() {
        return plantIntro;
    }

    public void setPlantIntro(String plantIntro) {
        this.plantIntro = plantIntro;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantTip() {
        return plantTip;
    }

    public void setPlantTip(String plantTip) {
        this.plantTip = plantTip;
    }
    public String getPlantImg() {
        return plantImg;
    }

    public void setPlantImg(String plantImg) {
        this.plantImg = plantImg;
    }
}
