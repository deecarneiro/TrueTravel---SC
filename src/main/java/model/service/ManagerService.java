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

import model.entities.Manager;

/**
 *
 * @author deecarneiro
 */
@LocalBean
public class ManagerService extends Servico<Manager> {

    @Override
    public Manager criar() {
        return new Manager();
    }
    
    @TransactionAttribute(SUPPORTS)
    public List<Manager> consultarEntidades() {
       return consultarEntidades( new Object[] {}, Manager.ALL_MANAGERS);
    }
    
    @TransactionAttribute(SUPPORTS)
    public Manager consultarEntidade(String senha, String login) {
        return consultarEntidade( new Object[] {login,senha}, Manager.PASS_AND_LOGIN); //To change body of generated methods, choose Tools | Templates.
    }
}
