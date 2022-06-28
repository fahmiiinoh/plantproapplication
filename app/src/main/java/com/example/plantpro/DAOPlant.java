package com.example.plantpro;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DAOPlant {
    private DatabaseReference databaseReference;
    public DAOPlant(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Plant.class.getSimpleName());

    }
    public Task<Void> add(Plant plt)
    {

        return databaseReference.push().setValue(plt);
    }
    public Task<Void> remove(String key){

        return databaseReference.child(key).removeValue();
    }

}
