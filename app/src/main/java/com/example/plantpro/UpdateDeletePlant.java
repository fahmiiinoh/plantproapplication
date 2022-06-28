package com.example.plantpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class UpdateDeletePlant extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_plant);

        drawerLayout = findViewById(R.id.drawer_layout);
    }
    public void ClickMenu(View view){

        adminpage.openDrawer(drawerLayout);
    }
    public void ClickLogo(View view){

        adminpage.closeDrawer(drawerLayout);
    }
    public void ClickHome(View view){

        adminpage.redirectActivity(this,adminpage.class);
    }
    public void ClickAdd(View view){
        adminpage.redirectActivity(this,AddPlant.class);
    }
    public void ClickUpdateDelete(View view){

        recreate();

    }
    public void ClickLogOut(View view){

        adminpage.logout(this);
    }

    protected void onPause(){
        super.onPause();
        adminpage.closeDrawer(drawerLayout);
    }
}