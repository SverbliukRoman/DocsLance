package ua.com.mexanik.docslance.helpers;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ua.com.mexanik.docslance.R;

import static android.content.ContentValues.TAG;
import static ua.com.mexanik.docslance.Constants.PREFS_PROFILE_FIRST_NAME;
import static ua.com.mexanik.docslance.Constants.PREFS_PROFILE_IMAGE_URL;
import static ua.com.mexanik.docslance.Constants.URL_TO_POST;

/**
 * Created by hp on 003 03.11.2017.
 */

public class DataGetterFromServer extends Thread {
    private String param;
    private String url;
    private Context context;
    private String responseData = "";
    private DataParser dataParser;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Activity referenceActivity;

    public DataGetterFromServer(String url, String param, Context context, DataParser dataParser, SwipeRefreshLayout swipeRefreshLayout) {
        this.param = param;
        this.url = url;
        this.context = context;
        this.dataParser = dataParser;
        this.swipeRefreshLayout = swipeRefreshLayout;
        referenceActivity = (Activity) context;
    }

    @Override
    public void run() {
        Request request = new Request.Builder()
                .url(url + param)
                .get()
                .build();
        OkHttpClient client = new OkHttpClient();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (checkInternet(context)) {

            if (response != null) {
                if (response.code() == 200) {
                    try {
                        responseData = response.body().string();
                        dataParser.parseResponse(responseData);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //Process the response Data
                    Log.d("ResponseData", responseData);
                    //loader.turnOffLoader();
                } else {
                    //Server problem
                    Toast.makeText(context, context.getResources().getString(R.string.connection_problems) + " " + response.code(), Toast.LENGTH_SHORT).show();
                    //loader.turnOffLoader();

                }
            } else {

                referenceActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, context.getResources().getString(R.string.connection_problems), Toast.LENGTH_SHORT).show();
                        //loader.turnOffLoader();

                    }
                });
            }
        }
    }

    private boolean checkInternet(final Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) {
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    Log.d(TAG, "checkInternet: " + "Connected to WIFI");
                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    Log.d(TAG, "checkInternet: " + "Connected to Mobile data");
                }
                return true;
            }
        }

        Log.d(TAG, "checkInternet: " + "Not connected");
        referenceActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, context.getString(R.string.internet_connection_failed), Toast.LENGTH_SHORT).show();
                // disable swipe
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                //loader.turnOffLoader();
                //getMainProgressBar().setVisibility(View.INVISIBLE);
            }
        });

        return false;

    }

    public static void sendPost(final Context context) {
        final HashMap<String, String> params = new HashMap<>();

            // implement here http connection
            params.put("photo", PreferenceManager.getDefaultSharedPreferences(context).getString(PREFS_PROFILE_IMAGE_URL, "bitch"));
            params.put("name", PreferenceManager.getDefaultSharedPreferences(context).getString(PREFS_PROFILE_FIRST_NAME, "fucker"));

        RequestQueue queue = Volley.newRequestQueue(context);


        StringRequest strRequest = new StringRequest(com.android.volley.Request.Method.POST, URL_TO_POST,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();

                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        queue.add(strRequest);
    }
    public static void sendPost(final Context context, String experience, int price, int age, String education, String specialization) {
        final HashMap<String, String> params = new HashMap<>();

        // implement here http connection
        params.put("photo", PreferenceManager.getDefaultSharedPreferences(context).getString(PREFS_PROFILE_IMAGE_URL, "bitch"));
        params.put("name", PreferenceManager.getDefaultSharedPreferences(context).getString(PREFS_PROFILE_FIRST_NAME, "fucker"));
        params.put("age", String.valueOf(age));
        params.put("experience", experience);
        params.put("Price", String.valueOf(price));
        params.put("education", education);
        params.put("education", specialization);

        RequestQueue queue = Volley.newRequestQueue(context);


        StringRequest strRequest = new StringRequest(com.android.volley.Request.Method.POST, URL_TO_POST,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();

                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        queue.add(strRequest);
    }



}
