package com.smithjacob.quickpoll;

import android.app.Activity;
import android.net.Uri;
import android.os.Environment;
import android.content.Intent;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.File;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class MainActivity extends FragmentActivity {
    EnterUPCFragment _enterUPCFragment;
    HomeFragment _homeFragment;
    HistoryFragment _historyFragment;
    ImportPhotoFragment _importPhotoFragment;
    ScanBarcodeFragment _scanBarcodeFragment;

    boolean doILoveMeghan = true;
    Button scanUPCButton, enterUPCButton, importButton, histButton;
    TextView barcodeResult;

    public static final int IMAGE_GALLERY_REQUEST = 20;
    final static int cameraData = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanUPCButton = (Button) findViewById(R.id.scan_barcode);
        /*scanUPCButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switchFragment("scan barcode");
            }
        });*/

        enterUPCButton = (Button) findViewById(R.id.enter_upc_manually);
        enterUPCButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switchFragment("enter upc");
            }
        });

        importButton = (Button) findViewById(R.id.import_photo);
        /*importButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switchFragment("import photo");
            }
        });*/

        histButton = (Button) findViewById(R.id.history);
        histButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switchFragment("history");
            }
        });

        // Initialize the fragments if they do not exist
        if (_homeFragment == null) {
            _homeFragment = new HomeFragment();
            _homeFragment.setActivity(this);
        }
        if (_enterUPCFragment == null) {
            _enterUPCFragment = new EnterUPCFragment();
            _enterUPCFragment.setActivity(this);
        }
        if (_historyFragment == null) {
            _historyFragment = new HistoryFragment();
            _historyFragment.setActivity(this);
        }
        if (_importPhotoFragment == null) {
            _importPhotoFragment = new ImportPhotoFragment();
            _importPhotoFragment.setActivity(this);
        }
        if (_scanBarcodeFragment == null) {
            _scanBarcodeFragment = new ScanBarcodeFragment();
            _scanBarcodeFragment.setActivity(this);
        }

        // Add the initial fragment
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.activity_main, _homeFragment).commit();
    }

    public void switchFragment(String newFragment) {
        if (newFragment.equals("enter upc")) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.fragment_container, _enterUPCFragment).commit();
        }
        else if (newFragment.equals("home")) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.fragment_container, _homeFragment).commit();
        }
        else if (newFragment.equals("history")) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.fragment_container, _historyFragment).commit();
        }
        /*else if (newFragment.equals("import photo")) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.fragment_container, _importPhotoFragment).commit();
        }*/
        else if (newFragment.equals("scan barcode")) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.fragment_container, _scanBarcodeFragment).commit();
        }
    }

    public void scan(View view) {
        Intent intent = new Intent(this, ScanBarcodeActivity.class);
        startActivityForResult(intent, cameraData);
    }

    public void goToGallery(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        Uri data = Uri.parse(pictureDirectoryPath);
        photoPickerIntent.setDataAndType(data, "image/*");
        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra("barcode");
                    barcodeResult.setText("Barcode value : " + barcode.displayValue);
                } else {
                    barcodeResult.setText("No barcode found");
                }
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
