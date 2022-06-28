package com.example.plantpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class MyPlant extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plant);

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
        recreate();
    }
    public void ClickPlantInformation(View view){
        HomeScreen.redirectActivity(this,PlantInformation.class);

    }
    public void ClickLogOut(View view){

        HomeScreen.logout(this);
    }

    protected void onPause(){
        super.onPause();
        HomeScreen.closeDrawer(drawerLayout);
    }
}