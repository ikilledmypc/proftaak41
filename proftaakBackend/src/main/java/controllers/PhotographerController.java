/**
 * 
 */
package controllers;

import interfaces.IDatabase;

import java.sql.SQLException;
import java.sql.ResultSet;

import managers.JsonManager;

import org.neo4j.cypher.internal.compiler.v2_1.ast.rewriters.isolateAggregation;
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
	@RequestMapping("/searchPhotographerActive")
	public boolean searchPhotographer(
			@RequestParam(value = "username", required = true) String username) {
		try {
			db = controllers.DatabaseController.getInstance();
			ResultSet rs = db
					.select("SELECT accountID FROM Account WHERE username='"
							+ username + "'");
			rs.next();
			ResultSet rs1 = db
					.select("SELECT isActive FROM Photographer WHERE accountID = '"
							+ rs.getInt("accountID") + "'");
			rs1.next();
			return rs1.getBoolean("isActive");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping(value = "/editPhotographerActive", method = RequestMethod.POST)
	public void editPhotographerActive(@RequestParam("photographer") String json) {
		jsonManager = JsonManager.GetInstance();
		System.out.println("editPhotographerActive");
		photographer = (Photographer) jsonManager.fromJson(json,
				Photographer.class);
		photographer.enableDisablePhotographer();
	}
}
