/*
 * To change this license header, choose License Headers in UserDetails Properties.
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

import model.entities.UserDetails;

/**
 *
 * @author deecarneiro
 */

@LocalBean
public class UserDetailsService extends Servico<UserDetails> {

    @Override
    public UserDetails criar() {
        return new UserDetails() {};
    }
    
     @Override
    public void persistir(UserDetails entidade) {
        entityManager.persist(entidade);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDetails atualizar(UserDetails entidade) {
        entityManager.merge(entidade);
        entityManager.flush();
        return entidade;
    }

    public void remover(UserDetails entidade) {
        entidade = entityManager.merge(entidade);
        entityManager.remove(entidade);

    }

    @TransactionAttribute(SUPPORTS)
    public List<UserDetails> consultarEntidades() {
       return consultarEntidades( new Object[] {}, UserDetails.ALL_USER_DETAILS);
    }
}
