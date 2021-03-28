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
import model.entities.OrderMessage;

/**
 *
 * @author deecarneiro
 */

@Stateless
public class OrderMessageService extends Servico<OrderMessage> {

	@Override
	public OrderMessage criar() {
		return new OrderMessage();
	}

	@Override
	public void persistir(OrderMessage entidade) {
		entityManager.persist(entidade);// To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public OrderMessage atualizar(OrderMessage entidade) {
		entityManager.merge(entidade);
		entityManager.flush();
		return entidade;
	}

	public void remover(OrderMessage entidade) {
		entidade = entityManager.merge(entidade);
		entityManager.remove(entidade);

	}

	@TransactionAttribute(SUPPORTS)
	public List<OrderMessage> consultarEntidades() {
		return consultarEntidades(new Object[] {}, OrderMessage.ALL_MESSAGE_ORDER);
	}

	@TransactionAttribute(SUPPORTS)
	public List<OrderMessage> consultar(long id) {
		return consultarEntidades(new Object[] { id }, OrderMessage.ALL_MESSAGE_BY_ORDER);

	}

	@TransactionAttribute(SUPPORTS)
	public OrderMessage consultarId(long id) {
		return consultarEntidade(new Object[] { id }, OrderMessage.ORDER_MESSAGE_BY_ID);

	}
}
