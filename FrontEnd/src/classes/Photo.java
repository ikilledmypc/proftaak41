/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Baya
 */
public class Photo {
    public String code;
    public String name;
    public String size;
    
    public Photo(String code, String name, String size) {
        this.code = code;
        this.name = name;
        this.size = size;
    }
    
    public String getName() {
        return name;
    }
    
    public String getSize() {
        return size;
    }
}
