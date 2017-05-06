package com.smithjacob.quickpoll;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class SearchUPCActivity extends AppCompatActivity {

    TextView ebayPrice;
    TextView productName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_upc);


        //Bundle bundle = getIntent().getExtras();
        //int upc = bundle.getInt("BBB");
        ReviewManager testRM = new ReviewManager("045496590420");//(ScanBarcodeActivity.upc);
        testRM.grabReviews();
        //String pdct = testRM.checkProduct();
        //String title = testRM.getProduct().get_Name();
        //productName = (TextView) findViewById(R.id.productName);
        //productName.setText(title);

        // Product Rating
        double rt = testRM.getAvgRating();


        // Product Price
        double fprice = testRM.getPrices().get(0);
        ebayPrice = (TextView) findViewById(R.id.productAvgRating);
        ebayPrice.setText(String.valueOf(fprice));

        // Product Name
        TextView name = (TextView) findViewById(R.id.productName);
        name.setText(testRM.getProduct().get_Name());

        //Product Description
        TextView fDesc = (TextView) findViewById(R.id.productDescription);
        fDesc.setText(testRM.getProduct().get_Description());

    }

    public void ebay(View view) {
        Intent ebayBrowserintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ebay.com/itm/Apple-iPad-Pro-12-9-Wi-Fi-/131842522235?var=&hash=item1eb26d3c7b%3Am%3AmdxHSpFKdlyzMgnkM4Cqv9A"));
        startActivity(ebayBrowserintent);
    }

    public void amazon(View view) {
        Intent amazonBrowserintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.com/Apple-iPad-12-9-Inch-Display-Space/dp/B0155OCLWK"));
        startActivity(amazonBrowserintent);
    }

    public void walmart(View view) {
        Intent walmartBrowserintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.walmart.com/ip/Apple-iPad-Pro-Wi-Fi-32GB/47364203"));
        startActivity(walmartBrowserintent);
    }


}
