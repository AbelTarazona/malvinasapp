package com.example.malvinasapp;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UbicaFragment extends Fragment implements View.OnClickListener {

    Spinner spRubro,spCategoria, spMarca;
    ArrayList<String> alCategoria,alMarca,alRubro;
    EditText monto;
    private ShimmerFrameLayout mShimmerViewContainer;
    LinearLayout lydata;
    Button btnSearch;

    ImageView cvItem;

    public UbicaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ubica, container, false);
        mShimmerViewContainer = v.findViewById(R.id.shimmer_view_container);
        monto = v.findViewById(R.id.monto);
        lydata = v.findViewById(R.id.lyData);
        btnSearch = v.findViewById(R.id.btnBusqueda);
        cvItem = v.findViewById(R.id.cvItemly);

        spCategoria = v.findViewById(R.id.spCategoria);
        spMarca = v.findViewById(R.id.spMarca);
        spRubro = v.findViewById(R.id.spRubro);

        alCategoria = new ArrayList<String>();
        alMarca = new ArrayList<String>();
        alRubro = new ArrayList<String>();

        alRubro.add("RUBRO");
        alRubro.add("Tecnología");
        alRubro.add("Cocina");
        alRubro.add("Ferretería");
        alRubro.add("Deportes");
        alRubro.add("Ropa");

        alMarca.add("MARCA");
        alCategoria.add("CATEGORÍA");

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,alRubro);
        spRubro.setAdapter(adapter);
        spRubro.setSelection(0);

        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,alCategoria);
        spCategoria.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,alMarca);
        spMarca.setAdapter(adapter3);



        spRubro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                alCategoria.clear();
                alCategoria.add("CATEGORÍA");
                if (i == 1) {
                    alCategoria.add("Celular");
                    alCategoria.add("Laptop");
                    alCategoria.add("Tablet");
                    alCategoria.add("Accesorios");
                } else if (i == 3) {
                    alCategoria.add("Electricidad");
                    alCategoria.add("Pintura");
                    alCategoria.add("Herramienta");
                }
                ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,alCategoria);
                spCategoria.setAdapter(adapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                alMarca.clear();
                alMarca.add("MARCA");
                if (i == 1) {
                    alMarca.add("Apple");
                    alMarca.add("Google");
                    alMarca.add("Xiaomi");
                    alMarca.add("One Plus");
                } else if (i == 3) {
                    alMarca.add("Cio Min");
                    alMarca.add("Forte");
                    alMarca.add("Cerro Verde");
                }
                ArrayAdapter<CharSequence> adapter3 = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,alMarca);
                spMarca.setAdapter(adapter3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                changeScreen(productDetailFragment);*/

                mShimmerViewContainer.startShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.VISIBLE);
                lydata.setVisibility(View.GONE);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        mShimmerViewContainer.stopShimmerAnimation();
                        mShimmerViewContainer.setVisibility(View.GONE);
                        lydata.setVisibility(View.VISIBLE);

                    }
                }, 3000);

            }
        });

        cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("asdsa","asdasdsadasd");
                ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                changeScreen(productDetailFragment);
            }
        });



        return v;
    }

    public void changeScreen(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framely, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cvItemly:
                Log.d("asdsa","asdasdsadasd");

                break;
        }
    }
}
