package model.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import model.entities.UserSuper;
import model.entities.UserSuper;
import model.entities.UserSuper;
import model.entities.UserSuper;
import model.service.UserService;

/**
 *
 * @author MASC
 */
@Stateless
public class UserBean{
    

    @EJB
    private UserService serviceUser;
    private UserSuper UserSuper = new UserSuper();
    List<UserSuper> lista = new ArrayList<UserSuper>();
    
    public void iniciarCampos() {
      serviceUser.criar();
    }

    public UserSuper salvar(UserSuper entidade) {
//    entidade.setId(Long.MIN_VALUE);
        serviceUser.persistir(entidade);
        return entidade;
    }
    
    public UserSuper atualizar(UserSuper order, int id) {
    	UserSuper userById = serviceUser.consultar(id);
    	return serviceUser.atualizar(order);
    }
    
    public void remover(int id) {
    	UserSuper userById = serviceUser.consultar(id);
    	serviceUser.remover(userById);
    }

    public List<UserSuper> getLista() {
        lista = serviceUser.consultarEntidades();
        return lista;
    }

    public UserSuper getById(long id) {
    	UserSuper = serviceUser.consultar(id);
    	return UserSuper;
    }
    
    public UserSuper login(String username, String password) {
    	UserSuper = serviceUser.login(username, password);
    	return UserSuper;
    }
    
    public void setLista(List<UserSuper> lista) {
        this.lista = lista;
    }

    public UserSuper getUserSuper() {
        return UserSuper;
    }

    public void setUserSuper(UserSuper UserSuper) {
        this.UserSuper = UserSuper;
    }

	public UserSuper atualizar(UserSuper user, Long id) {
		UserSuper userById = serviceUser.consultar(id);
    	return serviceUser.atualizar(user);	
	}
    

}
