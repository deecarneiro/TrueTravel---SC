package model.beans;

import model.entities.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MASC
 */
@Named
@SessionScoped
public class UserBean implements Serializable{

    @EJB
    private ClientService serviceClient;
    private ClientUser cliente = new ClientUser();
   
    @EJB
    private ManagerService serviceManager;
    private Manager Manager= new Manager();
 
    public ClientUser iniciarCampos() {
      return serviceClient.criar();
    }

    public String loginClient(String login, String senha) {
        cliente = serviceClient.consultarEntidade(senha, login);
        if(cliente != null){
                    System.out.println("LOGIN:"+login+" SENHA"+senha+"CLIENT"+cliente);

            return "solicitacoes";
        }
        System.out.println("LOGIN:"+login+" SENHA"+senha+"CLIENT"+cliente);
        return "index";
    }
    
    public String loginManager(String login, String senha) {
        Manager = serviceManager.consultarEntidade(senha, login);
        if(Manager != null){
            System.out.println("LOGIN:"+login+" SENHA"+senha+"CLIENT"+Manager);

            return "listarProjetos";
        }
        System.out.println("LOGIN:"+login+" SENHA"+senha+"CLIENT"+Manager);
        return "listarProjetos";
    }
 
    
    public String logout(){
              return "sair";
    }

    public ClientService getServiceClient() {
        return serviceClient;
    }

    public void setServiceClient(ClientService serviceClient) {
        this.serviceClient = serviceClient;
    }

    public ClientUser getCliente() {
        return cliente;
    }

    public void setCliente(ClientUser cliente) {
        this.cliente = cliente;
    }

    public ManagerService getServiceManager() {
        return serviceManager;
    }

    public void setServiceManager(ManagerService serviceManager) {
        this.serviceManager = serviceManager;
    }

    public Manager getManager() {
        return Manager;
    }

    public void setManager(Manager Manager) {
        this.Manager = Manager;
    }
    
}
