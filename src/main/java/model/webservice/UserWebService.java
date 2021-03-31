/*
 * To change this license header, choose License Headers in UserSuper Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.webservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.Consumes;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import model.beans.UserBean;
import model.entities.UserSuper;
/**
 * REST Web Service
 *
 * @author deecarneiro
 */
@Path("user")
public class UserWebService {

	@EJB
	private UserBean UserBean;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserSuper create(@Context HttpServletRequest request, String is)
			throws JsonMappingException, JsonProcessingException {
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		UserSuper user = mapper.readValue(is, UserSuper.class);
		Date rightNow = Calendar.getInstance().getTime(); 	
		user.setCreated(rightNow);
		UserBean.salvar(user);
		session.setAttribute("UserSuper", user);
		return user;
	}
	
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserSuper login(@Context HttpServletRequest request, String is) throws JsonMappingException, JsonProcessingException{
		HttpSession session = request.getSession();
		UserSuper user = new UserSuper();
		final ObjectNode node = new ObjectMapper().readValue(is, ObjectNode.class);
		if (node.has("password") && node.has("username")) {
			String password = node.get("password").textValue();
			String username = node.get("username").textValue();
			user = UserBean.login(username, password);
			Date rightNow = Calendar.getInstance().getTime(); 	
			user.setLastLogin(rightNow);
			UserBean.atualizar(user, user.getId());
		}  
		
		session.setAttribute("UserSuper", user);
		return user;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserSuper> list(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<UserSuper> users = UserBean.getLista();
		session.setAttribute("UserSuper", users);
		return users;

	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserSuper getById(@Context HttpServletRequest request, @PathParam("id") long id) {
		HttpSession session = request.getSession();
		UserSuper user = UserBean.getById(id);
		session.setAttribute("UserSuper", user);
		return user;
	}

	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserSuper update(@Context HttpServletRequest request, @PathParam("id") int id, String jsonString) throws JsonMappingException, JsonProcessingException {
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		UserSuper usernew = mapper.readValue(jsonString, UserSuper.class);
		Date rightNow = Calendar.getInstance().getTime(); 	
		usernew.setLastLogin(rightNow);
		UserBean.atualizar(usernew, id);
		session.setAttribute("UserSuper", usernew);
		return usernew;
	}

	@DELETE
	@Path("{id}")
	public void remove(@Context HttpServletRequest request, @PathParam("id") int id) {
		HttpSession session = request.getSession();
		UserBean.remover(id);
	}

	static class UserSuperWebService_JerseyClient {

		private WebTarget webTarget;
		private Client client;
		private static final String BASE_URI = "http://localhost:8080/TrueTravel/";

		public UserSuperWebService_JerseyClient() {
			client = javax.ws.rs.client.ClientBuilder.newClient();
			webTarget = client.target(BASE_URI).path("user");
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
