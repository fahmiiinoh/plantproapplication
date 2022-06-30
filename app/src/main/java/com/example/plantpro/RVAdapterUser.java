package com.example.plantpro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class RVAdapterUser extends FirebaseRecyclerAdapter<PlantInfo,RVAdapterUser.myViewHolder>
{

    public RVAdapterUser(@NonNull FirebaseRecyclerOptions<PlantInfo> options)
    {

    super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull PlantInfo model)
    {
        holder.plantname.setText(model.getPlantName());
        holder.plantcategory.setText(model.getPlantCategory());
        Glide.with(holder.img.getContext())
                .load(model.getPlantImg())
                .placeholder(R.drawable.ic_flower)
                .circleCrop()
                .error(R.drawable.ic_add)
                .into(holder.img);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_plantinfo,parent,false);

        return new myViewHolder(view);
    }



    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView plantname,plantcategory;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.imgplant);
            plantname = (TextView) itemView.findViewById(R.id.tv_plantnameinfo);
            plantcategory = (TextView)  itemView.findViewById(R.id.tv_plantcategoryinfo);

        }
    }
}
