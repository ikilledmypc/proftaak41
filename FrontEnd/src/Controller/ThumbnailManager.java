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

    public static File getThumnail(String photoname) throws IOException {
        FileOutputStream fos = null;
        try {
            String root = System.getProperty("user.dir");
            File thumbdir = new File(root + File.separator + "thumb");
            if (!thumbdir.exists()) {
                thumbdir.mkdirs();

            }
            File f = new File(thumbdir.getAbsolutePath()+ File.separator + photoname + ".jpg");
            if (f.exists() && !f.isDirectory()) {
                return f;
            }
            System.out.println("thumb not found downloading..");
            byte[] file = HttpController.excuteGetFile(FrontEnd.HOST + "/getThumbnail?filename=" + photoname + ".jpg");
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
