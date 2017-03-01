package com.smithjacob.quickpoll;

import java.util.*;

public class ReviewGrabber{


    protected List<Review> _reviews = new ArrayList<Review>();


    public ReviewGrabber(){
    }

    public String collectReviews(int upc){
        if(_reviews==null)
            return "collectReviews() false";
        return "collectReviews() success";
    }

    public double getAvgRating(){
        return _rating;
    }

    double getPrice() throws Exception {
        return EbayGrabber.retrievePrice();
    }

    public List<Review> getReviews(){
        return _reviews;
    }


    protected Product _product = ReviewManager._product;
    protected static int _upc;
    protected double _rating;
    protected double _price;
    protected double _numRating;
}