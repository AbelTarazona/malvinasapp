package com.example.malvinasapp;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {
    TextView name;
    CardView cvItem;


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        name = view.findViewById(R.id.name);
        cvItem = view.findViewById(R.id.cvItem);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String nombre = preferences.getString("Name", "");
        name.setText(" "+nombre);

        cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                changeScreen(productDetailFragment);
            }
        });


        return view;

    }

    public void changeScreen(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framely, fragment);
        fragmentTransaction.commit();
    }

}
