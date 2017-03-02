package com.smithjacob.quickpoll;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class SearchUPCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_upc);
    }

    public void ebay(View view)
    {
        Intent ebayBrowserintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ebay.com/itm/Bose-SoundTrue-Headphones-Around-Ear-Style-Black-/142295233128?hash=item212174f268:g:yJIAAOSw~AVYtIZU.kh"));
        startActivity(ebayBrowserintent);

    }

    public void amazon(View view){
        Intent amazonBrowserintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.com/Bose-SoundTrue-Headphones-Around-Ear-Style/dp/B00IUICOR6/ref=sr_1_1?s=beauty&ie=UTF8&qid=1488409242&sr=8-1&keywords=017817627962.kh"));
        startActivity(amazonBrowserintent);
    }

    public void walmart(View view){
        Intent walmartBrowserintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.walmart.com/ip/Bose-SoundTrue-Headphones-Around-Ear-Style-Stereo-Black-Wired-Over-the-head-Binaural-Circumaural-5.50-ft-Cable/47507837.kh"));
        startActivity(walmartBrowserintent);
    }


}
