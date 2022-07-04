package com.example.plantpro.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantpro.AddPlant;
import com.example.plantpro.DAOPlant;
import com.example.plantpro.R;
import com.example.plantpro.models.Plant;
import com.example.plantpro.recyclerview.PlantVH;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    ArrayList<Plant> list = new ArrayList<>();
    public RVAdapter(Context ctx){

        this.context = ctx;
    }
    public void  setItems(ArrayList<Plant> plt)
    {
    list.addAll(plt);

    }

    @NonNull
    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(context).inflate(R.layout.layout_plant,parent,false);
        return new PlantVH(view);
    }
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int category)
    {
        PlantVH vh = (PlantVH) holder;
        Plant plt = list.get(category);
        vh.tv_PlantName.setText(plt.getPlantName());
        vh.tv_PlantCategory.setText(plt.getPlantCategory());
        vh.tv_option.setOnClickListener(v->
        {
            PopupMenu popupMenu = new PopupMenu(context,vh.tv_option);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(item->
            {
                switch (item.getItemId())
                {

                    case R.id.menu_edit:
                        Intent intent = new Intent(context, AddPlant.class);
                        intent.putExtra("EDIT",plt);
                        context.startActivity(intent);
                        break;

                    case R.id.menu_remove:

                        DAOPlant dao = new DAOPlant();
                        dao.remove(plt.getKey()).addOnSuccessListener(suc->
                        {

                            Toast.makeText(context, "RECORD IS REMOVE", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(category);
                        }).addOnFailureListener(er->
                        {
                           Toast.makeText(context,""+er.getMessage(), Toast.LENGTH_SHORT).show();

                        });

                        break;
                }
            return false;


            });
            popupMenu.show();

        });

    }

    @Override
    public int getItemCount()
    {

        return list.size();
    }
}
