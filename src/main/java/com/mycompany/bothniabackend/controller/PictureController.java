/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bothniabackend.controller;

import com.mycompany.bothniabackend.model.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Caroline
 */
public class PictureController {
    
    private static final String URL = "jdbc:mysql://localhost:3306/bothniabladet?zeroDateTimeBehavior=convertToNull&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "Caroline";
   
    private final String GET_PICTURES = 
        "SELECT p.piNo, p.locationOnDisc " +
        "FROM Picture p " +
        "JOIN PictureKeyword pk ON p.piNo = pk.piNo " +
        "JOIN Keyword k ON k.kwNo = pk.kwNo " +
        "WHERE k.word = ?;";
    private final String GET_PICTURE_INFO = 
        "SELECT p.piNo, p.locationOnDisc, ph.fName , ph.lName, po.description AS oportunityDescription," +
        " p.description AS photoDescription, p.datetime, p.fileFormat, " +
        "p.width, p.height, p.fileSize, p.resolution, po.camera, p.coordinates," +
        " p.original, p.price, p.maxUse, p.used, p.aquiredThrough " +
        "FROM Picture p " +
        "JOIN PhotoOpportunity po ON p.poNo = po.poNo " +
        "JOIN Photographer ph ON po.photographer = ph.phNo " +
        "WHERE p.piNo = ?;";
    private final String GET_PICTURE_PUBLISHING_INFO =
        "SELECT pu.puNo, pu.date, pu.title, e.fName, e.lName " +
        "FROM Picture p " +
        "JOIN PublishingPicture pp ON p.piNo = pp.picNo " +
        "JOIN Publishing pu ON pp.puNo = pu.puNo " +
        "JOIN Employee e ON pu.editor = e.eNo " +
        "WHERE p.piNo = ?;";
     private final String GET_PICTURE_KEYWORDS =
        "SELECT k.word " +
        "FROM PictureKeyword pk " +
        "JOIN Keyword k ON k.kwNo = pk.kwNo " +
        "WHERE pk.piNo = ?;";
    
    private final Picture picture;
    
    
    
    
    public PictureController() {
        this.picture = new Picture();
    }

    
    public Picture[] getPictures(String search) {
        
        String[] keywords = this.splitSearchString(search);
        ArrayList<Picture> picturesMatchingAllKeywords = 
                this.searchDBforPictures(keywords);//sök i db på varje keyword och spara resultatet i en Arraylist av Arraylists + spara totala antalet unika Picture-objekt i en total ArrayList
        Picture[] pictures = picturesMatchingAllKeywords.toArray(new Picture[0]); //konveretera slutgiltiga resultat-arraylisten till en Picture-Array
        
        return pictures; //returnera den Picture arrayen
    }
    
    
    public Picture getAllPictureInfo(int id) {
        
        this.getPictureInfo(id);
        this.getPublishings(id);
        this.getKeywords(id);

        return picture;
    }
    
    
    private String[] splitSearchString(String search) {
        
        String[] keywords;
        String delimiter = "_";
        
        keywords = search.split(delimiter);
        
        // print substrings
        for (int i = 0; i < keywords.length; i++)
            System.out.println(keywords[i]);
        
        return keywords;
    }
    
    
    private ArrayList<Picture> searchDBforPictures(String[] keywords) {
        
        ArrayList<ArrayList<Picture>> allQueryResults = new ArrayList<>();
        ArrayList<Picture> allUniquePictures = new ArrayList<>();
        
        try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement getPictures = con.prepareStatement(GET_PICTURES)) {
            
            for (String keyword : keywords) {
                
                System.out.println("The keyword is: " + keyword);
                
                getPictures.setString(1, keyword);
                ResultSet rs = getPictures.executeQuery();

                ArrayList<Picture> queryResult = new ArrayList<>();

                while(rs.next()) {

                    Picture picture = new Picture();
                    picture.setPiNo(rs.getInt("piNo"));
                    picture.setLocationOnDisc(rs.getString("locationOnDisc"));
                    
                    queryResult.add(picture);
                    
                    if (!allUniquePictures.contains(picture)) {
                        allUniquePictures.add(picture);
                        
                        System.out.println("picture No: " + picture.getPiNo() + " was added to the total");
                        
                    } else {
                        System.out.println("picture No: " + picture.getPiNo() + " was NOT added to the total. Does already exist in the list");
                    }
                }
                
                allQueryResults.add(queryResult);
            }
                    
        } catch(SQLException e) {
            System.out.println("An SQL Exception occurred: " + e.getMessage());
        }
        
        ArrayList<Picture> picturesMatchingAllKeywords = 
                this.getPicturesMatchingAllKeywords(allQueryResults, allUniquePictures);
        
