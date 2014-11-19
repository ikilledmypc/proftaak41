/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workers;

import Controller.ThumbnailManager;
import domain.Photo;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.concurrent.Task;

/**
 *
 * @author tim
 */
public class ThumbnailDownloadWorker extends Task{
    private ArrayList<Photo> photos;
    
    public ThumbnailDownloadWorker(ArrayList<Photo> photos){
        this.photos =photos;
    }
    
    @Override
    protected Object call() throws Exception {
        HashMap<Photo,File > files = new HashMap<>();
        this.updateProgress(0, photos.size());
        int progress =0;
        for(Photo p :this.photos){
            files.put(p,ThumbnailManager.getThumnail(p.getPhotoID()+""));
            progress++;
            updateProgress(progress, photos.size());
        }
        return files;
    }
    
}
