package model.beans;

import model.entities.OrderMessage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import model.service.OrderMessageService;

/**
 *
 * @author MASC
 */
@Named(value="OrderMessageBean")
@ApplicationScoped
public class OrderMessageBean{
    

    @EJB
    private OrderMessageService serviceOrderMessage;
    private OrderMessage OrderMessage = new OrderMessage();
    List<OrderMessage> lista = new ArrayList<OrderMessage>();
    
    public void iniciarCampos() {
      serviceOrderMessage.criar();
    }

    public boolean salvar(OrderMessage entidade) {
    entidade.setId(Long.MIN_VALUE);
        serviceOrderMessage.persistir(entidade);
        return true;
    }

    public List<OrderMessage> getLista() {
        lista = serviceOrderMessage.consultarEntidades();
        return lista;
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
