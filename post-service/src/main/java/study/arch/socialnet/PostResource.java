package study.arch.socialnet;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/post")
public class PostResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{userId}")
    public String getPosts(@PathParam(value = "id") String id) {
        return "Hello from RESTEasy Reactive";
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String create(@HeaderParam(value = "myId") String myId) {
        return "Hello from RESTEasy Reactive";
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{postId}")
    public String like(@PathParam(value = "postId") String postId) {
        return "Hello from RESTEasy Reactive";
    }
}