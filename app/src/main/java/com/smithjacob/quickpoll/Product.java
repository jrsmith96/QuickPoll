package com.smithjacob.quickpoll; /**
 * Created by Jacob on 2/27/2017.
 */

import android.media.Image;

public class Product {
    private String _Name = null;
    private String _Description = null;
    private Image _image = null;

    public Product(String nm, String desc, Image img){
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

    public Image get_Image(){
        //_image
        return _image;
    }
    public void set_Image(Image img){
        _image = img;
    }
}
