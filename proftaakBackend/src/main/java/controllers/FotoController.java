package controllers;

import interfaces.IDatabase;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import domain.Photo;
import domain.PhotoGroup;

@RestController
public class FotoController {
	
	@RequestMapping(value = "/getAllPhotos", method = RequestMethod.GET)
	public String getAllPhotos(@RequestParam(value = "code", required = true) String code,@RequestParam(value="accountID", required=true)int accountID){
		ArrayList<Photo> photos = new ArrayList<Photo>();
		ArrayList<Integer> photogroupIDs = new ArrayList<Integer>();
		ArrayList<Integer> photoIds = new ArrayList<Integer>();
		Photo photo;
		Calendar newDate;
		DatabaseController db = DatabaseController.getInstance();
		ResultSet rst = db.select("SELECT photogroupID FROM photoGroup WHERE code = '"+ code +"'");
		try {
			while(rst.next()){
				
				ResultSet codur = db.select("SELECT * FROM code_redeemed_account WHERE accountID="+accountID+" AND photogroupID="+rst.getInt("photogroupID") +"");
				if(!codur.next())
				{
					db.insert("insert into code_redeemed_account (accountID,photogroupID) VALUES ('"+accountID +"','"+rst.getInt("photogroupID") +"')");
				}
				photogroupIDs.add(rst.getInt("photogroupID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(int i : photogroupIDs){
			rst = db.select("SELECT photoID FROM Photogroup_Photo WHERE photogroupID = '" + i + "'");
			try {
				while(rst.next()){
					photoIds.add(rst.getInt("photoID"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		for(int y : photoIds){
			rst = db.select("select * from Photo where photoID = '" + y + "'");
			try {
				while(rst.next()){
					//long millisecs = rst.getTimestamp("uploadDate").getTime() + (rst.getTimestamp("uploadDate").getNanos() / 1000000);
					newDate = new GregorianCalendar();
					newDate.setTime(rst.getTimestamp("uploadDate"));

					photo = new Photo(rst.getString("name"), newDate, (float)rst.getDouble("price"), rst.getInt("height"), rst.getInt("width"));
					photo.setPhotoID(rst.getInt("photoID"));
					photos.add(photo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		db.closeConnection();
		Gson gson = new Gson();
		return gson.toJson(photos);
	}
	
	@RequestMapping(value = "/redeemCode", method = RequestMethod.POST)
	public void redeemCode(@RequestParam(value = "code", required = true) String code,@RequestParam(value="accountID", required=true)int accountID){
		DatabaseController db = DatabaseController.getInstance();
		db.insert("insert into code_redeemed_account (accountID,photogroupID) VALUES ('"+accountID +"',(SELECT photogroupid from photogroup where code='"+code+"'))");
		db.closeConnection();
	}
	
	@RequestMapping(value = "/getRedeemdGroups", method = RequestMethod.GET)
	public ArrayList<PhotoGroup> getRedeemdGroups(@RequestParam(value="accountid" ,required = true) String id){
		ArrayList<PhotoGroup> groups = new ArrayList<>();
		DatabaseController db = DatabaseController.getInstance();
		
		return groups;
	}
	
	
	@RequestMapping(value = "/getPreviousRedeemed", method = RequestMethod.GET)
	public String getPreviousRedeemed(@RequestParam(value="accountID", required=true)int accountID){
		ArrayList<Photo> photos = new ArrayList<Photo>();
		ArrayList<Integer> photogroupIDs = new ArrayList<Integer>();
		ArrayList<Integer> photoIds = new ArrayList<Integer>();
		Photo photo;
		//Date newDate;
		Calendar newDate;
		DatabaseController db = DatabaseController.getInstance();
		ResultSet rst = db.select("SELECT photogroupID FROM code_redeemed_account WHERE accountID = '"+ accountID +"'");
		try {
			while(rst.next()){
				photogroupIDs.add(rst.getInt("photogroupID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(int i : photogroupIDs){
			rst = db.select("SELECT photoID FROM Photogroup_Photo WHERE photogroupID = '" + i + "'");
			try {
				while(rst.next()){
					photoIds.add(rst.getInt("photoID"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		for(int y : photoIds){
			rst = db.select("select * from Photo where photoID = '" + y + "'");
			try {
				while(rst.next()){
					//long millisecs = rst.getTimestamp("uploadDate").getTime() + (rst.getTimestamp("uploadDate").getNanos() / 1000000);
					newDate = new GregorianCalendar();
					newDate.setTime(rst.getTimestamp("uploadDate"));
					photo = new Photo(rst.getString("name"), newDate, (float)rst.getDouble("price"), rst.getInt("height"), rst.getInt("width"));
					photo.setPhotoID(rst.getInt("photoID"));
					photos.add(photo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		db.closeConnection();
		Gson gson = new Gson();
		return gson.toJson(photos);
	}
	
	@RequestMapping("/getThumbnail")
	public ResponseEntity<byte[]> testphoto(@RequestParam(value="filename", required=true)String filename) throws IOException {
		String rootPath = System.getProperty("user.dir");
		String dir = rootPath + File.separator + "Photos"+File.separator+"thumbnails"+File.separator;
		//String dir = rootPath + File.separator + "Photos"+File.separator; FULL IMAGE INSTEAD OF THUMB 
	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG);
	    byte[] image = IOUtils.toByteArray(new FileInputStream(dir+filename));

	    return new ResponseEntity<byte[]>(image, headers, HttpStatus.CREATED);
	}
}
