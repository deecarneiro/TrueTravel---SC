/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.webservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.entities.ClientUser;
import model.service.ClientService;

/**
 * REST Web Service
 *
 * @author deecarneiro
 */
@Path("client")
public class ClientWebService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public boolean list(@Context HttpServletRequest request) {
    	ClientService service = new ClientService();
    	ClientUser clients = new ClientUser();
    	boolean test = service.existe(clients);
        HttpSession session = request.getSession();
        session.setAttribute("clients", clients);
        System.out.println("CLIENTS"+ clients);
        return test;
    }

//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    public Genius playSequence(@Context HttpServletRequest request, String numbers) {
//        HttpSession session = request.getSession();
//        Genius g = (Genius) session.getAttribute("genius");
//  eclipse-javadoc:%E2%98%82=TrueTravel-Backend/%5C/home%5C/deecarneiro%5C/.m2%5C/repository%5C/javax%5C/javaee-web-api%5C/7.0%5C/javaee-web-api-7.0.jar=/maven.pomderived=/true=/=/org.eclipse.jst.component.dependency=/%5C/WEB-INF%5C/lib=/=/maven.pomderived=/true=/=/org.eclipse.jst.component.nondependency=/=/=/maven.groupId=/javax=/=/maven.artifactId=/javaee-web-api=/=/maven.version=/7.0=/=/maven.scope=/provided=/%3Cjavax      List<Integer> sequencePlayed = new ArrayList<Integer>();
//        if (numbers.contains(",")) {
//            ArrayList<String> strNumbers = new ArrayList<String>(Arrays.asList(numbers.split(",")));
//            sequencePlayed.addAll(getIntegerArray(strNumbers));
//        } else {
//            sequencePlayed.add(Integer.parseInt(numbers));
//        }
//        g = g.playSequence(sequencePlayed);
//        return g;
//    }

    private ArrayList<Integer> getIntegerArray(ArrayList<String> stringArray) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (String stringValue : stringArray) {
            try {
                result.add(Integer.parseInt(stringValue));
            } catch (NumberFormatException nfe) {
            }
        }
        return result;
    }

    static class GenericResource_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8080/TrueTravel/";

        public GenericResource_JerseyClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("client");
        }

        public <T> T list(Class<T> responseType) throws ClientErrorException {
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public <T> T playSequence(Object requestEntity, Class<T> responseType) throws ClientErrorException {
            return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
        }

        public void close() {
            client.close();
        }
    }

}
