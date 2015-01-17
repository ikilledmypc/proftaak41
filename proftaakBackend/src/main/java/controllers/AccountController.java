package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import domain.Account;
import domain.Photo;
import domain.PhotoGroup;
import domain.Photographer;

@RestController
public class AccountController {
	private HashMap<String, Account> loggedInUsers = new HashMap<>();

	@RequestMapping("/authenticateAndGet")
	public String authenticateAndGet(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {
		Account a = Photographer.authenticate(username, password);
		if (a != null) {
			a.setClaimedPhotos(getRedeemed(a.getAccountID()));
			loggedInUsers.put(username, a);
		}
		Gson gson = new Gson();
		return gson.toJson(a);
	}

	@RequestMapping(value = "/getPreviousRedeemed", method = RequestMethod.GET)
	public String getPreviousRedeemed(@RequestParam(value="accountID", required=true)int accountID){
		ArrayList<PhotoGroup> groups = this.getRedeemed(accountID);
		Gson gson = new Gson();
		return gson.toJson(groups);
	}
	
	private ArrayList<PhotoGroup> getRedeemed(int accountID){
		ArrayList<PhotoGroup> groups = new ArrayList<>();
		DatabaseController db = DatabaseController.getInstance();
		ResultSet rs = db.select("SELECT `groupName`,`p`.`photoID`, `name`, `uploadDate`, `price`, `size`, `height`, `width`  "
				+ "FROM `photogroup` pg, `photo` p, `photogroup_photo` pgp,`code_redeemed_account` cra "
				+ "WHERE cra.photogroupid = pg.photogroupid and pg.photogroupid = pgp.photogroupid and pgp.photoid = p.photoid and cra.accountid = "+accountID);
		PhotoGroup pg = new PhotoGroup("");
		try {
			while (rs.next()){
				if(!rs.getString("groupName").equals(pg.getGroupName())){
					pg = new PhotoGroup(rs.getString("groupName"));
					groups.add(pg);
				}
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.setTimeInMillis(rs.getTimestamp("uploadDate").getTime());
				pg.addPhoto(new Photo(rs.getInt("photoID"),rs.getString("name"),calendar, rs.getFloat("price"), rs.getInt("height"), rs.getInt("width")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.closeConnection();
		return groups;
	}

	public Account IsLoggedIn(String username) {
		return loggedInUsers.get(username);
	}

}
