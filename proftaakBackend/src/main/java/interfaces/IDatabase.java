package interfaces;

import java.sql.ResultSet;

public interface IDatabase {
	/**
	 * Select statement
	 * @param s String
	 * @return ResultSet
	 */
	public ResultSet select(String s);

	/**
	 * Insert statement
	 * @param s String
	 * @return boolean
	 */
	public boolean insert(String s);

	/**
	 * Delete statement
	 * @param s String
	 * @return boolean
	 */
	public boolean delete(String s);
	
	/**
	 * Update statement
	 * @param s String
	 * @return boolean
	 */
	public boolean update(String s);
}
