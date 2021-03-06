/**
 * 
 */
package controllers;

import interfaces.IDatabase;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import managers.JsonManager;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import domain.Photographer;

/**
 * @author Mike
 *
 */
@RestController
public class PhotographerController {

	private IDatabase db;
	private JsonManager jsonManager;
	private Photographer photographer;

	/**
	 * Search photographer Active
	 * 
	 * @param username
	 * @return true or false; default false
	 */
	@RequestMapping("/searchPhotographer")
	public Photographer searchPhotographer(
			@RequestParam(value = "username", required = true) String username) {
		try {
			db = controllers.DatabaseController.getInstance();
			ResultSet rs = db
					.select("SELECT accountID FROM Account WHERE username='"
							+ username + "'");
			rs.next();
			ResultSet rs1 = db
					.select("SELECT "
							+ "Account.accountID, username, name, address, zipcode, city, email, telephone, companyname, bankaccount, isActive "
							+ "FROM Account "
							+ "JOIN Photographer "
							+ "ON Account.accountID = Photographer.accountID "
							+ "WHERE Account.accountID = '"
							+ rs.getInt("accountID") + "'");
			rs1.next();
			photographer = new Photographer(rs1.getString("username"),rs1.getString("name"), rs1.getString("address"), rs1.getString("zipcode"),rs1.getString("city"),rs1.getString("email"), rs1.getString("telephone"), rs1.getString("companyname"), rs1.getString("bankaccount"), rs1.getBoolean("isActive"));
			return photographer;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/editPhotographer", method = RequestMethod.POST)
	public void editPhotographer(@RequestParam("photographer") String json) {
		jsonManager = JsonManager.GetInstance();
		photographer = (Photographer) jsonManager.fromJson(json, Photographer.class);
		photographer.editPhotographer();
	}
	
	@RequestMapping("/buildPieChartPhotographersInActive")
	public String buildPieChartPhotographersInActive() {
		try {
			String dataPieChart;
			db = controllers.DatabaseController.getInstance();
			ResultSet rs = db.select("SELECT count(accountID) FROM Photographer WHERE isActive = 0");
			rs.next();
			dataPieChart = rs.getString(1);
			return dataPieChart;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/buildPieChartPhotographersActive")
	public String buildPieChartPhotographersActive() {
		try {
			String dataPieChart;
			db = controllers.DatabaseController.getInstance();
			ResultSet rs = db.select("SELECT count(accountID) FROM Photographer WHERE isActive = 1");
			rs.next();
			dataPieChart = rs.getString(1);
			return dataPieChart;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/editDefaultPricePhoto", method = RequestMethod.POST)
	public void editDefaultPricePhoto(@RequestParam("accountID") int accountID, @RequestParam("defaultPricePhoto") double defaultPricePhoto) {
		db = controllers.DatabaseController.getInstance();
		db.update("UPDATE photographer SET defaultPricePhoto = '" + defaultPricePhoto + "' WHERE accountID ='" + accountID +"'");
	}
	
	@RequestMapping(value = "/getDefaultPricePhoto", method = RequestMethod.GET)
	public double getDefaultPricePhoto(@RequestParam("accountID") int accountID) {
		db = controllers.DatabaseController.getInstance();
		ResultSet rs = db.select("SELECT defaultPricePhoto FROM photographer WHERE accountID = '" + accountID + "'");
		try {
			rs.next();
			return rs.getDouble("defaultPricePhoto");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
