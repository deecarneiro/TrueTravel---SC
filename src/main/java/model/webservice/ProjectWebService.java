/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.webservice;

import java.util.ArrayList;
import java.util.Arrays;
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

import model.beans.ProjectBean;
import model.entities.Project;
import model.entities.Project;
/**
 * REST Web Service
 *
 * @author deecarneiro
 */
@Path("project")
public class ProjectWebService {

	@EJB
	private ProjectBean projectBean;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Project create(@Context HttpServletRequest request, String is)
			throws JsonMappingException, JsonProcessingException {
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		Project project = mapper.readValue(is, Project.class);
		projectBean.salvar(project);
		session.setAttribute("Project", project);
		return project;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> list(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<Project> projects = projectBean.getLista();
		session.setAttribute("Project", projects);
		return projects;

	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Project getById(@Context HttpServletRequest request, @PathParam("id") long id) {
		HttpSession session = request.getSession();
		Project project = projectBean.getById(id);
		session.setAttribute("Project", project);
		return project;
	}
	
	@GET
	@Path("search/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> getByName(@Context HttpServletRequest request, @PathParam("name") String name) {
		HttpSession session = request.getSession();
		List<Project> projects = projectBean.getByName(name);
		session.setAttribute("Project", projects);
		return projects;
	}

	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Project update(@Context HttpServletRequest request, @PathParam("id") int id, String jsonString) throws JsonMappingException, JsonProcessingException {
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		Project projectnew = mapper.readValue(jsonString, Project.class);
		projectBean.atualizar(projectnew, id);
		session.setAttribute("Project", projectnew);
		return projectnew;
	}

	@DELETE
	@Path("{id}")
	public void remove(@Context HttpServletRequest request, @PathParam("id") int id) {
		HttpSession session = request.getSession();
		projectBean.remover(id);
	}

	static class ProjectWebService_JerseyClient {

		private WebTarget webTarget;
		private Client client;
		private static final String BASE_URI = "http://localhost:8080/TrueTravel/";

		public ProjectWebService_JerseyClient() {
			client = javax.ws.rs.client.ClientBuilder.newClient();
			webTarget = client.target(BASE_URI).path("project");
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
