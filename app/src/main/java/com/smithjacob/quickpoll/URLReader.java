package com.smithjacob.quickpoll;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLReader {

    public static String read(String address) throws Exception{

        HttpURLConnection connection;
        //BufferedReader reader;
        //long totalBytes = 0;
        String response = "";
        //String result = null;

        try {
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            //connection.connect();

            //InputStream stream = connection.getInputStream();
            //Reader temp = new InputStreamReader(in);

            /*URL url = new URL("http://www.android.com/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                readStream(in);
            } finally {
                urlConnection.disconnect();
            }*/

            //reader = new BufferedReader(temp);
                    //reader = new BufferedReader(new InputStreamReader(stream));
            //StringBuilder builder = new StringBuilder();
            //int n = in.read();
            //StringBuilder builder = new StringBuilder();
            //String line = "";
            if( != null) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuffer temp = new StringBuffer();
                String currentLine = null;
                while ((currentLine = reader.readLine()) != null {
                    temp.append(currentLine);
                }
                response = temp.toString();
                in.close();
            }

            return response;
            //response = builder.toString();
        /*} catch(MalformedURLException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }*/
        //connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.8) Gecko/20100722 Firefox/3.6.8");
      //  return response;
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
