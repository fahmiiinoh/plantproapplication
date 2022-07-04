package com.example.plantpro;

import com.example.plantpro.models.PlantUser;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOPlantUser {
    private DatabaseReference databaseReference;

    public DAOPlantUser() {

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(PlantUser.class.getSimpleName());

    }

    public Task<Void> add(PlantUser pltT) {

        return databaseReference.push().setValue(pltT);
    }
}