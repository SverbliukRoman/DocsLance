package ua.com.mexanik.docslance.fragments.fragmentRecyclerViewDoctors.recyclerview;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import ua.com.mexanik.docslance.R;

/**
 * Created by root on 2/3/18.
 */

public class ModelDoctorAdapter extends RecyclerView.Adapter<ModelDoctorViewHolder>{
    private List<ModelDoctor> modelDoctors;
    private Context context;

    public ModelDoctorAdapter(List<ModelDoctor> modelDoctors, Context context)
    {
        this.modelDoctors = modelDoctors;
        this.context = context;
    }
    @Override
    public ModelDoctorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_doc, parent, false);
        context = itemView.getContext();
        return new ModelDoctorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ModelDoctorViewHolder holder, int position) {
        ModelDoctor doctor = modelDoctors.get(position);
        holder.ratingBar.setRating((float) doctor.getRating());
        holder.nameSurnameTextView.setText(doctor.getName() + " " + doctor.getSurname());
        holder.avatarImageView.setImageURI(Uri.parse(doctor.getAvaUrl()));
        Glide.with(context)
                .load(doctor.getAvaUrl())
                .into(holder.avatarImageView);
        holder.pricePerHourTextView.setText("Price per hour" + " " + (int)doctor.getPrice() + " " + "/UAH");
    }

    @Override
    public int getItemCount() {
        return modelDoctors.size();
    }
}
