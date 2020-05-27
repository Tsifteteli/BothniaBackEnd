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
public class Picture {
    
    private int piNo;
    private String locationOnDisc;
    private String fName;
    private String lName;
    private String oportunityDescription;
    private String photoDescription;
    private String datetime;
    private String fileFormat;
    private String width;
    private String height;
    private String fileSize;
    private String resolution;
    private String camera;
    private String coordinates;
    private int original;
    private String price;
    private int maxUse;
    private int used;
    private String aquiredThrough;
    private Publishing[] publishings;
    private String[] keywords;

    
    
    
    public Picture() { //testa att lägg in "?" på alla strängar - alla int automat-initieras på 0 - lägg in -1 istället? - alla Arrayer blir tomma arrayer
        this.piNo = -1;
        this.locationOnDisc = "?";
        this.fName = "?";
        this.lName = "?";
        this.oportunityDescription = "?";
        this.photoDescription = "?";
        this.datetime = "?";
        this.fileFormat = "?";
        this.width = "?";
        this.height = "?";
        this.fileSize = "?";
        this.resolution = "?";
        this.camera = "?";
        this.coordinates = "?";
        this.original = -1;
        this.price = "?";
        this.maxUse = -1;
        this.used = -1;
        this.aquiredThrough = "?";
        Publishing publishing = new Publishing(-1, "?", "?", "?", "?");
        Publishing[] initializerP = {publishing};
        this.publishings = initializerP;
//        this.publishings = new Publishing[0];
        String[] initializerS = {"?"};
        this.keywords = initializerS;
//        this.keywords = new String[0];
    }
    
    
    @Override
    public boolean equals(Object o) {
        
        if (o instanceof Picture) {

            Picture p = (Picture) o;
            
            if (this.piNo == p.getPiNo()) {
                return true;
            }
        }
        return false;
    }
    
    
    public void setPiNo(int piNo) {
        this.piNo = piNo;
    }
    
    
    public void setLocationOnDisc(String locationOnDisc) {
        this.locationOnDisc = locationOnDisc;
    }

    
    public void setfName(String fName) {
        this.fName = fName;
    }

    
    public void setlName(String lName) {
        this.lName = lName;
    }

    
    public void setOportunityDescription(String oportunityDescription) {
        this.oportunityDescription = oportunityDescription;
    }

    
    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
    }

    
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    
    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    
    public void setWidth(String width) {
        this.width = width;
    }

    
    public void setHeight(String height) {
        this.height = height;
    }

    
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    
    public void setCamera(String camera) {
        this.camera = camera;
    }

    
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    
    public void setOriginal(int original) {
        this.original = original;
    }

    
    public void setPrice(String price) {
        this.price = price;
    }

    
    public void setMaxUse(int maxUse) {
        this.maxUse = maxUse;
    }

    
    public void setUsed(int used) {
        this.used = used;
    }

    
    public void setAquiredThrough(String aquiredThrough) {
        this.aquiredThrough = aquiredThrough;
    }

    
    public void setPublishings(Publishing[] publishings) {
        this.publishings = publishings;
    }

    
    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    
    public int getPiNo() {
        return piNo;
    }

    
    public String getLocationOnDisc() {
        return locationOnDisc;
    }

    
    public String getfName() {
        return fName;
    }

    
    public String getlName() {
        return lName;
    }

    
    public String getOportunityDescription() {
        return oportunityDescription;
    }

    
    public String getPhotoDescription() {
        return photoDescription;
    }

    
    public String getDatetime() {
        return datetime;
    }

    
    public String getFileFormat() {
        return fileFormat;
    }

    
    public String getWidth() {
        return width;
    }

    
    public String getHeight() {
        return height;
    }

    
    public String getFileSize() {
        return fileSize;
    }

    
    public String getResolution() {
        return resolution;
    }

    
    public String getCamera() {
        return camera;
    }

    
    public String getCoordinates() {
        return coordinates;
    }

    
    public int getOriginal() {
        return original;
    }

    
    public String getPrice() {
        return price;
    }

    
    public int getMaxUse() {
        return maxUse;
    }

    
    public int getUsed() {
        return used;
    }

    
    public String getAquiredThrough() {
        return aquiredThrough;
    }

    
    public Publishing[] getPublishings() {
        return publishings;
    }

    
    public String[] getKeywords() {
        return keywords;
    }
    
    
}
