package com.example.plantpro.recyclerview;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.plantpro.R;
import com.example.plantpro.models.PlantInfo;
import com.example.plantpro.plant_information_details;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RVAdapterUser extends RecyclerView.Adapter<RVAdapterUser.myViewHolder> {
    private ArrayList<PlantInfo> courseModalArrayList;

    public RVAdapterUser(@NonNull ArrayList<PlantInfo> arrayList) {
        this.courseModalArrayList = arrayList;
        // super(arrayList);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.plantname.setText(courseModalArrayList.get(position).getPlantName());
        holder.plantcategory.setText(courseModalArrayList.get(position).getPlantCategory());
        Glide.with(holder.img.getContext())
                .load(courseModalArrayList.get(position).getPlantImg())
                .placeholder(R.drawable.ic_flower)
                .circleCrop()
                .error(R.drawable.ic_add)
                .into(holder.img);
        holder.parent_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), plant_information_details.class);
                intent.putExtra("plantCategory", courseModalArrayList.get(position).getPlantCategory());
                intent.putExtra("plantimg", courseModalArrayList.get(position).getPlantImg());
                intent.putExtra("plantname", courseModalArrayList.get(position).getPlantName());
                intent.putExtra("plantintro", courseModalArrayList.get(position).getPlantIntro());
                intent.putExtra("planttip", courseModalArrayList.get(position).getPlantTip());

                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return courseModalArrayList.size();
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_plantinfo, parent, false);

        return new myViewHolder(view);
    }


    class myViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img;
        TextView plantname, plantcategory;
        RelativeLayout parent_click;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.imgplant);
            plantname = (TextView) itemView.findViewById(R.id.tv_plantnameinfo);
            plantcategory = (TextView) itemView.findViewById(R.id.tv_plantcategoryinfo);
            parent_click = (RelativeLayout) itemView.findViewById(R.id.parent_click);

        }
    }


    // method for filtering our recyclerview items.
    public void filterList(ArrayList<PlantInfo> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        courseModalArrayList = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        Log.d("siurfiuwff", "RVAdapterUser: " + courseModalArrayList);


        notifyDataSetChanged();
    }
}
