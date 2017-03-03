package com.smithjacob.quickpoll;

import android.app.Activity;
import android.net.Uri;
import android.os.Environment;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.io.File;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class MainActivity extends AppCompatActivity {
    TextView barcodeResult;

    public static final int IMAGE_GALLERY_REQUEST = 20;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barcodeResult = (TextView)findViewById(R.id.barcode_result);
    }

    public void scanBarcode(View v) {
        Intent intent = new Intent(this, ScanBarcodeActivity.class);
        startActivityForResult(intent, 0);
    }

    public void goToEnterUPC(View view) {
        Intent intent = new Intent(this, EnterUPCActivity.class);
        startActivity(intent);
    }

    public void goToGallery(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        Uri data = Uri.parse(pictureDirectoryPath);
        photoPickerIntent.setDataAndType(data, "image/*");
        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
    }

    public void goToSearchHistory(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
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
