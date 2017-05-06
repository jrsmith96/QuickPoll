package com.smithjacob.quickpoll;

import java.util.*;

public class ReviewGrabber{
	
	public ReviewGrabber(){
		_reviews = new ArrayList<Review>();
		_product = new Product(null, null, null);
		_rating = -1;
		_price = -1;
		_itemURL = null;
	}
	
	public String collectReviews(String upc){
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

	public String getItemURL(){
		return _itemURL;
	}
	
	public Product returnProduct(){
		return _product;
	}
	
	public List<Review> getReviews(){
		return _reviews;
	}

	protected List<Review> _reviews;
	protected Product _product;
	protected double _rating;
	protected double _price;
	protected double _numRating;
	protected String _itemURL;
}
