package model.beans;

import model.entities.UserDetails;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author MASC
 */
@Named(value="UserDetailsBean")
@ApplicationScoped
public class UserDetailsBean{
    

    @EJB
    private UserDetailsService serviceUserDetails;
    private UserDetails UserDetails = new UserDetails();
    List<UserDetails> lista = new ArrayList<>();
    
    public void iniciarCampos() {
      serviceUserDetails.criar();
    }

    public boolean salvar(UserDetails entidade) {
    entidade.setId(Long.MIN_VALUE);
        serviceUserDetails.persistir(entidade);
        return true;
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
