package com.example.plantpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.plantpro.models.PlantInfo;
import com.example.plantpro.recyclerview.RVAdapterUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlantInformation extends AppCompatActivity {

    DrawerLayout drawerLayout;

    RVAdapterUser rvAdapterUser;
    RecyclerView recyclerView;
    SearchView searchView;
    private ArrayList<PlantInfo> plant_ModalArrayList;
    private ArrayList<PlantInfo> plant_ModalArrayList_cross;
    private DatabaseReference dbCategories;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_information);
        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);

        plant_ModalArrayList =new ArrayList<>();
        plant_ModalArrayList_cross =new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.rv_user);
        searchView = findViewById(R.id.searchview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        FirebaseRecyclerOptions<PlantInfo> options =
//                new FirebaseRecyclerOptions.Builder<PlantInfo>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Plant"), PlantInfo.class)
//                        .build();




        //courseModalArrayList=options.getSnapshots()

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filtessr(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()){
                    rvAdapterUser = new RVAdapterUser(plant_ModalArrayList_cross);
                    recyclerView.setAdapter(rvAdapterUser);
                }
                return false;
            }
        });


        rvAdapterUser = new RVAdapterUser(plant_ModalArrayList);
        recyclerView.setAdapter(rvAdapterUser);


        dbCategories = FirebaseDatabase.getInstance().getReference().child("Plant");
        dbCategories.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                      progressBar.setVisibility(View.GONE);
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        // String name = ds.getKey();
                        String plantCategory = ds.child("plantCategory").getValue(String.class);
                        String plantIntro = ds.child("plantIntro").getValue(String.class);
                        String plantName = ds.child("plantName").getValue(String.class);
                        String plantTip = ds.child("plantTip").getValue(String.class);
                        String plantImg = ds.child("plantImg").getValue(String.class);
//


                        Log.d("ksjskjs",plantCategory+plantIntro+plantName+plantTip+plantImg);
                        PlantInfo category = new PlantInfo(plantCategory, plantIntro, plantName,plantTip,plantImg);
                        plant_ModalArrayList.add(category);
                        plant_ModalArrayList_cross.add(category);

                        //  Add the categories inside the RecyclerView
                        rvAdapterUser.notifyDataSetChanged();   //  Reload RecyclerView
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View view) {

        HomeScreen.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view) {

        HomeScreen.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view) {

        HomeScreen.redirectActivity(this, HomeScreen.class);
    }

    public void ClickMyPlant(View view) {
        HomeScreen.redirectActivity(this, MyPlant.class);
    }

    public void ClickPlantInformation(View view) {
        recreate();


    }

    public void ClickLogOut(View view) {

        HomeScreen.logout(this);
    }

    protected void onPause() {
        super.onPause();
        HomeScreen.closeDrawer(drawerLayout);
    }

    private void filtessr(String text) {
        // creating a new array list to filter our data.
        ArrayList<PlantInfo> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (PlantInfo item : plant_ModalArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getPlantName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            rvAdapterUser.filterList(filteredlist);
        }
    }
}
