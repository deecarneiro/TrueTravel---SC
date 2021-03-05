/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;

import model.entities.ClientUser;

/**
 *
 * @author deecarneiro
 */
@Stateless
@LocalBean
@ValidateOnExecution(type = ExecutableType.ALL)
public class ClientService extends Servico<ClientUser> {


    @TransactionAttribute(SUPPORTS)
    public List<ClientUser> consultarEntidades() {
       return consultarEntidades( new Object[] {}, ClientUser.ALL_CLIENTS);
    }

    @TransactionAttribute(SUPPORTS)
    public ClientUser criar() {
        return new ClientUser();
    }

    @TransactionAttribute(SUPPORTS)
    public ClientUser consultarEntidade(String senha, String login) {
        return consultarEntidade( new Object[] {login,senha}, ClientUser.PASS_AND_LOGIN); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
