package com.example.plantpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddPlant extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        final EditText edit_plantname = findViewById(R.id.et_PlantName);
        final EditText edit_plantintro = findViewById(R.id.et_PlantIntro);
        final EditText edit_planttip = findViewById(R.id.et_PlantTip);
        Spinner plantCategory = findViewById(R.id.spinner_plantCategory);
        Button btn_addplant =  findViewById(R.id.btn_addPlant);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddPlant.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.plantCategory));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        plantCategory.setAdapter(myAdapter);

        DAOPlant dao = new DAOPlant();
        btn_addplant.setOnClickListener(v->{

            Plant plt = new Plant(plantCategory.getSelectedItem().toString(),edit_plantname.getText().toString(),edit_plantintro.getText().toString(),edit_planttip.getText().toString());
            dao.add(plt).addOnSuccessListener(suc->
            {
                Toast.makeText(this, "Plant Record Inserted!", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->{

                Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();
            });

        });

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
        recreate();
    }
    public void ClickUpdateDelete(View view){
        adminpage.redirectActivity(this,UpdateDeletePlant.class);

    }
    public void ClickLogOut(View view){

        adminpage.logout(this);
    }

    protected void onPause(){
        super.onPause();
        adminpage.closeDrawer(drawerLayout);
    }
}