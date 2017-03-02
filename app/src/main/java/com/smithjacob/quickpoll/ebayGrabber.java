package com.smithjacob.quickpoll;

import android.content.Intent;
import android.media.Image;
import android.widget.ImageView;

import com.google.android.gms.vision.barcode.Barcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import static android.R.attr.data;
import static java.lang.String.valueOf;

public class EbayGrabber extends ReviewGrabber{

    // First, we need to convert the returned barcode value to an integer.
    private static Intent data = new Intent();
    private static Barcode barcode = data.getParcelableExtra("barcode");
    private static Double d = Double.valueOf(barcode.displayValue);
    private static int upc = d.intValue();

    // Required Ebay data.
    private final static String EBAY_APP_ID = "JakeSmit-QuickPol-PRD-c2466ad44-ba7587d3";
    private final static String EBAY_FINDING_SERVICE_URI = "http://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME="
            + "{operation}&SERVICE-VERSION={version}&SECURITY-APPNAME="
            + "{applicationId}&GLOBAL-ID={globalId}&keywords={keywords}"
            + "&paginationInput.entriesPerPage={maxresults}";
    private static final String SERVICE_VERSION = "1.0.0";
    private static final String OPERATION_NAME = "findItemsByProduct";
    private static final String GLOBAL_ID = "EBAY-US";
    public final static int REQUEST_DELAY = 3000; // Necessary, but currently never used.
    public final static int MAX_RESULTS = 20; // How many results would we like to display?
    private int maxResults;

    // Mine
    public String collectReviews(int upc) {
        XPath xpath = XPathFactory.newInstance().newXPath();
        //com.smithjacob.quickpoll.Product part
        String title = null;
        Image img = null;
        if (_product.get_Name() == null)
            try {
                title = (String) xpath.evaluate("title", upc, XPathConstants.STRING);
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }
        _product.set_Name(title); /* Not sure how to fix this... */
        if (_product.get_Description() == null)
            _product.set_Description("Description YOU GET");
        if (_product.get_Image() == null) {

            String galleryUrl = null;
            try {
                galleryUrl = (String) xpath.evaluate("galleryURL", upc, XPathConstants.STRING);
                System.out.println(galleryUrl);


            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }

            try {
                // URLReader.read(galleryUrl); // Returns the URL in the form of a string.
                URL imgUrl = new URL(galleryUrl);
                //img = ImageIO.read(imgUrl); //Ask Letscher
            } catch (Exception e) {
                e.printStackTrace();
            }

            _product.set_Image(img);
        }
        try {
            _price = retrievePrice();
        } catch (Exception e) {
            e.printStackTrace();
        }
        _rating = 9.1;

         //Reviews
         int flag = 1;
         while(flag == 1) { // looping for adding reviews
             Review a = new Review("Nice", 8.88, 2, "www.google.com");
             Review b = new Review("OK", 5.0, 4, "www.facebook.com");
             _reviews.add(a);
             _reviews.add(b);
             _numRating = 2;
             flag = 0;
         }
        return "success";
    }

    // Mine
    public static double retrievePrice() throws Exception {
        XPath xpath = XPathFactory.newInstance().newXPath();
        String currentPrice = (String) xpath.evaluate("sellingStatus/currentPrice", upc, XPathConstants.STRING);
        return Double.parseDouble(currentPrice); // Converts the value string to a float.
    }

    private void run(String tag) throws Exception {
        String address = createAddress(tag);
        String response = URLReader.read(address);
        processResponse(response);
    }

    private String createAddress(String tag) {

        //substitute token
        String address = EbayGrabber.EBAY_FINDING_SERVICE_URI;
        address = address.replace("{version}", EbayGrabber.SERVICE_VERSION);
        address = address.replace("{operation}", EbayGrabber.OPERATION_NAME);
        address = address.replace("{globalId}", EbayGrabber.GLOBAL_ID);
        address = address.replace("{applicationId}", EbayGrabber.EBAY_APP_ID);
        address = address.replace("{keywords}", tag);
        address = address.replace("{maxresults}", "" + this.maxResults);

        return address;

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    //	This function accomplishes several things. processResponse returns a 'success' on packets    //
    //	that are properly communicating on the network, returns the product's price, ID #, name,	 //
    //	and the URL of the image.																	 //
    ///////////////////////////////////////////////////////////////////////////////////////////////////

    private void processResponse(String response) throws Exception {

        XPath xpath = XPathFactory.newInstance().newXPath();
        InputStream is = new ByteArrayInputStream(response.getBytes("UTF-8"));
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = domFactory.newDocumentBuilder();

        Document doc = builder.parse(is);
        XPathExpression ackExpression = xpath.compile("//findItemsByProductResponse/ack");
        XPathExpression itemExpression = xpath.compile("//findItemsByProductResponse/searchResult/item");

        String ackToken = (String) ackExpression.evaluate(doc, XPathConstants.STRING);
        System.out.println("ACK from ebay API :: " + ackToken);
        if (!ackToken.equals("Success")) {
            throw new Exception(" service returned an error");
        }

        // This line creates a list of all the scanned items.
        NodeList nodes = (NodeList) itemExpression.evaluate(doc, XPathConstants.NODESET);

        Node node = nodes.item(0);

        String itemId = (String) xpath.evaluate("itemId", node, XPathConstants.STRING);
        String title = (String) xpath.evaluate("title", node, XPathConstants.STRING);
        String itemUrl = (String) xpath.evaluate("viewItemURL", node, XPathConstants.STRING);
        String galleryUrl = (String) xpath.evaluate("galleryURL", node, XPathConstants.STRING);

        String currentPrice = (String) xpath.evaluate("sellingStatus/currentPrice", node, XPathConstants.STRING);

        System.out.println("currentPrice: " + currentPrice);
        System.out.println("itemId: " + itemId);
        System.out.println("title: " + title);
        System.out.println("galleryUrl: " + galleryUrl);

        //}

        is.close();

    }

    public static void main(String[] args) throws Exception {
        EbayGrabber driver = new EbayGrabber();
        String tag = "Velo binding machine";
        driver.run(java.net.URLEncoder.encode(tag, "UTF-8"));

    }
}
