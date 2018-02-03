package ua.com.mexanik.docslance.fragments.fragmentRecyclerViewDoctors.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import ua.com.mexanik.docslance.R;

/**
 * Created by root on 2/3/18.
 */

public class ModelDoctorViewHolder extends RecyclerView.ViewHolder {

    public TextView nameSurnameTextView;
    public TextView pricePerHourTextView;
    public ImageView avatarImageView;
    public RatingBar ratingBar;

    public ModelDoctorViewHolder(View itemView) {
        super(itemView);
        nameSurnameTextView = itemView.findViewById(R.id.text_view_card_view_title); // title
        pricePerHourTextView = itemView.findViewById(R.id.text_view_price); // bottom|price
        avatarImageView = itemView.findViewById(R.id.profile_image_card_view); // avatar
        ratingBar = itemView.findViewById(R.id.ratingBar);

    }
}
