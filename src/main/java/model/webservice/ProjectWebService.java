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
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.entities.Project;
/**
 * REST Web Service
 *
 * @author deecarneiro
 */
@Path("project")
public class ProjectWebService {


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Project create(@Context HttpServletRequest request, Project project) {
		HttpSession session = request.getSession();
		session.setAttribute("Client", "TESTE");
		return null;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Project list(@Context HttpServletRequest request) {
		return null;

	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getById(@Context HttpServletRequest request, @PathParam("id") int id) {
		return "PROJECT";

	}

	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Project update(@Context HttpServletRequest request, @PathParam("id") int id) {
		HttpSession session = request.getSession();
		session.setAttribute("Client", "TESTE");
		return null;
	}

	@DELETE
	@Path("{id}")
	public String remove(@Context HttpServletRequest request, @PathParam("id") int id) {
		HttpSession session = request.getSession();
		session.setAttribute("Client", "TESTE");
		return "PROJECT";

	}

	static class ProjectWebService_JerseyClient {

		private WebTarget webTarget;
		private Client client;
		private static final String BASE_URI = "http://localhost:8080/TrueTravel/";

		public ProjectWebService_JerseyClient() {
			client = javax.ws.rs.client.ClientBuilder.newClient();
			webTarget = client.target(BASE_URI).path("order");
		}

		public <T> T create(Object requestEntity, Class<T> responseType) throws ClientErrorException {
			return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(
					javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON),
					responseType);
		}

		public <T> T list(Class<T> responseType) throws ClientErrorException {
			WebTarget resource = webTarget;
			return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
		}

		public <T> T getById(Class<T> responseType) throws ClientErrorException {
			WebTarget resource = webTarget;
			return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
		}

		public <T> T update(Object requestEntity, Class<T> responseType) throws ClientErrorException {
			return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(
					javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON),
					responseType);
		}

		public <T> T remove(Class<T> responseType) throws ClientErrorException {
			WebTarget resource = webTarget;
			return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).delete(responseType);
		}

		public void close() {
			client.close();
		}
	}

}
