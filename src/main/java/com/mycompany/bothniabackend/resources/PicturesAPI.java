/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bothniabackend.resources;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;
import com.mycompany.bothniabackend.controller.PictureController;
import com.mycompany.bothniabackend.model.Picture;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Caroline
 */
@Path("picture")
@RequestScoped
public class PicturesAPI {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PicturesAPI
     */
    public PicturesAPI() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.bothniabackend.resources.PicturesAPI
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPictureInfo(@QueryParam("id") int id) {

        PictureController pc = new PictureController();
        
        Picture p = pc.getAllPictureInfo(id);

        return Response.ok(p).build();
    }

    /**
     * PUT method for updating or creating an instance of PicturesAPI
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
