/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.hibernate.validator.internal.util.logging.Log;

import model.entities.Manager;
import model.entities.Entidade;
import model.entities.UserSuper;

/**
 * REST Web Service
 *
 * @author deecarneiro
 */
@Path("client")
public class ManagerWebService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Manager createManager(@Context HttpServletRequest request) {
        Manager Manager = new Manager();
        HttpSession session = request.getSession();
        session.setAttribute("Manager", Manager);
        return Manager;
    }


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
        private static final String BASE_URI = "http://localhost:8080/manager/resources";

        public GenericResource_JerseyClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("generic");
        }

        public <T> T createGame(Class<T> responseType) throws ClientErrorException {
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
