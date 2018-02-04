package ua.com.mexanik.docslance.fragments.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ua.com.mexanik.docslance.Constants;
import ua.com.mexanik.docslance.R;

import static ua.com.mexanik.docslance.Constants.PREFS_PROFILE_FIRST_NAME;
import static ua.com.mexanik.docslance.Constants.PREFS_PROFILE_IMAGE_URL;


public class FragmentProfile extends Fragment {

    public TextView name;
    public TextView specialization;
    public TextView age;
    public TextView experience;
    public TextView education;
    public TextView gas;
    public ImageView avatarImageView;
    public RatingBar ratingBar;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    //public ImageButton imageButton;
    Intent intent;

    public FragmentProfile() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        avatarImageView = view.findViewById(R.id.person_image);
        name = view.findViewById(R.id.namePro);
        specialization = view.findViewById(R.id.specialization);
        age = view.findViewById(R.id.age);
        experience = view.findViewById(R.id.experience);
        education = view.findViewById(R.id.education);
        gas = view.findViewById(R.id.gas);

        prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = prefs.edit();

        // get prefs for loading
        String profileImageUrl = prefs.getString(PREFS_PROFILE_IMAGE_URL, "");
        Log.d("URL", profileImageUrl);
        String profileName = prefs.getString(PREFS_PROFILE_FIRST_NAME, "");

        name.setText(getString(R.string.greetings) + ", " + "Костя Боскін" + " " + "!");

        Glide.with(this).load(profileImageUrl).
                into(avatarImageView);
        gas.setText("Баланс мого кошелька: " + Constants.PROBA_DOCTOR_GAS);
        name.setText(Constants.PROBA_DOCTOR_NAME);
        specialization.setText(Constants.PROBA_DOCTOR_SPECIALIZATION);
        age.setText("Возрост: "+Constants.PROBA_DOCTOR_AGE);
        experience.setText("Место роботи: "+Constants.PROBA_DOCTOR_EXPERIENCE);
        education.setText("Образование: "+ Constants.PROBA_DOCTOR_EDUCATION);
        return view;
    }

}
