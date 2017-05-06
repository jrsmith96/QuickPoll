package com.smithjacob.quickpoll;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import static android.content.ContentValues.TAG;

public class ebayGrabber extends ReviewGrabber{

    public final static String EBAY_APP_ID = "JakeSmit-QuickPol-PRD-c2466ad44-ba7587d3";
    public final static String EBAY_FINDING_SERVICE_URI = "http://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME="
            + "{operation}&SERVICE-VERSION={version}&SECURITY-APPNAME="
            + "{applicationId}&GLOBAL-ID={globalId}&"
            + "keywords={UPC}"
            + "&paginationInput.entriesPerPage={maxresults}";
    public static final String SERVICE_VERSION = "1.13.0";
    public static final String OPERATION_NAME = "findItemsAdvanced";
    public static final String GLOBAL_ID = "EBAY-US";
    public final static int REQUEST_DELAY = 3000;
    public final static int MAX_RESULTS = 10;
    private int maxResults;
    private String title;
    private String galleryUrl;
    private double currentPrice;
    private String itemUrl;

    public ebayGrabber() {
        this.maxResults = MAX_RESULTS;
        
    }

    public ebayGrabber(int maxResults) {
        this.maxResults = maxResults;
    }
    
    //*****
    public String collectReviews(String upc){
    	try {
			run(upc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
            Log.d(TAG, "aaaaaaaaa");
			return "Failed";
		}
        _product.set_Name(title);
        _product.set_Image(galleryUrl);
        _price= currentPrice;
        _itemURL = itemUrl;
        return "Success";
    }
    
    public void run(String tag) throws Exception {

        String address = createAddress(tag);
        String response = URLReader.read(address);
        //process xml dump returned from EBAY
        processResponse(response);
        //Honor rate limits - wait between results
        Thread.sleep(REQUEST_DELAY);
        
    }
    
    //*****
    private String createAddress(String tag) {

        //substitute token
        String address = ebayGrabber.EBAY_FINDING_SERVICE_URI;
        address = address.replace("{version}", ebayGrabber.SERVICE_VERSION);
        address = address.replace("{operation}", ebayGrabber.OPERATION_NAME);
        address = address.replace("{globalId}", ebayGrabber.GLOBAL_ID);
        address = address.replace("{applicationId}", ebayGrabber.EBAY_APP_ID);
        address = address.replace("{UPC}", tag);
        address = address.replace("{maxresults}", "" + this.maxResults);

        return address;

    }

    private void processResponse(String response) throws Exception {

        XPath xpath = XPathFactory.newInstance().newXPath();
        InputStream is = new ByteArrayInputStream(response.getBytes("UTF-8"));
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = domFactory.newDocumentBuilder();


        Document doc = builder.parse(is);
        //XPathExpression ackExpression = xpath.compile("//findItemsAdvancedResponse/ack");
        XPathExpression itemExpression = xpath.compile("//findItemsAdvancedResponse/searchResult/item");

        NodeList nodes = (NodeList) itemExpression.evaluate(doc, XPathConstants.NODESET);

        //for (int i = 0; i < nodes.getLength(); i++) {

            Node node = nodes.item(0);

            //String itemId = (String) xpath.evaluate("itemId", node, XPathConstants.STRING);
            title = (String) xpath.evaluate("title", node, XPathConstants.STRING);

            itemUrl = (String) xpath.evaluate("viewItemURL", node, XPathConstants.STRING);
            galleryUrl = (String) xpath.evaluate("galleryURL", node, XPathConstants.STRING);

            String cp = (String) xpath.evaluate("sellingStatus/currentPrice", node, XPathConstants.STRING);
            currentPrice = Double.parseDouble(cp);

        is.close();

    }

}
