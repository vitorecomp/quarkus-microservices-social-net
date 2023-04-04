package study.arch.socialnet.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/follow")
public class FolllowersResource {

    @GET
    @Path("/{id}")
    public String lookFollowing(@HeaderParam(value = "myId") String myid) {
        return "Hello from RESTEasy Reactive";
    }

    @POST
    @Path("/{whoToFollow}")
    public String create(@HeaderParam(value = "myId") String myid, @PathParam(value = "whoToFollow") String whoToFollow) {
        return "Hello from RESTEasy Reactive";
    }

    @DELETE
    @Path("/{whoToUnfollow}")
    public String delete(@PathParam(value = "whoToUnfollow") String whoToUnfollow) {
        return "Hello from RESTEasy Reactive";
    }
}