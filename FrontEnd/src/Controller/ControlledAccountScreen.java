/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import domain.Account;

/**
 *
 * @author Tim
 */
public abstract class ControlledAccountScreen implements ControlledScreen {

    /**
     * login account
     */
    protected Account loggedInAccount;

    /**
     * parent screencontroller
     */
    protected ScreensController parent;
    
    /**
     * screen
     * @param screenPage screen page
     */
    @Override
    public void setScreenParent(ScreensController screenPage){
        this.parent =screenPage;
    }
    
    /**
     * set account
     * @param a account
     */
    public void setAccount(Account a){
        loggedInAccount = a;
    }
    
}
