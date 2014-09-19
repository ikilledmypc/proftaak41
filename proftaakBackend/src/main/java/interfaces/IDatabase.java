package interfaces;

import java.sql.ResultSet;

public interface IDatabase {
	public ResultSet select(String s);
	public boolean insert(String s);
	public boolean delete(String s);
}
