/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import domain.Account;

/**
 *
 * @author Mike Rooijackers
 */
public abstract class ControlledAccountScreen implements ControlledScreen {

    /**
     *
     */
    protected Account loggedInAccount;

    /**
     *
     */
    protected ScreensController parent;
    
    /**
     *
     * @param screenPage
     */
    @Override
    public void setScreenParent(ScreensController screenPage){
        this.parent =screenPage;
    };
    
    /**
     *
     * @param a
     */
    public void setAccount(Account a){
        loggedInAccount = a;
    }
    
}
