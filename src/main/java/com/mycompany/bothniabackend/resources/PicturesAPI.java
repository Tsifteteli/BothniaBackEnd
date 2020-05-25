/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bothniabackend.resources;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;
import com.mycompany.bothniabackend.controller.PictureController;
import com.mycompany.bothniabackend.model.*;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;
import javax.ws.rs.ext.Provider;

/**
 * REST Web Service
 *
 * @author Caroline
 */
@Path("picture")
@RequestScoped
@Provider
public class PicturesAPI implements ContainerResponseFilter {

    @Context
    private UriInfo context;
    
    
    @Override
    public void filter(ContainerRequestContext requestContext, 
      ContainerResponseContext responseContext) throws IOException {
          responseContext.getHeaders().add(
            "Access-Control-Allow-Origin", "*");
          responseContext.getHeaders().add(
            "Access-Control-Allow-Credentials", "true");
          responseContext.getHeaders().add(
           "Access-Control-Allow-Headers",
           "origin, content-type, accept, authorization");
          responseContext.getHeaders().add(
            "Access-Control-Allow-Methods", 
            "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }

    /**
     * Creates a new instance of PicturesAPI
     */
    public PicturesAPI() {
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPictureInfo(@QueryParam("id") int id) {

        PictureController pc = new PictureController();
        
        Picture p = pc.getAllPictureInfo(id);

        return Response.ok(p).build();
    }


    @Path("pictures")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPictures(@QueryParam("search") String search) {
        
//        PictureController pc = new PictureController();
//        
//        Picture[] p = pc.getPictures(search);


// Ta en String som är nyckleorden separerade med _
// Gör metod för att separera ut respektive ord och lägg i Arraylist
//Gör lop som gör en sökning för avrje ord i listan och lägger respektive resultat i var sin Arraylist
//Samt lägger varje nytt piNo i en totallista och räknar upp en counter för varje gång den inträffar
//Kolla igenom total-arraylisten efter de bilder som komm med i samtliga sökord (-1) - Ska vi ha sätt för att markera om de är alla eller -1?
//Returnera array med picture-objekt

        
        return Response.ok(search).build();
    }
}
