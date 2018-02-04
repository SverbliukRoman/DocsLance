package ua.com.mexanik.docslance.fragments.fragments.recyclerViewDoctors.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import ua.com.mexanik.docslance.R;

/**
 * Created by root on 2/3/18.
 */

public class ModelDoctorViewHolder/* extends RecyclerView.ViewHolder implements View.OnClickListener */{
/*
    public TextView nameSurnameTextView;
    public TextView pricePerHourTextView;
    public ImageView avatarImageView;
    public RatingBar ratingBar;
    public ImageButton imageButton;

    public ModelDoctorViewHolder(View itemView) {
        super(itemView);
        listenerRef = new WeakReference<>(listener);
        nameSurnameTextView = itemView.findViewById(R.id.text_view_card_view_title); // title
        pricePerHourTextView = itemView.findViewById(R.id.text_view_price); // bottom|price
        avatarImageView = itemView.findViewById(R.id.profile_image_card_view); // avatar
        ratingBar = itemView.findViewById(R.id.ratingBar);
        imageButton = itemView.findViewById(R.id.go_to_activity);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == imageButton.getId()) {
            Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }

        listenerRef.get().onPositionClicked(getAdapterPosition());
    }*/
}
