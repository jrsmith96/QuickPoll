package com.smithjacob.quickpoll;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class SearchUPCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_upc);

        //Bundle bundle = getIntent().getExtras();
        //int upc = bundle.getInt("BBB");
        ReviewManager RM = new ReviewManager(12345);
        ReviewManager.grabReviews();

        // Product Rating
        double rt = ReviewManager.getAvgRating();
        TextView fRating = (TextView) findViewById(R.id.productAvgRating);
        fRating.setText(String.valueOf(rt));

        // Product Price
        double fPrice = ReviewManager.getPrices().get(0);

        // Product Name
        TextView fName = (TextView) findViewById(R.id.productName);
        fName.setText(/*getName*/);

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
