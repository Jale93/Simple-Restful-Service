/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebApplication;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jale.mammadli
 */
@Path("aliens")
public class AlienResource {
    AlienRepository repo = new AlienRepository();
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Alien> getAliens(){
        return repo.getAliens();
    }
    
    @POST
    @Path("alien")
    @Consumes(MediaType.APPLICATION_XML)
    public Alien createAlien(Alien a1){
        repo.create(a1);
        return a1;
    }
    @GET
    @Path("alien/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Alien getAlien(@PathParam("id") int id){
        return repo.getAlien(id);
    }
    @PUT
    @Path("alien")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Alien updateAlien(Alien a1){
        if(repo.getAlien(a1.getId()) == null) repo.create(a1);
        else repo.update(a1);
        return a1;
    }
    @DELETE
    @Path("alien/{id}")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Alien killAlien(@PathParam("id") int id){
        Alien a = repo.getAlien(id);
        if(a!=null && a.getId()!=0)repo.delete(id);
        return a;
    }
}
