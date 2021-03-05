package model.beans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import model.service.*;
/**
 *
 * @author MASC
 */
@Named(value="OrderBean")
@ApplicationScoped
public class OrderBean{
    

    @EJB
    private OrderService serviceOrder;
    private Order Order = new Order();
    List<Order> lista = new ArrayList<>();
    
    public void iniciarCampos() {
      serviceOrder.criar();
    }

    public boolean salvar(Order entidade) {
    entidade.setId(Long.MIN_VALUE);
        serviceOrder.persistir(entidade);
        return true;
    }

    public List<Order> getLista() {
        lista = serviceOrder.consultarEntidades();
        return lista;
    }

    public void setLista(List<Order> lista) {
        this.lista = lista;
    }

    public Order getOrder() {
        return Order;
    }

    public void setOrder(Order Order) {
        this.Order = Order;
    }
    

}
