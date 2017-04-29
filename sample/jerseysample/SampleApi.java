package jerseysample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.ObjectMapper;

@Path("/api")
public class SampleApi {

    @GET
    @Produces("text/plain")
    public String index() {
        // http://localhost:8080/jerseysample2/api/
        return "Without path param.";
    }

    @GET
    @Path("/hello")
    @Produces("text/plain")
    public String returnHello() {
        // http://localhost:8080/jerseysample2/api/hello
        return "Hello Jersey.";
    }

    @GET
    @Path("/{path}")
    @Produces("text/plain")
    public String existPathParam(@PathParam("path") String pathParam) {
        // http://localhost:8080/jerseysample2/api/hoge
        return "path : " + pathParam;
    }

    @GET
    @Path("/query/string")
    @Produces("text/plain")
    public String existQueryParam(@QueryParam("query") String queryParam) {
        // http://localhost:8080/jerseysample2/api/query/string?query=fuga
        return "queryString : " + queryParam;
    }

    @GET
    @Path("/query/integer")
    @Produces("text/plain")
    public String isIntegerQueryParam(@QueryParam("query") Integer queryParam) {
        // http://localhost:8080/jerseysample2/api/query/integer?query=3
        // http://localhost:8080/jerseysample2/api/query/integer?query=funya -> not found
        return "queryInteger : " + queryParam;
    }

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnJson() {
        // http://localhost:8080/jerseysample2/api/json
        PersonsDto dto = new PersonsDto();
        dto.personsDataDto = Arrays.asList(
                new PersonDataDto("Tom", 56, "Tokyo", "fishing"),
                new PersonDataDto("David", 18, "Osaka", "running"),
                new PersonDataDto("Jef", 32, "Okinawa", "cooking"));

        return setResponse(dto, MediaType.APPLICATION_JSON);
    }

    @XmlRootElement
    public class PersonsDto {
        public List<PersonDataDto> personsDataDto = new ArrayList<>();
    }

    @XmlRootElement
    public class PersonDataDto {
        public String name;
        public Integer age;
        public String address;
        public String hobby;

        public PersonDataDto(String name, Integer age, String address, String hobby) {
            this.name = name;
            this.age = age;
            this.address = address;
            this.hobby = hobby;
        }
    }

    Response setResponse(PersonsDto dto, String mediaType) {
        try {
            return Response.ok(new ObjectMapper().writeValueAsString(dto), mediaType).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Status.BAD_REQUEST).build();
        }
    }
}