package ua.com.mexanik.docslance.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import java.util.Arrays;

import ua.com.mexanik.docslance.Constants;
import ua.com.mexanik.docslance.R;

public class ActivityDoctor extends AppCompatActivity implements RatingDialogListener {

    public TextView name;
    public TextView specialization;
    public TextView age;
    public TextView experience;
    public TextView education;
    public ImageView avatarImageView;
    public RatingBar ratingBar;
    Button response;

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
        ratingBar = findViewById(R.id.ratingBarCV);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        intent = getIntent();

        Glide.with(this).load(intent.getStringExtra(Constants.EXTRAS_ACTIVITY_DOCTORS_FOTOURL)).
                into(avatarImageView);

        name.setText(intent.getStringExtra(Constants.EXTRAS_ACTIVITY_DOCTORS_NAME));
        specialization.setText(intent.getStringExtra(Constants.EXTRAS_ACTIVITY_DOCTORS_SPECIALIZATION));
        age.setText("Age is : "+intent.getStringExtra(Constants.EXTRAS_ACTIVITY_DOCTORS_AGE));
        experience.setText("Experience of work is : "+intent.getStringExtra(Constants.EXTRAS_ACTIVITY_DOCTORS_EXPERIENCE));
        education.setText("Education is : " + intent.getStringExtra(Constants.EXTRAS_ACTIVITY_DOCTORS_EDUCATION));
        ratingBar.setRating((float) intent.getDoubleExtra(Constants.DOC_RATING, 4));

        response = findViewById(R.id.respbutton);
        response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       setStatusBarColor();

    }

    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }
    private void showDialog() {
        new AppRatingDialog.Builder()
                .setPositiveButtonText("Submit")
                .setNegativeButtonText("Cancel")
                .setNeutralButtonText("Later")
                .setNoteDescriptions(Arrays.asList("Very Bad", "Not good", "Quite ok", "Very Good", "Excellent !!!"))
                .setDefaultRating(2)
                .setTitle("Rate this doctor")
                .setDescription("Please select some stars and give your feedback")
                .setDefaultComment("Nice job")
                .setStarColor(R.color.starColor)
                .setNoteDescriptionTextColor(R.color.noteDescriptionTextColor)
                .setTitleTextColor(R.color.colorWhite)
                .setDescriptionTextColor(R.color.colorWhite)
                .setHint("Please write your comment here ...")
                .setHintTextColor(R.color.colorWhite)
                .setCommentTextColor(R.color.colorWhite)
                .setCommentBackgroundColor(R.color.noteDescriptionTextColor)
                .setWindowAnimation(R.style.MyDialogFadeAnimation)
                .create(ActivityDoctor.this)
                .show();
    }

    @Override
    public void onPositiveButtonClicked(int i, String s) {
        Toast.makeText(getApplicationContext(), "Your rating added to query at server", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNegativeButtonClicked() {

    }

    @Override
    public void onNeutralButtonClicked() {

    }

}
