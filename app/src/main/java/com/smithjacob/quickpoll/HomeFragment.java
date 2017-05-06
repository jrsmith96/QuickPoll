package com.smithjacob.quickpoll;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment {
    MainActivity _activity;
    EnterUPCFragment _enterUPCFragment;
    HomeFragment _homeFragment;

    Button scanUPCButton, enterUPCButton, importButton, histButton;
    TextView barcodeResult;

    public static final int IMAGE_GALLERY_REQUEST = 20;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, parent, false);

        /*scanUPCButton = (Button) view.findViewById(R.id.scan_barcode);
        scanUPCButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _activity.switchFragment("scan barcode");
            }
        });

        enterUPCButton = (Button) view.findViewById(R.id.enter_upc_manually);
        enterUPCButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _activity.switchFragment("enter upc");

            }
        });

        importButton = (Button) view.findViewById(R.id.import_photo);
        importButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _activity.switchFragment("import photo");
            }
        });

        histButton = (Button) view.findViewById(R.id.history);
        histButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _activity.switchFragment("history");
            }
        });*/

        return view;
    }

    // This should be called immediately after constuction.
    // The fragment needs to know the activity in order to switch screens.
    public void setActivity(MainActivity activity) {
        _activity = activity;
    }

}
