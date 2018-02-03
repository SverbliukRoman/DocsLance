package ua.com.mexanik.docslance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static ua.com.mexanik.docslance.Constants.CHECK_IF_IS_AUTH_PASSED;
import static ua.com.mexanik.docslance.Constants.PREFS_PROFILE_FIRST_NAME;
import static ua.com.mexanik.docslance.Constants.PREFS_PROFILE_IMAGE_URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences.Editor editor;
    SharedPreferences prefs;
    CircleImageView profileImageView;
    TextView profileTextName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
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
}
