/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import frontend.FrontEnd;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tim
 */
public class ThumbnailManager {
    public static File getThumnail(String photoname) throws IOException{
        FileOutputStream fos = null;
        try {
            File f = new File("thumb"+File.separator+photoname);
            if(f.exists() && !f.isDirectory()) { 
                
                return f;
            } 
            System.out.println("thumb not found downloading..");
            byte[] file =  HttpController.excuteGetFile(FrontEnd.HOST + "/getThumbnail?filename="+photoname);            
            fos = new FileOutputStream(f);
            fos.write(file);
            fos.close();
            return f;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ThumbnailManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
        
    }
    
}
