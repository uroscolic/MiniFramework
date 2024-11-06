package example.myPackage.controllers;

import example.annotations.Controller;
import example.annotations.GET;
import example.annotations.POST;
import example.annotations.Path;

@Controller
@Path("/test-controller")
public class TestController {
    @GET
    @Path("/test")
    public void testGet() {
        System.out.println("GET /test-controller/test");
    }

    @POST
    @Path("/test")
    public void testPost() {
        System.out.println("POST /test-controller/test");
    }

}
