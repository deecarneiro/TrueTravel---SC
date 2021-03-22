/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.test.java.Entity.jpqlTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.test.java.Entity.ValidatorTest.GenericTest;
import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Test;

import model.entities.ClientUser;
import model.entities.UserSuper;

/**
 *
 * @author Deyse
 */
public class ClientUserJPQLTest extends GenericTest{
    
    @Test
    public void allClientUsers(){
        logger.info("Running allClientUsers()");
        TypedQuery<UserSuper> query = em.createQuery(
        "SELECT u FROM UserSuper u WHERE TYPE(u) IN (ClientUser)",
        UserSuper.class
        );
        List<UserSuper> clients = query.getResultList();
        for (UserSuper client : clients){
            System.out.print(client);
        }
        assertEquals(8, clients.size());
    }
    
    
    @Test
    public void allActiveClientUsers(){
        logger.info("Running allActiveClientUsers()");
        TypedQuery<UserSuper> query = em.createQuery(
        "SELECT u FROM UserSuper u WHERE TYPE(u) IN (ClientUser) AND u.status LIKE :status",
         UserSuper.class
        );
        List<UserSuper> clients = query.getResultList();
        for (UserSuper client : clients){
            System.out.print(client);
        }
        assertEquals(8, clients.size());
    }
    
    
    @Test
    public void clientByName(){
        logger.info("Running clientByName()");
        TypedQuery<ClientUser> query = em.createQuery(
        "SELECT c FROM ClientUser c WHERE c.name LIKE :name",
        ClientUser.class
        );
        query.setParameter("name","Deyse Carneiro");
        List<ClientUser> clients = query.getResultList();
       
        for (ClientUser client : clients){
            assertTrue(client.getName().startsWith("Deyse Carneiro"));
            System.out.print(client);
        }
        
        assertEquals(1, clients.size());
    }
    
    @Test
    public void clientByCNPJ(){
        logger.info("Running clientByCNPJ");
        TypedQuery<ClientUser> query = em.createQuery(
        "SELECT c FROM ClientUser c WHERE c.cnpj LIKE :cnpj",
        ClientUser.class
        );
        query.setParameter("cnpj", "94.444.303/0001-47");
        ClientUser client = query.getSingleResult();
        System.out.println(client);
        assertNotNull(client);
    }
       
}
