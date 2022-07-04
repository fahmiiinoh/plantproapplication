package com.example.plantpro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class HomeScreen extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //Assign Variable
        drawerLayout = findViewById(R.id.drawer_layout);
        Button btn_plantstatus = findViewById(R.id.btn_plantstatus);

        btn_plantstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this,PlantStatus.class);
                startActivity(intent);
            }
        });

    }
    public void ClickMenu(View view){

        openDrawer(drawerLayout);

    }

    public static void openDrawer(@NonNull DrawerLayout drawerLayout) {

        drawerLayout.openDrawer(GravityCompat.START);


    }
    public void ClickLogo(View view){

        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(@NonNull DrawerLayout drawerLayout){

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){


            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view){

        recreate();
    }
    public void ClickMyPlant(View view){

        redirectActivity(this,MyPlant.class);
    }
    public void ClickPlantInformation(View view){

        redirectActivity(this,PlantInformation.class);
    }
    public void ClickLogOut(View view){

        logout(this);
    }

    public static void logout(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which){
                activity.finishAffinity();
                System.exit(0);
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });
        builder.show();
    }


    public static void redirectActivity(Activity activity, Class aClass){

        Intent intent = new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }


    protected void onPause(){
        super.onPause();
        HomeScreen.closeDrawer(drawerLayout);
    }
}