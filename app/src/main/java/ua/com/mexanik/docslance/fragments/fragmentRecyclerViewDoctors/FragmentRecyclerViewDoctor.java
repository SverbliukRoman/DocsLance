package ua.com.mexanik.docslance.fragments.fragmentRecyclerViewDoctors;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ua.com.mexanik.docslance.Constants;
import ua.com.mexanik.docslance.R;
import ua.com.mexanik.docslance.fragments.fragmentRecyclerViewDoctors.recyclerview.ModelDoctor;
import ua.com.mexanik.docslance.fragments.fragmentRecyclerViewDoctors.recyclerview.ModelDoctorAdapter;
import ua.com.mexanik.docslance.helpers.DataGetterFromServer;
import ua.com.mexanik.docslance.helpers.DataParser;

import static ua.com.mexanik.docslance.Constants.DOC_AGE;
import static ua.com.mexanik.docslance.Constants.DOC_EDU;
import static ua.com.mexanik.docslance.Constants.DOC_EXP;
import static ua.com.mexanik.docslance.Constants.DOC_GAS;
import static ua.com.mexanik.docslance.Constants.DOC_NAME;
import static ua.com.mexanik.docslance.Constants.DOC_PHOTO;
import static ua.com.mexanik.docslance.Constants.DOC_PRICE;
import static ua.com.mexanik.docslance.Constants.DOC_RATING;
import static ua.com.mexanik.docslance.Constants.DOC_SPEC;
import static ua.com.mexanik.docslance.Constants.DOC_SURN;


public class FragmentRecyclerViewDoctor extends Fragment {
    RecyclerView recyclerView;
    ArrayList<ModelDoctor> modelDoctorArrayList;
    public  String param = "";
    SwipeRefreshLayout swipeRefreshLayout;
    private ModelDoctorAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private Activity mActivity;



    public FragmentRecyclerViewDoctor() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivity = getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view_doctor, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        swipeRefreshLayout = view.findViewById(R.id.fragment_docs_swipe_refresh_layout);

        modelDoctorArrayList = new ArrayList<>();
        //get shared preferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        param += /*prefs.getString(Constants.EXTRA_LOGIN_CODE, "0");*/  "1";

        // recycler decorator
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        getDataFromConnection(); // fill our arraylist

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //filterYear = 0;
                modelDoctorArrayList.clear();
                getDataFromConnection();
            }
        });

        return view;
    }
    private void getDataFromConnection() {
        DataGetterFromServer dataGetterFromServer = new DataGetterFromServer(Constants.URL_TO_PARSE, param, getContext(), new DataParser() {
            @Override
            public void parseResponse(String responseData) {
                try {
                    //Process the response Data
                    JSONArray jsonArray = new JSONArray(responseData);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject doctor = jsonArray.getJSONObject(i);

                        Log.d("doctor", String.valueOf(doctor));
                        //String date = doctor.getString("data").substring(0, 10);// trim because Danya is fucktar, date is not in proper format (;/;/; hh:mm:ss)

                        ModelDoctor modelDoctor = new ModelDoctor(doctor.getString(DOC_NAME),
                                doctor.getString(DOC_SURN),
                                doctor.getString(DOC_PHOTO),
                                doctor.getString(DOC_AGE),
                                doctor.getString(DOC_SPEC),
                                doctor.getString(DOC_EDU),
                                Double.parseDouble(doctor.getString(DOC_RATING)),
                                Double.parseDouble(doctor.getString(DOC_PRICE)),
                                doctor.getString(DOC_EXP),
                                Integer.parseInt(doctor.getString(DOC_GAS))
                        );


                        modelDoctorArrayList.add(modelDoctor);
                    }

                    Log.d("modelMaterials", modelDoctorArrayList.toString());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            // adapter recreation, for some reason notifyDataSetChanged doesnt work
                            mAdapter = new ModelDoctorAdapter(modelDoctorArrayList, mActivity.getApplicationContext());
                            recyclerView.swapAdapter(mAdapter, true);

                            /*if (filterYear != 0) {
                                mAdapter.getFilter().filter("" + filterYear +filterMonth+filterDay);
                            }*/

                            // duplication avoiding (just removing all from the list)
                            //modelMaterialsList.clear();
                            // stop refreshing
                            swipeRefreshLayout.setRefreshing(false);
                            // make big loader invisible
                            //getMainProgressBar().setVisibility(View.INVISIBLE);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, swipeRefreshLayout);
        dataGetterFromServer.start();
    }

}
