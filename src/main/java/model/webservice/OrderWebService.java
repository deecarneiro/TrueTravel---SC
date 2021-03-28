/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.webservice;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.beans.OrderBean;
import model.entities.Order;
import model.service.OrderService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * REST Web Service
 *
 * @author deecarneiro
 */

@Path("order")
public class OrderWebService {

	@EJB
	private OrderBean orderBean;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Order create(@Context HttpServletRequest request, String is)
			throws JsonMappingException, JsonProcessingException {
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.readValue(is, Order.class);
		orderBean.salvar(order);
		session.setAttribute("Order", order);
		return order;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> list(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<Order> orders = orderBean.getLista();
		session.setAttribute("Order", orders);
		return orders;

	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Order getById(@Context HttpServletRequest request, @PathParam("id") long id) {
		HttpSession session = request.getSession();
		Order order = orderBean.getById(id);
		session.setAttribute("Order", order);
		return order;
	}

	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Order update(@Context HttpServletRequest request, @PathParam("id") int id, String jsonString) throws JsonMappingException, JsonProcessingException {
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		Order ordernew = mapper.readValue(jsonString, Order.class);
		orderBean.atualizar(ordernew, id);
		session.setAttribute("Order", ordernew);
		return ordernew;
	}

	@DELETE
	@Path("{id}")
	public void remove(@Context HttpServletRequest request, @PathParam("id") int id) {
		HttpSession session = request.getSession();
		orderBean.remover(id);
	}

	static class OrderWebService_JerseyClient {

		private WebTarget webTarget;
		private Client client;
		private static final String BASE_URI = "http://localhost:8080/TrueTravel/";

		public OrderWebService_JerseyClient() {
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
