package model.beans;

import model.entities.Project;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import model.service.ProjectService;

/**
 *
 * @author MASC
 */
@Named(value="ProjectBean")
@ApplicationScoped
public class ProjectBean{
    

    @EJB
    private ProjectService serviceProject;
    private Project Project = new Project();
    List<Project> lista = new ArrayList<Project>();
    
    public void iniciarCampos() {
      serviceProject.criar();
    }

    public boolean salvar(Project entidade) {
    entidade.setId(Long.MIN_VALUE);
        serviceProject.persistir(entidade);
        return true;
    }

    public List<Project> getLista() {
        lista = serviceProject.consultarEntidades();
        return lista;
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
