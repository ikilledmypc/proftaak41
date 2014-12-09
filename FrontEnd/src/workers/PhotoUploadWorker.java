/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workers;

import Controller.HttpController;
import com.google.gson.Gson;
import domain.Account;
import domain.Photo;
import domain.PhotoGroup;
import frontend.FrontEnd;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Random;
import javafx.concurrent.Task;
import javafx.scene.control.TextField;
import javax.imageio.ImageIO;

/**
 *
 * @author tim
 */
public class PhotoUploadWorker extends Task {

    private String groupName;
    private HashMap<File, TextField> selectedPhotos = new HashMap<>();
    private Account account;

    public PhotoUploadWorker(String groupname, HashMap<File, TextField> selectedPhotos, Account account) {
        this.groupName = groupname;
        this.selectedPhotos = selectedPhotos;
        this.account = account;
    }

    @Override
    protected Object call() throws Exception {
        this.updateProgress(0, selectedPhotos.size());
        Gson gson = new Gson();
        String photogroupid = "";
        String code = generateCode();
        photogroupid = HttpController.excutePost(FrontEnd.HOST + "/createPhotoGroup", "photogroup="
                + gson.toJson(new PhotoGroup(this.account.getAccountID(), code, groupName, true, 0)));
        //groupCode.setText(code);
        int progress = 0;

        for (Map.Entry pairs : selectedPhotos.entrySet()) {
            File p = ((File) pairs.getKey());
            BufferedImage image = ImageIO.read(p);
            Photo photo = new Photo(p.getName(), new GregorianCalendar(), Float.parseFloat(((TextField) pairs.getValue()).getText()), image.getHeight(), image.getWidth());

            String photoid = HttpController.excutePost(FrontEnd.HOST + "/uploadGroupPhoto", "photo=" + gson.toJson(photo) + "&photogroupID=" + photogroupid);
            photoid = photoid.trim();
            String bla = HttpController.postFile("http://localhost:8080/upload", p.getPath(), Integer.parseInt(photoid));
            progress ++;
            this.updateProgress(progress, selectedPhotos.size());
        }
        return code;
    }

    public String generateCode() {
        Random rand = new Random();
        int code = rand.nextInt(899999) + 100000;
        String hashcode = Integer.toHexString(code);
        String bezet = HttpController.excuteGet(FrontEnd.HOST + "/checkCodeavailability?hashcode=" + hashcode);
        if (bezet.equals("true")) {
            generateCode();
        } else {
            return hashcode;
        }
        return "";
    }

}
