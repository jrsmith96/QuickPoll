package com.smithjacob.quickpoll;


public class Review {
	public String _customerReview;
	public double _customerRating;
	public int _helpfulness;
	public String _url = null;
	
	public Review(String cr, double rating, int help, String url){
		_customerReview = cr;
		_customerRating = rating;
		_helpfulness = help;
		_url = url;
	}
	public String get_CustomerReview(){
		return _customerReview;
	}
	public double get_CustomerRating(){
		return _customerRating;
	}	
	public int get_Helpfulness(){
		return _helpfulness;
	}
	public String get_url(){
		return _url;
	}
}
