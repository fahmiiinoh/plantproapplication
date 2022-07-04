package com.example.plantpro.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantpro.R;

public class PlantVH extends RecyclerView.ViewHolder {

    public TextView tv_PlantName, tv_PlantCategory, tv_option;
    public PlantVH(@NonNull View itemView) {
        super(itemView);
        tv_PlantName = itemView.findViewById(R.id.tv_PlantName);
        tv_PlantCategory = itemView.findViewById(R.id.tv_PlantCategory);
        tv_option = itemView.findViewById(R.id.tv_option);
    }
}
