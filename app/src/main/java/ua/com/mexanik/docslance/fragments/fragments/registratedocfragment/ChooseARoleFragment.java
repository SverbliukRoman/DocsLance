package ua.com.mexanik.docslance.fragments.fragments.registratedocfragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import ua.com.mexanik.docslance.Constants;
import ua.com.mexanik.docslance.R;

import static com.facebook.FacebookSdk.getApplicationContext;


public class ChooseARoleFragment extends Fragment {
    CircleImageView doc;
    CircleImageView patient;
    public static boolean isDoc;

    public ChooseARoleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_arole, container, false);
        doc = view.findViewById(R.id.profile_image1); // doc
        doc.setImageResource(R.drawable.doc);
        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(Constants.PREFS_LOGIN_STATUS, "doctor");
                editor.apply();
                Toast.makeText(getApplicationContext(), "You've chosen a doctor role!", Toast.LENGTH_SHORT).show();
                isDoc = true;
            }
        });
        patient = view.findViewById(R.id.profile_image2); // patient
        patient.setImageResource(R.drawable.sick);
        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(Constants.PREFS_LOGIN_STATUS, "patient");
                editor.apply();
                Toast.makeText(getApplicationContext(), "You've chosen a doctor client role!", Toast.LENGTH_SHORT).show();
                isDoc = false;
            }
        });
        return view;
    }

}
