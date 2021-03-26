package model.beans;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;

import model.entities.Order;
import model.service.OrderService;
/**
 *
 * @author MASC
 */
@Stateless
public class OrderBean{
    

    @EJB
    private OrderService serviceOrder;
    private Order Order = new Order();
    List<Order> lista = new ArrayList<Order>();
    
    public void iniciarCampos() {
      serviceOrder.criar();
    }

    public Order salvar(Order entidade) {
//    entidade.setId(Long.MIN_VALUE);
        serviceOrder.persistir(entidade);
        return entidade;
    }
    
    public Order atualizar(Order order, int id) {
    	Order orderById = serviceOrder.consultar(id);
    	return serviceOrder.atualizar(order);
    }
    
    public void remover(int id) {
    	Order orderById = serviceOrder.consultar(id);
    	serviceOrder.remover(orderById);
    }

    public List<Order> getLista() {
        lista = serviceOrder.consultarEntidades();
        return lista;
    }
    
    public Order getById(long id) {
    	this.Order = serviceOrder.consultarPorId(id);
    	return this.Order;
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
