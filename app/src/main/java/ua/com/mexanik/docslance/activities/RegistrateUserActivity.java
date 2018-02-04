package ua.com.mexanik.docslance.activities;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import ua.com.mexanik.docslance.R;
import ua.com.mexanik.docslance.fragments.fragments.registratedocfragment.FixturesTabsFragment;

public class RegistrateUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrate_user);

        FixturesTabsFragment fxt = new FixturesTabsFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.registration_container, fxt);
        ft.commit();

        // denied orientation changing
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        setStatusBarColor();


    }

    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }


}
