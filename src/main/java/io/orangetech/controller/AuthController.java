package io.orangetech.controller;

import io.orangetech.entity.AccessToken;
import io.orangetech.entity.UserRequest;
import io.orangetech.entity.LoginRequest;
import io.orangetech.services.AuthService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/secured")
public class AuthController {

    @Inject
    AuthService authService;


    @POST
    @Path("/sign-up")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(UserRequest userRequest){
        try{
            authService.registerUser(userRequest);
            return Response.ok("Account Created Successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity("Account is already created").build();
        }
    }

    @POST
    @Path("/sign-in")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(LoginRequest loginRequest) throws Exception {
            try {
                AccessToken accessToken = authService.authenticateUser(loginRequest);
                return Response.ok(accessToken).build();
            }catch (Exception e ){
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
    }


}
