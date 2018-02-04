package ua.com.mexanik.docslance.fragments.fragmentRecyclerViewDoctors.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;
import java.util.List;

import ua.com.mexanik.docslance.Constants;
import ua.com.mexanik.docslance.R;
import ua.com.mexanik.docslance.activities.ActivityDoctor;

/**
 * Created by root on 2/3/18.
 */

public class ModelDoctorAdapter extends RecyclerView.Adapter<ModelDoctorAdapter.ModelDoctorViewHolder>{
    private List<ModelDoctor> modelDoctors;
    private Context context;
    private final InterfaceDoctor listener;

    public ModelDoctorAdapter(List<ModelDoctor> modelDoctors, Context context, InterfaceDoctor listener)
    {
        this.modelDoctors = modelDoctors;
        this.context = context;
        this.listener = listener;
    }
    @Override
    public ModelDoctorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_doc, parent, false);
        context = itemView.getContext();
        return new ModelDoctorViewHolder(itemView, listener);
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

    public class ModelDoctorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nameSurnameTextView;
        public TextView pricePerHourTextView;
        public ImageView avatarImageView;
        public RatingBar ratingBar;
        public ImageButton imageButton;
        private WeakReference<InterfaceDoctor> listenerRef;

        public ModelDoctorViewHolder(View itemView, InterfaceDoctor listener) {
            super(itemView);
            listenerRef = new WeakReference<>(listener);
            nameSurnameTextView = itemView.findViewById(R.id.text_view_card_view_title); // title
            pricePerHourTextView = itemView.findViewById(R.id.text_view_price); // bottom|price
            avatarImageView = itemView.findViewById(R.id.profile_image_card_view); // avatar
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageButton = itemView.findViewById(R.id.go_to_activity);
            imageButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == imageButton.getId()) {
                Intent intent = new Intent(v.getContext(), ActivityDoctor.class);
                intent.putExtra(Constants.EXTRAS_ACTIVITY_DOCTORS_NAME, nameSurnameTextView.getText());
                intent.putExtra(Constants.EXTRAS_ACTIVITY_DOCTORS_AGE, modelDoctors.get(getAdapterPosition()).getAge());
                intent.putExtra(Constants.EXTRAS_ACTIVITY_DOCTORS_EDUCATION, modelDoctors.get(getAdapterPosition()).getEducation());
                intent.putExtra(Constants.EXTRAS_ACTIVITY_DOCTORS_EXPERIENCE, modelDoctors.get(getAdapterPosition()).getExperience());
                intent.putExtra(Constants.EXTRAS_ACTIVITY_DOCTORS_FOTOURL, modelDoctors.get(getAdapterPosition()).getAvaUrl());
                intent.putExtra(Constants.EXTRAS_ACTIVITY_DOCTORS_PRICE, modelDoctors.get(getAdapterPosition()).getPrice());
                intent.putExtra(Constants.EXTRAS_ACTIVITY_DOCTORS_RATING, modelDoctors.get(getAdapterPosition()).getRating());
                intent.putExtra(Constants.EXTRAS_ACTIVITY_DOCTORS_SPECIALIZATION, modelDoctors.get(getAdapterPosition()).getSpecialization());
                listenerRef.get().onPositionClicked(getAdapterPosition());
                Toast.makeText(v.getContext(), "ITEM PRESSED = " + modelDoctors.get(getAdapterPosition()).getAvaUrl(), Toast.LENGTH_SHORT).show();

               v.getContext().startActivity(intent);
                Toast.makeText(v.getContext(), "ITEM PRESSED = " + nameSurnameTextView.getText(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }

            //listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }
}