        return picturesMatchingAllKeywords;
    }
    
    
    //Kolla vilka Pictures som förekommer i Alla sökresultats-arrayLists och gör en ny Arraylist av dem
    private ArrayList<Picture> getPicturesMatchingAllKeywords(ArrayList<ArrayList<Picture>> allQueryResults, ArrayList<Picture> allUniquePictures) {
        
        ArrayList<Picture> picturesMatchingAllKeywords = new ArrayList<>();
        
        for (Picture picture : allUniquePictures) { //För varje picture i allUniquePictures
            
            int keywordHits = 0;
            
            for (ArrayList<Picture> queryResult : allQueryResults) { //Kola en contains i varje Arraylist i allQueryResults
                if (queryResult.contains(picture)) { //if contains, then öka på sökordsträffar med +1
                    keywordHits++;
                }
                System.out.println("picture No: " + picture.getPiNo() + ", keywordHits: " + keywordHits);
            }
            if (keywordHits == allQueryResults.size()) { //if sökordsträffar = längden på allQueryResults => adda bilden till picturesMatchingAllKeywords
                picturesMatchingAllKeywords.add(picture);
                System.out.println("picture No: " + picture.getPiNo() + " was added to picturesMatchingAllKeywords");
            } else {
                System.out.println("picture No: " + picture.getPiNo() + " was NOT added to picturesMatchingAllKeywords");
            }
        }
        return picturesMatchingAllKeywords;
    }
    
    
    private void getPictureInfo(int id) {
        
        try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement getPictureInfo = con.prepareStatement(GET_PICTURE_INFO)) {
            
            getPictureInfo.setInt(1, id);
            ResultSet rs = getPictureInfo.executeQuery();
      
            while(rs.next()) {
                picture.setPiNo(rs.getInt("piNo"));
                picture.setLocationOnDisc(rs.getString("locationOnDisc"));
                picture.setfName(rs.getString("fName"));
                picture.setlName(rs.getString("lName"));
                picture.setOportunityDescription(rs.getString("oportunityDescription"));
                picture.setPhotoDescription(rs.getString("photoDescription"));
                picture.setDatetime(rs.getString("datetime"));
                picture.setFileFormat(rs.getString("fileFormat"));
                picture.setWidth(rs.getString("width"));
                picture.setHeight(rs.getString("height"));
                picture.setFileSize(rs.getString("fileSize"));
                picture.setResolution(rs.getString("resolution"));
                picture.setCamera(rs.getString("camera"));
                picture.setCoordinates(rs.getString("coordinates"));
                picture.setOriginal(rs.getInt("original"));
                picture.setPrice(rs.getString("price"));
                picture.setMaxUse(rs.getInt("maxUse"));
                picture.setUsed(rs.getInt("used"));
                picture.setAquiredThrough(rs.getString("aquiredThrough"));          
            } 
        } catch(SQLException e) {
            System.out.println("An SQL Exception occurred: " + e.getMessage());
        }
    }
    
    
    private void getPublishings(int id) { 
        
        try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement getPicturePublishingInfo = con.prepareStatement(GET_PICTURE_PUBLISHING_INFO)) {
            
            getPicturePublishingInfo.setInt(1, id);
            ResultSet rs = getPicturePublishingInfo.executeQuery();
            
            ArrayList<Publishing> publishings = new ArrayList<>();
      
            while(rs.next()) {
                publishings.add(new Publishing(rs.getInt("puNo"), rs.getString("date"),
                        rs.getString("title"), rs.getString("fName"), rs.getString("lName")));      
            }
            
//            //Ett sätt att konveretra araylist till array
//            Publishing[] publishingsArray = new Publishing[publishings.size()];
//
//            for(int index = 0; index < publishings.size(); index++) {
//                publishingsArray[index] = publishings.get(index);
//            }
            
            //Smidigare sätt att konveretra araylist till array
            Publishing[] publishingsArray = publishings.toArray(new Publishing[0]);

            picture.setPublishings(publishingsArray);
                    
        } catch(SQLException e) {
            System.out.println("An SQL Exception occurred: " + e.getMessage());
        }
    }
    
    
    private void getKeywords(int id) {
        
        try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement getPictureKeywords = con.prepareStatement(GET_PICTURE_KEYWORDS)) {
            
            getPictureKeywords.setInt(1, id);
            ResultSet rs = getPictureKeywords.executeQuery();
            
            ArrayList<String> keywords = new ArrayList<>();
      
            while(rs.next()) {
                keywords.add(rs.getString("word"));      
            }
            
            String[] keywordArray = keywords.toArray(new String[0]);
            
            picture.setKeywords(keywordArray);
                    
        } catch(SQLException e) {
            System.out.println("An SQL Exception occurred: " + e.getMessage());
        }
    }
    
    
}
