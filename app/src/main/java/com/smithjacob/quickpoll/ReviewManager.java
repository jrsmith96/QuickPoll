package com.smithjacob.quickpoll;

import java.util.*;

public class ReviewManager{

    private List<ReviewGrabber> reviewGrabbers = new ArrayList<ReviewGrabber>();
    public List<Double> Prices = new ArrayList<Double>();
    public List<Double> Ratings = new ArrayList<Double>();
    public static Product _product = new Product(null, null, null);

    public ReviewManager(int upc){
        _upc = upc;
    }

    public String grabReviews(int size){
        //boolean flag = true;
        for(int i = 0; i < size; i++){
            ReviewGrabber temp = new EbayGrabber(); // Ask Letscher
            temp.collectReviews(_upc);
            reviewGrabbers.add(temp);
            Ratings.add(temp.getAvgRating());
            try {
                Prices.add(temp.getPrice());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "grab Reviews success";
    }

    public double getAvgRating(){
        double ar = 0.0;
        double tempr;
        for(int i = 0; i < reviewGrabbers.size(); i++){
            tempr = Ratings.get(i);
            //System.out.println(tempr);
            ar += tempr;
        }
        ar = ar/(double)reviewGrabbers.size();
        ar = Math.round(ar*10.0)/10.0;
        return ar;
    }

    public List<Review> getReviews(int i) {
        List<Review> tempR = reviewGrabbers.get(i).getReviews();
        return tempR;
    }

    public List<Double> getPrices(){
        return Prices;
    }


    public String getProduct(){
        if(_product.get_Name()!=null && _product.get_Description()!=null)
            return "getProduct() success, at least has a name and des";
        return "getProduct fail by not having a name or a des";
    }

    public void processReviews(){
        //
    }

    private static int _upc;
}
