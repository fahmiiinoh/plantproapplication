package com.example.plantpro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.plantpro.models.PlantUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PlantStatus extends AppCompatActivity {

    DrawerLayout drawerLayout;

    TextView plantname, plantcategory, planthumidity, plantstatus;
    Button btn_show;
    DatabaseReference databaseReference,databaseReference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_status);

        drawerLayout = findViewById(R.id.drawer_layout);

        plantname = (TextView) findViewById(R.id.tv_plantnameuser);
        plantcategory = (TextView) findViewById(R.id.tv_plantcategoryuser);
        planthumidity = (TextView) findViewById(R.id.tv_planthumadityuser);
        plantstatus = (TextView)  findViewById(R.id.tv_plantstatususer);
        btn_show = (Button) findViewById(R.id.btn_show);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("PlantUser");
        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("PlantSensor");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
             PlantUser plantUser =  snapshot.getValue(PlantUser.class);

           plantname.setText(plantUser.getPlantNameUser());
            plantcategory.setText(plantUser.getPlantCategoryUser());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference2.addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


            String planthumiditys = snapshot.child("Humidity").getValue().toString();
            planthumidity.setText(planthumiditys);
            if(Integer.parseInt(planthumiditys)>40){
                plantstatus.setText("Greater");
            }else if (Integer.parseInt(planthumiditys)<40 && Integer.parseInt(planthumiditys)>10){
                plantstatus.setText("Average ");
            }
            else if (Integer.parseInt(planthumiditys)<10){
                plantstatus.setText("Water your plant!");
            }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));





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