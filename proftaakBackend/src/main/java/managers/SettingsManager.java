package managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import domain.Account;

public class SettingsManager {

	public void loadOrCreate() {
		String rootPath = System.getProperty("user.dir")+File.separator+"config"+File.separator;
		new File(rootPath).mkdirs();
		Properties rootprops =new Properties();
		Properties userprops = new Properties();
		try {
			rootprops.load(this.getClass().getClassLoader()
					.getResourceAsStream("application.properties"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		File props = new File(rootPath+"application.properties");
		if (!props.exists()) {
		try {
				props.createNewFile();
				for(Entry<Object, Object> entry : rootprops.entrySet()){
					userprops.setProperty((String) entry.getKey(),(String)entry.getValue());
				}
				userprops.store(new FileOutputStream(props), "---------Edit for custom settings--------");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
