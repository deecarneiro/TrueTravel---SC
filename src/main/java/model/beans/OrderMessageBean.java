package model.beans;

import model.entities.Order;
import model.entities.OrderMessage;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import model.service.OrderMessageService;

/**
 *
 * @author MASC
 */
@Stateless
public class OrderMessageBean{
    

    @EJB
    private OrderMessageService serviceOrderMessage;
    private OrderMessage OrderMessage = new OrderMessage();
    List<OrderMessage> lista = new ArrayList<OrderMessage>();
    
    public void iniciarCampos() {
      serviceOrderMessage.criar();
    }

    public OrderMessage salvar(OrderMessage entidade) {
//    entidade.setId(Long.MIN_VALUE);
        serviceOrderMessage.persistir(entidade);
        return entidade;
    }
    
    public OrderMessage atualizar(OrderMessage order, int id) {
    	model.entities.OrderMessage orderById = serviceOrderMessage.consultarId(id);
    	return serviceOrderMessage.atualizar(orderById);
    }

    public void remover(int id) {
    	OrderMessage orderById = serviceOrderMessage.consultarId(id);
    	serviceOrderMessage.remover(orderById);
    }
    
    public List<OrderMessage> getLista() {
        lista = serviceOrderMessage.consultarEntidades();
        return lista;
    }

    public OrderMessage getById(long id) {
    	OrderMessage = serviceOrderMessage.consultarId(id);
    	return OrderMessage;
    }
    
    public List<OrderMessage> getByOrder(long id) {
    	List<OrderMessage> orderMessages = serviceOrderMessage.consultar(id);
    	return orderMessages;
    }
    
    public void setLista(List<OrderMessage> lista) {
        this.lista = lista;
    }

    public OrderMessage getOrderMessage() {
        return OrderMessage;
    }

    public void setOrderMessage(OrderMessage OrderMessage) {
        this.OrderMessage = OrderMessage;
    }
    

}
