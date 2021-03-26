package model.service;


import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;

import model.service.Servico;

import model.entities.Order;

/**
 *
 * @author deecarneiro
 */
@Stateless
public class OrderService extends Servico<Order> {

    @Override
    public Order criar() {
        return new Order();
    }
    
     @Override
    public void persistir(Order entidade) {
        entityManager.persist(entidade);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order atualizar(Order entidade) {
        entityManager.merge(entidade);
        entityManager.flush();
        return entidade;
    }

    public void remover(Order entidade) {
        entidade = entityManager.merge(entidade);
        entityManager.remove(entidade);

    }

    @TransactionAttribute(SUPPORTS)
    public List<Order> consultarEntidades() {
       return consultarEntidades( new Object[] {}, Order.ALL_ORDERS);
    }
    
    @TransactionAttribute(SUPPORTS)
    public Order consultar(long id) {
        return consultarEntidade( new Object[] {id}, Order.ORDER_BY_ID);

    }
}
