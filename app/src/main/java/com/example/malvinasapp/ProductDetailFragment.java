package com.example.malvinasapp;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.net.URLEncoder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailFragment extends Fragment {

    Button btnWsp;

    public ProductDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_product_detail, container, false);
        btnWsp = v.findViewById(R.id.btnWhatsapp);
        btnWsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickWhatsApp("51989197085","*[Compra Pe']* Hola! Me interesa el producto 'Xiaomi Mi9' " +
                        "y me gustaría regatear el precio. \uD83D\uDE04" +
                        "\n*Datos de venta*" +
                        "\nCliente: Abel"+
                        "\nCódigo: 201901"+
                        "\nPrecio propuesto: S/. 1400.50");
            }
        });
        return v;
    }

    public void onClickWhatsApp(String phone, String message) {

        /*PackageManager pm=getActivity().getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = "YOUR TEXT HERE";

            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(getContext(), "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }*/

        PackageManager packageManager = getContext().getPackageManager();
        Intent i = new Intent(Intent.ACTION_VIEW);

        try {
            String url = "https://api.whatsapp.com/send?phone="+ phone +"&text=" + URLEncoder.encode(message, "UTF-8");
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            if (i.resolveActivity(packageManager) != null) {
                getContext().startActivity(i);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
