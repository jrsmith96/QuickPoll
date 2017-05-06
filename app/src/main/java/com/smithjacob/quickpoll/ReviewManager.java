package com.smithjacob.quickpoll;

import java.util.*;

public class ReviewManager{
	
	public ReviewManager(String upc){
		reviewGrabbers = new ArrayList<ReviewGrabber>();
		Prices = new ArrayList<Double>();
		Ratings = new ArrayList<Double>();
		ItemURLs = new ArrayList<String>();
		_product = new Product(null, null, null);
		_upc = upc;
		
		//Grabbers!
		ReviewGrabber fake = new fakegrabber();
		//reviewGrabbers.add(fake);
		ReviewGrabber ebay = new ebayGrabber();
		reviewGrabbers.add(ebay);
		ReviewGrabber walmart = new walmartGrabber();
		reviewGrabbers.add(walmart);

	}
	
	public String grabReviews(){
		Ratings.clear();
		Prices.clear();
		ItemURLs.clear();
		for(int i = 0; i < reviewGrabbers.size(); i++){
			ReviewGrabber temp = reviewGrabbers.get(i);
			temp.collectReviews(_upc);
			Ratings.add(temp.getAvgRating());
			Prices.add(temp.getPrice());
			ItemURLs.add(temp.getItemURL());
			
			//products
			if(_product.get_Name() == null){
				if(reviewGrabbers.get(i).returnProduct().get_Name()!=null){
					_product.set_Name(reviewGrabbers.get(i).returnProduct().get_Name());
				}		
			}
			if(_product.get_Description() == null){
				if(reviewGrabbers.get(i).returnProduct().get_Description()!=null){
					_product.set_Description(reviewGrabbers.get(i).returnProduct().get_Description());
				}		
			}
			if(_product.get_Image() == null){
				if(reviewGrabbers.get(i).returnProduct().get_Image()!=null){
					_product.set_Image(reviewGrabbers.get(i).returnProduct().get_Image());
				}		
			}
		}
		return "grab Reviews success";
	}
	
	public double getAvgRating(){
		double ar = 0.0;
		double tempr;
		double s = 1.0*reviewGrabbers.size();
		for(int i = 0; i < reviewGrabbers.size(); i++){
			tempr = Ratings.get(i);
			if(tempr==-1)
				s--;
			//System.out.println(tempr);
			else
				ar += tempr;
		}
		ar = ar/s;
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
	
	public List<Double> getRatings(){
		return Ratings;
	}

	public List<String> getItemURLs() {
		return ItemURLs;
	}
	
	public String checkProduct(){
		if(_product.get_Name()!=null || _product.get_Description()!=null)
			return "checkProduct() success, at least has a name and des";
		return "checkProduct fail by not having a name or a des";
	}
	
	public Product getProduct(){
		return _product;
	}

	private String _upc;
	private List<ReviewGrabber> reviewGrabbers;
	private List<Double> Prices;
	private List<Double> Ratings;
	private Product _product;
	private List<String> ItemURLs;
}

