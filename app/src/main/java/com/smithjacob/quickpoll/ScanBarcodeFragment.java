package com.smithjacob.quickpoll;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

public class ScanBarcodeFragment extends Fragment {
    MainActivity _activity;
    SurfaceView cameraPreview;
    int flag = 1;

    public ScanBarcodeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, parent, false);
        return view;
    }

    // This should be called immediately after constuction.
    // The fragment needs to know the activity in order to switch screens.
    public void setActivity(MainActivity activity) {
        _activity = activity;
    }
}