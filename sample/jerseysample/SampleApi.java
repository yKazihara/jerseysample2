package jerseysample;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/sample")
public class SampleApi {

    @GET
    public String hello() {
        return "Hello";
    }
}