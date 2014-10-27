package controllers;

import interfaces.IDatabase;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import domain.Photo;

@Controller
public class FileUploadController {

    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(
            @RequestParam("file") MultipartFile file){
    	String name = "Test";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
    
    @RequestMapping(value = "/createPhotoGroup", method = RequestMethod.PUT)
	public String createPhotoGroup(@RequestParam(value = "accountID", required = true)int accountID,
			@RequestParam(value = "code", required = true)int code, @RequestParam(value = "groupName", required = true)String groupName,
			@RequestParam(value = "isPublic", required = true) boolean isPublic){
		IDatabase db = DatabaseController.getInstance();
		int groupcode = 0;
		ResultSet rst = db.insert("INSERT INTO PHOTOGORUP (accountID, code, groupName, isPublic)VALUES('" + accountID + "','" + code + "'"
				+ ",'" + groupName + "', '" + isPublic + "')");
		try {
			while(rst.next()){
				groupcode = rst.getInt(0);
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
					photoID = rst.getInt(0);
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
