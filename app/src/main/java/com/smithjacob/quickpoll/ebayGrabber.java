package com.smithjacob.quickpoll;

/**
 * Created by Jacob on 2/20/2017.
 */

import android.content.Intent;

import com.google.android.gms.vision.barcode.Barcode;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import static android.R.attr.data;
import static java.lang.String.valueOf;

public class ebayGrabber {

    // First, we need to convert the returned barcode value to an integer.
    Intent data = new Intent();
    Barcode barcode = data.getParcelableExtra("barcode");
    public final Double d = Double.valueOf(barcode.displayValue);
    public final int upc = d.intValue();

    public final static String EBAY_APP_ID = "JakeSmit-QuickPol-PRD-c2466ad44-ba7587d3";
    public final static String EBAY_FINDING_SERVICE_URL = "http://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME="
            + "{operation}&SERVICE-VERSION={version}&SECURITY-APPNAME="
            + "{applicationId}&GLOBAL-ID={globalId}&keywords={keywords}"
            + "&paginationInput.entriesPerPage={maxresults}";
    public static final String SERVICE_VERSION = "1.0.0";
    public static final String OPERATION_NAME = "findItemsByKeywords";
    public static final String GLOBAL_ID = "EBAY-US";
    public final static int REQUEST_DELAY = 3000;
    public final static int MAX_RESULTS = 10;
    private int maxResults;

    public ebayGrabber() {
        this.maxResults = MAX_RESULTS;
    }

    public ebayGrabber(int maxResults) {
        this.maxResults = maxResults;
    }

    // Mine
    public String collectReview(ebayGrabber.upc) {
        return "Hello";
    }

    // Mine
    public float retrievePrice(ebayGrabber.upc) throws Exception {

        XPath xpath = XPathFactory.newInstance().newXPath();
        String currentPrice = (String) xpath.evaluate("sellingStatus/currentPrice", upc, XPathConstants.STRING);
        float f = Float.parseFloat(currentPrice); // Converts the value string to a float.
        return f;

    }

    public void run(String tag) throws Exception {

        String address = createAddress(tag);
        System.out.println("Sending request to :: " + address);
        String response = URLReader.read(address);
        System.out.println("response :: " + response);
        // process the xml dump returned from EBAY
        processResponse(response);
        // Honor rate limits - wait between results
        // Thread.sleep(REQUEST_DELAY);

    }

    private String createAddress(String tag) {

        //substitute token
        String address = ebayGrabber.EBAY_FINDING_SERVICE_URL;
        address = address.replace("{version}", ebayGrabber.SERVICE_VERSION);
        address = address.replace("{operation}", ebayGrabber.OPERATION_NAME);
        address = address.replace("{globalId}", ebayGrabber.GLOBAL_ID);
        address = address.replace("{applicationId}", ebayGrabber.EBAY_APP_ID);
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
        XPathExpression ackExpression = xpath.compile("//findItemsByKeywordsResponse/ack");
        XPathExpression itemExpression = xpath.compile("//findItemsByKeywordsResponse/searchResult/item");

        String ackToken = (String) ackExpression.evaluate(doc, XPathConstants.STRING);
        System.out.println("ACK from ebay API :: " + ackToken);
        if (!ackToken.equals("Success")) {
            throw new Exception(" service returned an error");
        }

        NodeList nodes = (NodeList) itemExpression.evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < nodes.getLength(); i++) {

            Node node = nodes.item(i);

            String itemId = (String) xpath.evaluate("itemId", node, XPathConstants.STRING);
            String title = (String) xpath.evaluate("title", node, XPathConstants.STRING);
            String itemUrl = (String) xpath.evaluate("viewItemURL", node, XPathConstants.STRING);
            String galleryUrl = (String) xpath.evaluate("galleryURL", node, XPathConstants.STRING);

            String currentPrice = (String) xpath.evaluate("sellingStatus/currentPrice", node, XPathConstants.STRING);

            System.out.println("currentPrice: " + currentPrice);
            System.out.println("itemId: " + itemId);
            System.out.println("title: " + title);
            System.out.println("galleryUrl: " + galleryUrl);

        }

        is.close();

    }

    private void print(String name, String value) {
        System.out.println(name + "::" + value);
    }

    // Should return the product's review(s)
    //public String collectReview(upc) {

    //}

    public static void main(String[] args) throws Exception {
        ebayGrabber driver = new ebayGrabber();
        String tag = "Velo binding machine";
        driver.run(java.net.URLEncoder.encode(tag, "UTF-8"));

    }
}
