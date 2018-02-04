package ua.com.mexanik.docslance.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ua.com.mexanik.docslance.Constants;
import ua.com.mexanik.docslance.R;

public class ActivityDoctor extends AppCompatActivity {

    public TextView name;
    public TextView specialization;
    public TextView age;
    public TextView experience;
    public TextView education;
    public ImageView avatarImageView;
    public RatingBar ratingBar;
    //public ImageButton imageButton;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        //imageButton = findViewById(R.id.person_image);
        avatarImageView = findViewById(R.id.person_image);
        name = findViewById(R.id.name);
        specialization = findViewById(R.id.specialization);
        age = findViewById(R.id.age);
        experience = findViewById(R.id.experience);
        education = findViewById(R.id.education);

        intent = getIntent();

        Glide.with(this).load(intent.getStringExtra(Constants.EXTRAS_ACTIVITY_DOCTORS_FOTOURL)).
                into(avatarImageView);

        name.setText(intent.getStringExtra(Constants.EXTRAS_ACTIVITY_DOCTORS_NAME));
        specialization.setText(intent.getStringExtra(Constants.EXTRAS_ACTIVITY_DOCTORS_SPECIALIZATION));
        age.setText("Возрост: "+intent.getStringExtra(Constants.EXTRAS_ACTIVITY_DOCTORS_AGE));
        experience.setText("Место роботи: "+intent.getStringExtra(Constants.EXTRAS_ACTIVITY_DOCTORS_EXPERIENCE));
        education.setText(intent.getStringExtra(Constants.EXTRAS_ACTIVITY_DOCTORS_EDUCATION));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }

}
