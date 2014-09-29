package controllers;

import interfaces.IDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Photo;

public class FotoController {
	
	@RequestMapping(value = "/getAllPhotos", method = RequestMethod.POST)
	public ArrayList<Photo> getAllPhotos(@RequestParam(value = "code", required = true) String code){
		ArrayList<Photo> photos = new ArrayList<Photo>();
		ArrayList<Integer> photogroupIDs = new ArrayList<Integer>();
		ArrayList<Integer> photoIds = new ArrayList<Integer>();
		Photo photo;
		IDatabase db = DatabaseController.getInstance();
		ResultSet rst = db.select("SELECT photogroupID FROM photoGroup WHERE code = '"+ code +"'");
		try {
			while(rst.next()){
				photogroupIDs.add(rst.getInt(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(int i : photogroupIDs){
			rst = db.select("SELECT photoID FROM Photogroup_Photo WHERE photogroupID = '" + i + "'");
			try {
				while(rst.next()){
					photoIds.add(rst.getInt(0));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		for(int y : photoIds){
			rst = db.select("select * from Photo where photoID = '" + y + "'");
			try {
				while(rst.next()){
					photo = new Photo(rst.getDate("uploadDate"), (float)rst.getDouble("price"));
					photo.setPhotoID(rst.getInt("photoID"));
					photos.add(photo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return photos;
	}
}
