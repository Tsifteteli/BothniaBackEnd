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
   
    private final String GET_PICTURE_INFO = 
        "SELECT p.piNo, ph.fName , ph.lName, po.description AS oportunityDescription," +
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
    
    private Picture picture;
    
    
    
    public PictureController() {
        this.picture = new Picture();
    }

    
    public Picture getAllPictureInfo(int id) {
        
        this.getPictureInfo(id);
        this.getPublishings(id);
        this.getKeywords(id);

        return picture;
    }
    
    
    private void getPictureInfo(int id) {
        
        try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement getPictureInfo = con.prepareStatement(GET_PICTURE_INFO)) {
            
            getPictureInfo.setInt(1, id);
            ResultSet rs = getPictureInfo.executeQuery();
      
            while(rs.next()) {
                picture.setPiNo(rs.getInt("piNo"));
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
