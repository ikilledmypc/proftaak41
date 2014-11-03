/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;

/**
 *
 * @author Mr. Jin
 */
public class HttpController {
    public static String excutePost(String targetURL, String urlParameters)
  {
    URL url;
    HttpURLConnection connection = null;  
    try {
      //Create connection
      url = new URL(targetURL);
      connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", 
           "application/x-www-form-urlencoded");
			
      connection.setRequestProperty("Content-Length", "" + 
               Integer.toString(urlParameters.getBytes().length));
      connection.setRequestProperty("Content-Language", "en-US");  
			
      connection.setUseCaches (false);
      connection.setDoInput(true);
      connection.setDoOutput(true);

      //Send request
      DataOutputStream wr = new DataOutputStream (
                  connection.getOutputStream ());
      wr.writeBytes (urlParameters);
      wr.flush ();
      wr.close ();

      //Get Response	
      InputStream is = connection.getInputStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
      String line;
      StringBuffer response = new StringBuffer(); 
      while((line = rd.readLine()) != null) {
        response.append(line);
        response.append('\r');
      }
      rd.close();
      return response.toString();

    } catch (Exception e) {

      e.printStackTrace();
      return null;

    } finally {

      if(connection != null) {
        connection.disconnect(); 
      }
    }
  }
    /*
  ...
The urlParameters is a URL encoded string.

 String urlParameters =
        "fName=" + URLEncoder.encode("???", "UTF-8") +
        "&lName=" + URLEncoder.encode("???", "UTF-8")
    */
    
    
    public static String excuteGet(String targetURL)
  {
    URL url;
    HttpURLConnection connection = null;  
    try {
      //Create connection
      url = new URL(targetURL);
      connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("GET");

      //Get Response	
      InputStream is = connection.getInputStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
      String line;
      StringBuffer response = new StringBuffer(); 
      while((line = rd.readLine()) != null) {
        response.append(line);
        response.append('\r');
      }
      rd.close();
      return response.toString();

    } catch (Exception e) {

      e.printStackTrace();
      return null;

    } finally {

      if(connection != null) {
        connection.disconnect(); 
      }
    }
  }
 
    public static String postFile(String targetURL, String filePath) 
    {
        String fileName = filePath.substring(filePath.lastIndexOf("\\")+1, filePath.length());
        System.out.println("Filename: " + fileName);
        
       HttpURLConnection conn = null;
          DataOutputStream dos = null;
          DataInputStream inStream = null;
          String lineEnd = "\r\n";
          String twoHyphens = "--";
          String boundary =  "*****";
          int bytesRead, bytesAvailable, bufferSize;
          byte[] buffer;
          int maxBufferSize = 1*1024*1024;
          try{
              FileInputStream fileInputStream = new FileInputStream(new File(filePath));
              URL url = new URL(targetURL);
              conn = (HttpURLConnection) url.openConnection();
              conn.setDoInput(true);
              conn.setDoOutput(true);
              conn.setUseCaches(false);
              conn.setRequestMethod("POST");
              conn.setRequestProperty("Connection", "Keep-Alive");
              conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
              dos = new DataOutputStream( conn.getOutputStream() );
             
              dos.writeBytes(twoHyphens + boundary + lineEnd);
              dos.writeBytes("Content-Disposition: form-data; name=\"file\";" + " filename=\"" + filePath +"\"" + lineEnd);
              //System.out.println("Content-Disposition: form-data; name=\"file\";" + " filename=\"" + filePath +"\"" + lineEnd);
              //System.out.println("Content-Disposition: form-data; name=\"" + fileName +  "\";" + " filename=\"" + filePath +"\"" + lineEnd);
              //dos.writeBytes();
              dos.writeBytes(lineEnd);
              // create a buffer of maximum size
              bytesAvailable = fileInputStream.available();
              bufferSize = Math.min(bytesAvailable, maxBufferSize);
              buffer = new byte[bufferSize];
              // read file and write it into form...
              bytesRead = fileInputStream.read(buffer, 0, bufferSize);
           while (bytesRead > 0){
               dos.write(buffer, 0, bufferSize);
               bytesAvailable = fileInputStream.available();
               bufferSize = Math.min(bytesAvailable, maxBufferSize);
               bytesRead = fileInputStream.read(buffer, 0, bufferSize);
               //SimpleLog.write(filePath + " uploaded to " + targetURL);
           }
           // send multipart form data necesssary after file data...
           dos.writeBytes(lineEnd);
           dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
           // close streams
           fileInputStream.close();
           dos.flush();
           dos.close();
           System.out.println(filePath + " uploaded to from " + targetURL);
          }catch (MalformedURLException ex){
              System.out.println("From ServletCom CLIENT REQUEST:"+ex);
          }catch (IOException ioe){
              System.out.println("From ServletCom CLIENT REQUEST:"+ioe);
          }
          //------------------ read the SERVER RESPONSE
          try {
              InputStream is = conn.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer(); 
                while((line = rd.readLine()) != null) {
                  response.append(line);
                  response.append('\r');
                }
                rd.close();
                return response.toString();

              } catch (Exception e) {

                e.printStackTrace();
                return null;

              } finally {

                if(conn != null) {
                  conn.disconnect(); 
                }
              }
    }
}
