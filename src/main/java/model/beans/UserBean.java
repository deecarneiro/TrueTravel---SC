package model.beans;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import model.entities.UserSuper;
import model.service.UserService;
import utils.UserUtils;

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
    UserUtils userUtils;
    
    public void iniciarCampos() {
      serviceUser.criar();
    }

    public UserSuper salvar(UserSuper entidade) throws NoSuchAlgorithmException {
//    entidade.setId(Long.MIN_VALUE);
    	String senhaCripto = UserUtils.md5(entidade.getPassword());
    	entidade.setPassword(senhaCripto);
        serviceUser.persistir(entidade);
        return entidade;
    }
    
    public UserSuper atualizar(UserSuper order, int id) throws NoSuchAlgorithmException {
    	String password = UserUtils.md5(order.getPassword());
    	order.setPassword(password);
    	serviceUser.atualizar(order);
    	return order;
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
    
    public UserSuper login(String username, String password) throws NoSuchAlgorithmException {
    	String senhaCripto = userUtils.md5(password);
    	UserSuper user = serviceUser.login(username, senhaCripto);
    	UserSuper = serviceUser.consultar(user.getId());
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

	public UserSuper atualizar(UserSuper user, Long id) throws NoSuchAlgorithmException {
		UserSuper userById = serviceUser.consultar(id);
    	String senhaCripto = UserUtils.md5(user.getPassword());
    	user.setPassword(senhaCripto);
    	return serviceUser.atualizar(user);	
	}
    
	public UserSuper atualizarLogin(UserSuper user, Long id) throws NoSuchAlgorithmException {
		UserSuper userById = serviceUser.consultar(id);
    	return serviceUser.atualizar(user);	
	}

}
