package model.beans;

import model.entities.UserDetails;
import model.entities.UserSuper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import model.service.UserDetailsService;

/**
 *
 * @author MASC
 */
@Stateless
public class UserDetailsBean{
    

    @EJB
    private UserDetailsService serviceUserDetails;
    private UserDetails UserDetails = new UserDetails() {};
    List<UserDetails> lista = new ArrayList<UserDetails>();
    
    public void iniciarCampos() {
      serviceUserDetails.criar();
    }

    public boolean salvar(UserDetails entidade) {
//    entidade.setId(Long.MIN_VALUE);
        serviceUserDetails.persistir(entidade);
        return true;
    }

    public UserDetails atualizar(UserDetails entidade, int id) {
    	UserDetails userById = serviceUserDetails.consultar(id);
    	return serviceUserDetails.atualizar(entidade);
    }
    
    public void remover(int id) {
    	UserDetails userById = serviceUserDetails.consultar(id);
    	serviceUserDetails.remover(userById);
    }
   
    public List<UserDetails> getLista() {
        lista = serviceUserDetails.consultarEntidades();
        return lista;
    }

    public void setLista(List<UserDetails> lista) {
        this.lista = lista;
    }

    public UserDetails getUserDetails() {
        return UserDetails;
    }

    public void setUserDetails(UserDetails UserDetails) {
        this.UserDetails = UserDetails;
    }
    

}
