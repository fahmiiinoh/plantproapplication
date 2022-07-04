package com.example.plantpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class plant_information_details extends AppCompatActivity {

    TextView tv_plantname,tv_plant_cat,tv_plant_intro,tv_plant_tips;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_information_details);

        Bundle extras = getIntent().getExtras();
        String plantCategory= ""
                ,plantimg = "",plantname = "",
                plantintro= "",
                planttip= "";

        if (extras != null) {
            plantCategory = extras.getString("plantCategory");
            plantimg = extras.getString("plantimg");
            plantname = extras.getString("plantname");
            plantintro = extras.getString("plantintro");
            planttip = extras.getString("planttip");
            // and get whatever type user account id is
         //   Toast.makeText(this, plantCategory, Toast.LENGTH_SHORT).show();

        }

        tv_plantname=findViewById(R.id.plant_name);
        tv_plant_cat=findViewById(R.id.plant_cat);
        tv_plant_intro=findViewById(R.id.plant_intro);
        tv_plant_tips=findViewById(R.id.plant_tips);
        img=findViewById(R.id.image);


        tv_plantname.setText(plantname);
        tv_plant_intro.setText(plantintro);
        tv_plant_cat.setText(plantCategory);
        tv_plant_tips.setText(planttip);

        Glide.with(this)
                .load(plantimg)
                .placeholder(R.drawable.ic_flower)
                .circleCrop()
                .error(R.drawable.ic_add)
                .into(img);
    }
}