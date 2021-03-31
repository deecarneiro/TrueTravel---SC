package model.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import model.entities.Project;
import model.entities.Project;
import model.entities.Project;
import model.service.ProjectService;

/**
 *
 * @author MASC
 */
@Stateless
public class ProjectBean{
    

    @EJB
    private ProjectService serviceProject;
    private Project Project = new Project();
    List<Project> lista = new ArrayList<Project>();
    
    public void iniciarCampos() {
      serviceProject.criar();
    }

    public Project salvar(Project entidade) {
//    entidade.setId(Long.MIN_VALUE);
        serviceProject.persistir(entidade);
        return entidade;
    }
    
    public Project atualizar(Project order, int id) {
    	Project orderById = serviceProject.consultar(id);
    	return serviceProject.atualizar(order);
    }
    
    public void remover(int id) {
    	Project orderById = serviceProject.consultar(id);
    	serviceProject.remover(orderById);
    }

    public List<Project> getLista() {
        lista = serviceProject.consultarEntidades();
        return lista;
    }

    public Project getById(long id) {
    	Project = serviceProject.consultar(id);
    	return Project;
    }
    
    public void setLista(List<Project> lista) {
        this.lista = lista;
    }

    public Project getProject() {
        return Project;
    }

    public void setProject(Project Project) {
        this.Project = Project;
    }
    

}
