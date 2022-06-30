package com.example.plantpro;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Queue;

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
    public Task<Void> Update(String key, HashMap<String, Object> hashMap)
    {

        return databaseReference.child(key).updateChildren(hashMap);
    }
    public Task<Void> remove(String key){

        return databaseReference.child(key).removeValue();
    }
    public Query get(String key)
    {
        if (key==null)
        {
            return databaseReference.orderByKey().limitToFirst(8);

        }

        return databaseReference.orderByKey().startAfter(key).limitToFirst(8);
    }

}
