package com.example.plantpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class AddPlant extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        drawerLayout = findViewById(R.id.drawer_layout);
        final EditText edit_plantname = findViewById(R.id.et_PlantName);
        final EditText edit_plantintro = findViewById(R.id.et_PlantIntro);
        final EditText edit_planttip = findViewById(R.id.et_PlantTip);
        Spinner plantCategory = findViewById(R.id.spinner_plantCategory);
        Button btn_addplant =  findViewById(R.id.btn_addPlant);
        Button btn_open = findViewById(R.id.btn_openPlant);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddPlant.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.plantCategory));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        plantCategory.setAdapter(myAdapter);

        btn_open.setOnClickListener(v->{

            Intent intent = new Intent(AddPlant.this, UpdateDeletePlant.class);
            startActivity(intent);
        });
        DAOPlant dao = new DAOPlant();

        Plant plt_edit = (Plant) getIntent().getSerializableExtra("EDIT");
        if(plt_edit !=null)
        {
            btn_addplant.setText("UPDATE");
            edit_plantname.setText(plt_edit.getPlantName());
            edit_plantintro.setText(plt_edit.getPlantIntro());
            edit_planttip.setText(plt_edit.getPlantTip());
            //plantCategory.setOnItemSelectedListener(plt_edit.getPlantCategory());
            btn_open.setVisibility(View.GONE);

        }else {

            btn_addplant.setText("SUBMIT");
            btn_open.setVisibility(View.VISIBLE);
        }

        btn_addplant.setOnClickListener(v-> {

            Plant plt = new Plant(plantCategory.getSelectedItem().toString(), edit_plantname.getText().toString(), edit_plantintro.getText().toString(), edit_planttip.getText().toString());
            if (plt_edit == null)
                dao.add(plt).addOnSuccessListener(suc ->
                {
                    Toast.makeText(this, "Plant Record Inserted!", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er -> {

                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
        else
        {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("plantName", edit_plantname.getText().toString());
            hashMap.put("plantIntro", edit_plantintro.getText().toString());
            hashMap.put("plantTip", edit_planttip.getText().toString());
            hashMap.put("plantCategory", plantCategory.getSelectedItem().toString());
            dao.Update(plt_edit.getKey(), hashMap).addOnSuccessListener(suc ->
            {
                Toast.makeText(this, "Record is updated", Toast.LENGTH_SHORT).show();
                finish();
            }).addOnFailureListener(er ->
            {

                Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            });
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