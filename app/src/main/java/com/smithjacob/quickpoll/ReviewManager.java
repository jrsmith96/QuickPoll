package com.smithjacob.quickpoll;

import java.util.*;

public final class ReviewManager{
	
	private static List<ReviewGrabber> reviewGrabbers = new ArrayList<ReviewGrabber>();
	private static List<Double> Prices = new ArrayList<Double>();
	private static List<Double> Ratings = new ArrayList<Double>();
	private static Product _product = new Product(null, null, null);
	
	public ReviewManager(int upc){
		_upc = upc;
		ReviewGrabber fake = new fakegrabber();
		reviewGrabbers.add(fake);
	}
	public static String grabReviews(){
		Ratings.clear();
		Prices.clear();
		for(int i = 0; i < reviewGrabbers.size(); i++){
			ReviewGrabber temp = reviewGrabbers.get(i);
			temp.collectReviews(_upc);
			Ratings.add(temp.getAvgRating());
			Prices.add(temp.getPrice());
		}
		return "grab Reviews success";
	}
	
	public static double getAvgRating(){
		double ar = 0.0;
		double tempr;
		double s = 1.0*reviewGrabbers.size();
		for(int i = 0; i < reviewGrabbers.size(); i++){
			tempr = Ratings.get(i);
			//System.out.println(tempr);
			ar += tempr;
		}
		ar = ar/s;
		ar = Math.round(ar*10.0)/10.0;
		return ar;
	}

	public static String getName() {
		String s = reviewGrabbers
	}
	
	public static List<Review> getReviews(int i) {
		List<Review> tempR = reviewGrabbers.get(i).getReviews();
		return tempR;
	}
	
	public static List<Double> getPrices(){
		return Prices;
	}
	
	public static List<Double> getRatings(){
		return Ratings;
	}
	
	
	public static String checkProduct(){
		if(_product.get_Name()!=null && _product.get_Description()!=null)
			return "checkProduct() success, at least has a name and des";
		return "checkProduct fail by not having a name or a des";
	}
	
	public static Product getProduct(){
		return _product;
	}
	
	public void processReviews(){
		//
	}

	private static int _upc;
}

