/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.webservice;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.beans.UserDetailsBean;
import model.entities.UserDetails;
import utils.Authorize;
/**
 * REST Web Service
 *
 * @author deecarneiro
 */
@Path("user/details")
public class UserDetailsWebService {

	@EJB
	private UserDetailsBean UserDetailsBean;
	
	@Authorize
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserDetails create(@Context HttpServletRequest request, String is)
			throws JsonMappingException, JsonProcessingException {
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		UserDetails details = mapper.readValue(is, UserDetails.class);
		UserDetailsBean.salvar(details);
		session.setAttribute("UserDetails", details);
		return details;
	}

	@Authorize
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserDetails> list(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<UserDetails> detailss = UserDetailsBean.getLista();
		session.setAttribute("UserDetails", detailss);
		return detailss;

	}

	@Authorize
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserDetails getById(@Context HttpServletRequest request, @PathParam("id") long id) {
		HttpSession session = request.getSession();
		UserDetails details = UserDetailsBean.getById(id);
		session.setAttribute("UserDetails", details);
		return details;
	}

	@Authorize
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserDetails update(@Context HttpServletRequest request, @PathParam("id") int id, String jsonString) throws JsonMappingException, JsonProcessingException {
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		UserDetails detailsnew = mapper.readValue(jsonString, UserDetails.class);
		UserDetailsBean.atualizar(detailsnew, id);
		session.setAttribute("UserDetails", detailsnew);
		return detailsnew;
	}

	@Authorize
	@DELETE
	@Path("{id}")
	public void remove(@Context HttpServletRequest request, @PathParam("id") int id) {
		HttpSession session = request.getSession();
		UserDetailsBean.remover(id);
	}

	static class UserDetailsWebService_JerseyClient {

		private WebTarget webTarget;
		private Client client;
		private static final String BASE_URI = "http://localhost:8080/TrueTravel/";

		public UserDetailsWebService_JerseyClient() {
			client = javax.ws.rs.client.ClientBuilder.newClient();
			webTarget = client.target(BASE_URI).path("user/details");
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
