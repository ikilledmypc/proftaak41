package controllers;

import interfaces.IDatabase;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import managers.JsonManager;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import domain.Account;
import domain.Photo;
import domain.Photogroup;

@RestController
public class FileUploadController {

    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(
            @RequestParam("file") MultipartFile file){
    	String path = file.getOriginalFilename().toString();
    	String name = path.substring(path.lastIndexOf("\\")+1, path.length());
    	if (!file.isEmpty()) {
            try {          	
                byte[] bytes = file.getBytes();
                BufferedImage thumbnail =  Scalr.resize(ImageIO.read(multipartToFile(file)), 300);
                
             // Creating the directory to store file
                String rootPath = System.getProperty("user.dir");
                File dir = new File(rootPath + File.separator + "Photos"+File.separator+"thumbnails");
                if (!dir.exists()){
                    dir.mkdirs();

                }
                	
                
             // Create the file on server
                File serverFile = new File(rootPath + File.separator + "Photos" + File.separator + name);
                
                BufferedOutputStream stream =
                		new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                ImageIO.write(thumbnail, "jpg", new File(dir.getAbsolutePath()+ File.separator + name));
                return "You successfully uploaded " + name + " into " + serverFile.getAbsolutePath() + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
    
    public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
    {
            File convFile = new File( multipart.getOriginalFilename());
            multipart.transferTo(convFile);
            return convFile;
    }
    
    private BufferedImage scaleImage(BufferedImage source,double ratio) {
    	  int w = (int) (source.getWidth() * ratio);
    	  int h = (int) (source.getHeight() * ratio);
    	  BufferedImage bi = getCompatibleImage(w, h);
    	  Graphics2D g2d = bi.createGraphics();
    	  double xScale = (double) w / source.getWidth();
    	  double yScale = (double) h / source.getHeight();
    	  AffineTransform at = AffineTransform.getScaleInstance(xScale,yScale);
    	  g2d.drawRenderedImage(source, at);
    	  g2d.dispose();
    	  return bi;
    	}
    private BufferedImage getCompatibleImage(int w, int h) {
    	  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	  GraphicsDevice gd = ge.getDefaultScreenDevice();
    	  GraphicsConfiguration gc = gd.getDefaultConfiguration();
    	  BufferedImage image = gc.createCompatibleImage(w, h);
    	  return image;
    	}
    
    @RequestMapping(value="/uploadThumbnail", method=RequestMethod.POST)
    public @ResponseBody String handleThumbnailUpload(
            @RequestParam("file") MultipartFile file,
			@RequestParam(value = "photoID", required = true)int photoID){
    	String name = "Test";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                
             // Creating the directory to store file
                String rootPath = System.getProperty("default.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();
                
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(Integer.toString(photoID) + "-Thumbnail")));
                stream.write(bytes);
                stream.close();
                
                
                return "You successfully uploaded " + name + " into " + name + ".jpg !";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
    
    @RequestMapping(value = "/createPhotoGroup", method = RequestMethod.POST)
	public String createPhotoGroup(@RequestParam(value = "photogroup", required = true)String photogroupJson){
		IDatabase db = DatabaseController.getInstance();
		JsonManager jsonManager = JsonManager.GetInstance();
		Photogroup photogroup = (Photogroup) jsonManager.fromJson(photogroupJson, Photogroup.class);
		int groupcode = 0;
		int isPublic = 0;
		if(photogroup.getIsPublic()){
			isPublic = 1;
		}
		ResultSet rst = db.insert("INSERT INTO PHOTOGROUP (accountID, code, groupName, isPublic)VALUES('" + photogroup.getAccountID() + "','" + photogroup.getCode() + "'"
				+ ",'" + photogroup.getGroupName() + "', '" + isPublic + "')");
		try {
			while(rst.next()){
				groupcode = rst.getInt("code");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		return gson.toJson(groupcode);
	}
    
    @RequestMapping(value = "/checkCodeavailability", method = RequestMethod.GET)
	public String checkCodeavailability(@RequestParam(value = "hashcode", required = true)String hashcode){
		IDatabase db = DatabaseController.getInstance();
		String groupcode = "";
		ResultSet rst = db.select("SELECT code FROM PHOTOGROUP WHERE code = '" + hashcode + "'");
		try {
			while(rst.next()){
				groupcode = rst.getString("code");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(groupcode == ""){
			return "false";
		}
		return "true";
	}
    
    @RequestMapping(value = "/uploadGroupPhotos", method = RequestMethod.PUT)
	public String uploadGroupPhotos(@RequestParam(value = "photos", required = true)ArrayList<Photo> photos,
			@RequestParam(value = "photogroupID", required = true)int photogroupID){
		IDatabase db = DatabaseController.getInstance();
		int photoID = 0;
		for(Photo p : photos){
		    ResultSet rst = db.insert("INSERT INTO PHOTO (name, uploadDate, price) VALUES('" + p.getName() + "', '" + p.getUploadDate() + "'"
		    		+ ", '" + p.getPrice() + "')");
		    try {
				while(rst.next()){
					photoID = rst.getInt("photoid");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rst = db.insert("INSERT INTO PHOTOGROUP_PHOTO VALUES('" + photogroupID + "', '" + photoID + "')");
		}
		Gson gson = new Gson();
		return gson.toJson(true);
	}
}
