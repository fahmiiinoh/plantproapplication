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

import com.example.plantpro.models.PlantUser;

public class AddPlantUser extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant_user);

       final EditText et_plantname = findViewById(R.id.et_plantnameuser);
        Spinner spinner_category = findViewById(R.id.spinnerplantuser);

        Button btn_addplant = findViewById(R.id.btn_addtrackplant);
        DAOPlantUser daoU = new DAOPlantUser();

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddPlantUser.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.plantCategoryUser));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_category.setAdapter(myAdapter);
        btn_addplant.setOnClickListener(v->
        {
            PlantUser pltT = new PlantUser(et_plantname.getText().toString(), spinner_category.getSelectedItem().toString());
            daoU.add(pltT).addOnSuccessListener(suc->
            {

                Toast.makeText(this, "Plant is Added!", Toast.LENGTH_SHORT).show();
                et_plantname.getText().clear();
            });

        });



        //Assign Variable
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