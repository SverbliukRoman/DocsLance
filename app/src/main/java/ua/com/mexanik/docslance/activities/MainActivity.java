package ua.com.mexanik.docslance.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;
import ua.com.mexanik.docslance.R;
import ua.com.mexanik.docslance.fragments.fragments.registratedocfragment.FixturesTabsFragment;
import ua.com.mexanik.docslance.fragments.fragments.FragmentRecyclerViewDoctor;

import static ua.com.mexanik.docslance.Constants.CHECK_IF_IS_AUTH_PASSED;
import static ua.com.mexanik.docslance.Constants.PREFS_PROFILE_FIRST_NAME;
import static ua.com.mexanik.docslance.Constants.PREFS_PROFILE_IMAGE_URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RatingDialogListener {
    SharedPreferences.Editor editor;
    SharedPreferences prefs;
    CircleImageView profileImageView;
    TextView profileTextName;
    ImageView headerImageView;
    FragmentRecyclerViewDoctor fragmentRecyclerViewDoctor;
    FixturesTabsFragment fixturesTabsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // fab moar coins
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddMoreCoinsActivity.class));
            }
        });

        // denied orientation changing
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // prefs initialization
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = prefs.edit();

        // get prefs for loading
        String profileImageUrl = prefs.getString(PREFS_PROFILE_IMAGE_URL, "");
        Log.d("URL", profileImageUrl);
        String profileName = prefs.getString(PREFS_PROFILE_FIRST_NAME, "");
        Log.d("Name", profileName);

        View headerLayout = navigationView.getHeaderView(0);// я ебу нахуй оно, если я мог просто его импортировать же....просто работает и х с ним

        // finding our elements
        profileImageView = headerLayout.findViewById(R.id.profile_image);
        profileTextName = headerLayout.findViewById(R.id.profile_name);

        // load image into circleImageView
        Picasso.with(getApplicationContext())
                .load(profileImageUrl)
                .into(profileImageView);
        profileTextName.setText(getString(R.string.greetings) + ", " + profileName + " " + "!");

        headerImageView = headerLayout.findViewById(R.id.header_image_view);

        // add 3d dna image
        Glide.with(this)
                .load(R.raw.source)
                .into(headerImageView);

        // recyclerview

        if (savedInstanceState == null) {
            if (fragmentRecyclerViewDoctor == null) {
                fragmentRecyclerViewDoctor = new FragmentRecyclerViewDoctor();
                replaceWithFragment(fragmentRecyclerViewDoctor, null);
            } else {
                replaceWithFragment(fragmentRecyclerViewDoctor, null);
            }
        }
        showDialog();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
        } else if (id == R.id.nav_docs) {
            if (fragmentRecyclerViewDoctor == null) {
                replaceWithFragment(new FragmentRecyclerViewDoctor(), null);
            } else {
                replaceWithFragment(fragmentRecyclerViewDoctor, null);
            }
        } else if (id == R.id.nav_about_us) {
            if (fixturesTabsFragment == null) {
                replaceWithFragment(new FixturesTabsFragment(), null);
            } else {
                replaceWithFragment(fixturesTabsFragment, null);
            }
        } else if (id == R.id.nav_liked) {

        } else if (id == R.id.nav_exit) {
            rewriteLogInValueAndBackToLogIn(editor);
            System.gc();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void rewriteLogInValueAndBackToLogIn(SharedPreferences.Editor editor) {
        // change value of our variable
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();

        // trigger switching
        editor.putBoolean(CHECK_IF_IS_AUTH_PASSED, false);
        editor.apply();

        // back to LoginActivity
        Intent backToLoginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(backToLoginIntent);
        finish();
    }

    private void showDialog() {
        new AppRatingDialog.Builder()
                .setPositiveButtonText("Submit")
                .setNegativeButtonText("Cancel")
                .setNeutralButtonText("Later")
                .setNoteDescriptions(Arrays.asList("Very Bad", "Not good", "Quite ok", "Very Good", "Excellent !!!"))
                .setDefaultRating(2)
                .setTitle("Rate this application")
                .setDescription("Please select some stars and give your feedback")
                .setDefaultComment("This app is pretty cool !")
                .setStarColor(R.color.starColor)
                .setNoteDescriptionTextColor(R.color.noteDescriptionTextColor)
                .setTitleTextColor(R.color.colorWhite)
                .setDescriptionTextColor(R.color.colorWhite)
                .setHint("Please write your comment here ...")
                .setHintTextColor(R.color.colorWhite)
                .setCommentTextColor(R.color.commentTextColor)
                .setCommentBackgroundColor(R.color.colorPrimaryDark)
                .setWindowAnimation(R.style.MyDialogFadeAnimation)
                .create(MainActivity.this)
                .show();
    }

    public void replaceWithFragment(Fragment fragment, Handler handler) {
        // frgmcont has strong reference because we always replace it exactly
        /*if (fragment.isAdded()) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            for (Fragment addedFragment : getSupportFragmentManager().getFragments()) {

                if (addedFragment != fragment) {
                    ft.hide(addedFragment);
                }
            }
            ft.show(fragment).commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frgmCont, fragment)
                    .addToBackStack(String.valueOf(fragment.getId()))
                    .show(fragment)
                    .commit();
        }*/

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frgmCont, fragment);
        ft.commit();
        if (handler != null) handler.sendEmptyMessage(1);

    }

    @Override
    public void onPositiveButtonClicked(int i, String s) {
        Toast.makeText(getApplicationContext(), "rating is : " + i, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNegativeButtonClicked() {

    }

    @Override
    public void onNeutralButtonClicked() {

    }
}
