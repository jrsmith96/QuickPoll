package com.smithjacob.quickpoll;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class fakegrabber extends ReviewGrabber{

    public fakegrabber() {
    }

    public String collectReviews(int upc){
        //com.smithjacob.quickpoll.Product
        if(_product.get_Name() == null)
            _product.set_Name("Ipad");
        if(_product.get_Description()==null)
            _product.set_Description("By Apple");
        if(_product.get_Image()==null){
            BufferedImage img = null;
            try {
                URL url = new URL("https://support.apple.com/library/content/dam/edam/applecare/images/en_US/ipad/ipadpro/identify-ipad-pro.jpg");
                img = ImageIO.read(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            _product.set_Image(img);
        }
        _price = 988.0;
        _rating = 8.9;

        //reviews
        int flag=1;
        while(flag == 1){ //looping for adding reviews
            Review a = new Review("Nice", 8.88, 2, "www.google.com");
            Review b = new Review("OK", 5.0, 4, "www.facebook.com");
            _reviews.add(a);
            _reviews.add(b);
            _numRating = 2;
            flag = 0;
        }
        return "Success";
    }

}
