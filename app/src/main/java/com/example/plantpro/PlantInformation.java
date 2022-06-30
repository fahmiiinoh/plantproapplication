package com.example.plantpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlantInformation extends AppCompatActivity {

    DrawerLayout drawerLayout;

    RVAdapterUser rvAdapterUser;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_information);

        recyclerView = (RecyclerView)findViewById(R.id.rv_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<PlantInfo> options =
                new FirebaseRecyclerOptions.Builder<PlantInfo>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Plant"),PlantInfo.class)
                        .build();

        rvAdapterUser = new RVAdapterUser(options);
        recyclerView.setAdapter(rvAdapterUser);

        drawerLayout = findViewById(R.id.drawer_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        rvAdapterUser.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        rvAdapterUser.stopListening();
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
