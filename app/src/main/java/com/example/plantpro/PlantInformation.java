package com.example.plantpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class PlantInformation extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_information);

        drawerLayout = findViewById(R.id.drawer_layout);
    }
    public void ClickMenu(View view){

        HomeScreen.openDrawer(drawerLayout);
    }
    public void ClickLogo(View view){

        HomeScreen.closeDrawer(drawerLayout);
    }
    public void ClickHome(View view){

        HomeScreen.redirectActivity(this,HomeScreen.class);
    }
    public void ClickMyPlant(View view){
        HomeScreen.redirectActivity(this,MyPlant.class);
    }
    public void ClickPlantInformation(View view){
        recreate();


    }
    public void ClickLogOut(View view){

        HomeScreen.logout(this);
    }

    protected void onPause(){
        super.onPause();
        HomeScreen.closeDrawer(drawerLayout);
    }
}
