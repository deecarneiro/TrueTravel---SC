/*
 * To change this license header, choose License Headers in UserSuper Properties.
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

import model.entities.Order;
import model.entities.UserSuper;

/**
 *
 * @author deecarneiro
 */
@Stateless
public class UserService extends Servico<UserSuper> {

    @Override
    public UserSuper criar() {
        return new UserSuper();
    }
    
     @Override
    public void persistir(UserSuper entidade) {
        entityManager.persist(entidade);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserSuper atualizar(UserSuper entidade) {
        entityManager.merge(entidade);
        entityManager.flush();
        return entidade;
    }

    public void remover(UserSuper entidade) {
        entidade = entityManager.merge(entidade);
        entityManager.remove(entidade);

    }

    @TransactionAttribute(SUPPORTS)
    public List<UserSuper> consultarEntidades() {
       return consultarEntidades( new Object[] {}, UserSuper.ALL_USERS);
    }
    
    @TransactionAttribute(SUPPORTS)
	public UserSuper consultar(long id) {
		return consultarEntidade(new Object[] { id }, UserSuper.USER_BY_ID);

	}
    
    @TransactionAttribute(SUPPORTS)
    public UserSuper login(String username, String password) {
    	return consultarEntidade(new Object[] {username, password}, UserSuper.LOGIN);
    }
}
