/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;

import model.entities.Project;

/**
 *
 * @author deecarneiro
 */
@LocalBean
@ValidateOnExecution(type = ExecutableType.ALL)
public class ProjectService extends Servico<Project> {

    @Override
    public Project criar() {
        return new Project();
    }
    
     @Override
    public void persistir(Project entidade) {
        entityManager.persist(entidade);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Project atualizar(Project entidade) {
        entityManager.merge(entidade);
        entityManager.flush();
        return entidade;
    }

    public void remover(Project entidade) {
        entidade = entityManager.merge(entidade);
        entityManager.remove(entidade);

    }

    @TransactionAttribute(SUPPORTS)
    public List<Project> consultarEntidades() {
       return consultarEntidades( new Object[] {}, Project.ALL_PROJECTS);
    }
}
