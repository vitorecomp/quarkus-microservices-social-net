package study.arch.socialnet;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String look() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public String look(@PathParam(value = "id") String id) {
        return "Hello from RESTEasy Reactive";
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String create() {
        return "Hello from RESTEasy Reactive";
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String delete() {
        return "Hello from RESTEasy Reactive";
    }
}