package com.example.plantpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;

import com.example.plantpro.models.Plant;
import com.example.plantpro.recyclerview.RVAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UpdateDeletePlant extends AppCompatActivity {

    DrawerLayout drawerLayout;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    RVAdapter adapter;
    DAOPlant daoPlant;
    boolean isLoading=false;
    String key=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_plant);


        swipeRefreshLayout = findViewById(R.id.swipe);
        recyclerView = findViewById(R.id.rv_admin);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new RVAdapter(this);
        recyclerView.setAdapter(adapter);
        daoPlant = new DAOPlant();
        loadData();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItem = linearLayoutManager.getItemCount();
                int lastVisble = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (totalItem<lastVisble+3)
                {
                    if(!isLoading)
                    {

                        isLoading = true;
                        loadData();
                    }

                }

            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
    }

    private void loadData()
    {
        swipeRefreshLayout.setRefreshing(true);

        daoPlant.get(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                ArrayList<Plant> plts = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren())
                {
                    Plant plt = data.getValue(Plant.class);
                   plt.setKey(data.getKey());
                    plts.add(plt);
                    key = data.getKey();
                }
                adapter.setItems(plts);
                adapter.notifyDataSetChanged();
                isLoading = false;
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
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