package com.smithjacob.quickpoll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EnterUPCActivity extends AppCompatActivity {

    EditText editUPC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_upc);

        editUPC = (EditText) findViewById(R.id.EnterUPC);
    }


    public void SearchUPC(View view)
    {
        Intent intent = new Intent(this, SearchUPCActivity.class);
        startActivity(intent);
    }

}
