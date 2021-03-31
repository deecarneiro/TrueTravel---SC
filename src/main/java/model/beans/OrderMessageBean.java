package model.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import model.entities.OrderMessage;
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
    	OrderMessage orderById = serviceOrderMessage.consultarId(id);
    	return serviceOrderMessage.atualizar(order);
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
    
    public List<OrderMessage> getByUser(long id) {
    	List<OrderMessage> orderMessages = serviceOrderMessage.consultarPorUser(id);
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
