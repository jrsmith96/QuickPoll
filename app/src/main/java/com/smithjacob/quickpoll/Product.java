package com.smithjacob.quickpoll;

public class Product{
	private String _Name; 
	private String _Description;
	private String _image;
	
	public Product(String nm, String desc, String img){
		_Name = nm; 
		_Description = desc;
		_image = img;
	}
	public String get_Name(){ 
		return _Name; 
	}
	
	public void set_Name(String n){
		_Name = n;
	}
	
	
	public String get_Description(){ 
		return _Description; 
	}
	public void set_Description(String d){
		_Description = d;
	}
	
	public String get_Image(){
		//_image
        return _image;
    }
	public void set_Image(String img){
		_image = img;
	}
}
