/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bothniabackend.model;

/**
 *
 * @author Caroline
 */
public class Publishing {
    
    private int puNo;
    private String date;
    private String title;
    private String fName;
    private String lName;

    
    
    
    public Publishing(int puNo, String date, String title, String fName, String lName) {
        this.puNo = puNo;
        this.date = date;
        this.title = title;
        this.fName = fName;
        this.lName = lName;
    }

    
    public int getPuNo() {
        return puNo;
    }

    
    public String getDate() {
        return date;
    }

    
    public String getTitle() {
        return title;
    }

    
    public String getfName() {
        return fName;
    }

    
    public String getlName() {
        return lName;
    }
    
    
}
