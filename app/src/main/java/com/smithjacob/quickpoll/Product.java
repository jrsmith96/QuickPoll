package com.smithjacob.quickpoll;


public class Product{
	private String _Name = null; 
	private String _Description = null;
	private String _image = null;
	
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


/*
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

Image image = null;
try {
    URL url = new URL("https://images-na.ssl-images-amazon.com/images/I/51E5bmuUTrL._SY450_.jpg ");
    image = ImageIO.read(url);
} catch (IOException e) {
	e.printStackTrace();
}

JFrame frame = new JFrame();
frame.setSize(300, 300);
JLabel label = new JLabel(new ImageIcon(image));
frame.add(label);
frame.setVisible(true);
*/