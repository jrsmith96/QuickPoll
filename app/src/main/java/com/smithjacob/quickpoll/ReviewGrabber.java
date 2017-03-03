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
	public double getPrice(){
		return _price;
	}
	
	public List<Review> getReviews(){
		return _reviews;
	}

	
	protected Product _product = ReviewManager.getProduct();
	protected double _rating;
	protected double _price;
	protected double _numRating;
}
