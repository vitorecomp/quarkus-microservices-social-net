package study.arch.socialnet;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/follow")
public class FolllowersResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public String lookFollowing(@HeaderParam(value = "myId") String myid) {
        return "Hello from RESTEasy Reactive";
    }

    @POST
    @Path("/{whoToFollow}")
    @Produces(MediaType.TEXT_PLAIN)
    public String create(@HeaderParam(value = "myId") String myid, @PathParam(value = "whoToFollow") String whoToFollow) {
        return "Hello from RESTEasy Reactive";
    }

    @DELETE
    @Path("/{whoToUnfollow}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam(value = "whoToUnfollow") String whoToUnfollow) {
        return "Hello from RESTEasy Reactive";
    }
}