package study.arch.socialnet.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/follow")
public class FollowersResource {

    @GET
    @Path("/{id}")
    public String lookFollowing(@HeaderParam(value = "X-User-Id") String userId) {
        return "Hello from RESTEasy Reactive";
    }

    @POST
    @Path("/{whoToFollow}")
    public String create(@HeaderParam(value = "X-User-Id") String userId, @PathParam(value = "whoToFollow") String whoToFollow) {
        return "Hello from RESTEasy Reactive";
    }

    @DELETE
    @Path("/{whoToUnFollow}")
    public String delete(@HeaderParam(value = "X-User-Id") String userId, @PathParam(value = "whoToUnFollow") String whoToUnFollow) {
        return "Hello from RESTEasy Reactive";
    }
}