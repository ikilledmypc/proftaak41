package managers;

import com.google.gson.Gson;

public class JsonManager {
	private static JsonManager manager= null;
	private Gson gson;
	
	private JsonManager(){
		gson = new Gson();
	}
	
	public static JsonManager GetInstance(){
		if(manager == null){
			manager = new JsonManager();
			return manager;
		}
		return manager;
	}
	
	public Object fromJson(String jsonstring,Class javaclass){
		return gson.fromJson(jsonstring, javaclass);
	}
	
	public String toJson(Object object){
		return gson.toJson(object);
	}
}
