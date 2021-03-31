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

import model.beans.OrderMessageBean;
import model.entities.OrderMessage;

/**
 * REST Web Service
 *
 * @author deecarneiro
 */
@Path("message")
public class OrderMessageWebService {

	@EJB
	private OrderMessageBean orderMessageBean;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public OrderMessage create(@Context HttpServletRequest request, String is) throws JsonMappingException, JsonProcessingException {
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		OrderMessage message = mapper.readValue(is, OrderMessage.class);
		orderMessageBean.salvar(message);
		session.setAttribute("UserSuper", message);
		return message;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderMessage> list(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<OrderMessage> orderMessages = orderMessageBean.getLista();
		session.setAttribute("Order", orderMessages);
		return orderMessages;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public OrderMessage getById(@Context HttpServletRequest request, @PathParam("id") int id) {
		HttpSession session = request.getSession();
		OrderMessage orderMessage = orderMessageBean.getById(id);
		session.setAttribute("Order", orderMessage);
		return orderMessage;
	}
	
	@GET
	@Path("order/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderMessage> getByOrder(@Context HttpServletRequest request, @PathParam("id") int id) {
		HttpSession session = request.getSession();
		List<OrderMessage> orderMessage = orderMessageBean.getByOrder(id);
		session.setAttribute("Order", orderMessage);
		return orderMessage;
	}

	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public OrderMessage update(@Context HttpServletRequest request, @PathParam("id") int id,  String jsonString) throws JsonMappingException, JsonProcessingException {
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		OrderMessage orderMessagenew = mapper.readValue(jsonString, OrderMessage.class);
		orderMessageBean.atualizar(orderMessagenew, id);
		session.setAttribute("Order", orderMessagenew);
		return orderMessagenew;
	}

	@DELETE
	@Path("{id}")
	public void remove(@Context HttpServletRequest request, @PathParam("id") int id) {
		HttpSession session = request.getSession();
		orderMessageBean.remover(id);;
	}

	static class OrderMessageWebService_JerseyClient {

		private WebTarget webTarget;
		private Client client;
		private static final String BASE_URI = "http://localhost:8080/TrueTravel/";

		public OrderMessageWebService_JerseyClient() {
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
