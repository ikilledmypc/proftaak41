package controllers;

import interfaces.IDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import domain.Photo;

@RestController
public class FotoController {
	
	@RequestMapping(value = "/getAllPhotos", method = RequestMethod.GET)
	public String getAllPhotos(@RequestParam(value = "code", required = true) String code,@RequestParam(value="accountID", required=true)int accountID){
		ArrayList<Photo> photos = new ArrayList<Photo>();
		ArrayList<Integer> photogroupIDs = new ArrayList<Integer>();
		ArrayList<Integer> photoIds = new ArrayList<Integer>();
		Photo photo;
		Date newDate;
		IDatabase db = DatabaseController.getInstance();
		ResultSet rst = db.select("SELECT photogroupID FROM photoGroup WHERE code = '"+ code +"'");
		try {
			while(rst.next()){
				db.insert("insert into code_redeemed_account (accountID,photogroupID) VALUES ('"+accountID +"','"+rst.getInt("photogroupID") +"')");
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
					long millisecs = rst.getTimestamp("uploadDate").getTime() + (rst.getTimestamp("uploadDate").getNanos() / 1000000);
					newDate = new Date(millisecs);
					photo = new Photo(newDate, (float)rst.getDouble("price"));
					photo.setPhotoID(rst.getInt("photoID"));
					photos.add(photo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Gson gson = new Gson();
		return gson.toJson(photos);
	}
}
