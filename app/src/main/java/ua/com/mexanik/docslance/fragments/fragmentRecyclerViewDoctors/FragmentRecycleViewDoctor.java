package ua.com.mexanik.docslance.fragments.fragmentRecyclerViewDoctors;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.com.mexanik.docslance.R;


public class FragmentRecycleViewDoctor extends Fragment {


    public FragmentRecycleViewDoctor() {
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
        return inflater.inflate(R.layout.fragment_fragment_recycle_view_doctor, container, false);
    }

}
