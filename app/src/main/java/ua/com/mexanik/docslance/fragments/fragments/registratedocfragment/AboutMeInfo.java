package ua.com.mexanik.docslance.fragments.fragments.registratedocfragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.spark.submitbutton.SubmitButton;

import de.hdodenhof.circleimageview.CircleImageView;
import ua.com.mexanik.docslance.R;
import ua.com.mexanik.docslance.activities.MainActivity;
import ua.com.mexanik.docslance.helpers.DataGetterFromServer;

import static com.facebook.FacebookSdk.getApplicationContext;
import static ua.com.mexanik.docslance.Constants.PREFS_PROFILE_IMAGE_URL;
import static ua.com.mexanik.docslance.fragments.fragments.registratedocfragment.ChooseARoleFragment.isDoc;

public class AboutMeInfo extends Fragment {

    AutoCompleteTextView mAutoCompleteTextViewSpecialization;
    AutoCompleteTextView mAutoCompleteTextViewEducation;
    EditText mExperienceEditText;
    EditText mEditTextCostPerHour;
    SubmitButton submitButton;

    final String[] mProfessions = {"Urology", "Dermatology", "Psychology", "Gastroenterology",
            "Pediatrics", "Stomatology"};

    final String[] mEducation = {"Lviv National Medical University named after Danylo Halytsky",
            "OO Bohomolets National Medical University", "Bukovinian State Medical University", "Kharkiv National Medical University"};

    SharedPreferences.Editor editor;
    SharedPreferences prefs;
    CircleImageView profileImageView;

    public AboutMeInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_me_info, container, false);


        mAutoCompleteTextViewSpecialization = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextViewSpecialization);
        mAutoCompleteTextViewEducation = view.findViewById(R.id.editTextViewEducation);
        mExperienceEditText = view.findViewById(R.id.editTextTextViewExperience);
        mEditTextCostPerHour = view.findViewById(R.id.editTextPricePerHour);

        mAutoCompleteTextViewEducation.setAdapter(new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_dropdown_item_1line, mEducation));

        mAutoCompleteTextViewSpecialization.setAdapter(new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_dropdown_item_1line, mProfessions));


        submitButton = view.findViewById(R.id.submit);


        // prefs initialization
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = prefs.edit();

        // get prefs for loading
        String profileImageUrl = prefs.getString(PREFS_PROFILE_IMAGE_URL, "");

        profileImageView = view.findViewById(R.id.profile_image_about_me_info);
        Glide.with(getApplicationContext())
                .load(profileImageUrl)
                .into(profileImageView);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (!isDoc)
                    {
                        DataGetterFromServer.sendPost(getApplicationContext());
                    }
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


        return view;
    }
}
