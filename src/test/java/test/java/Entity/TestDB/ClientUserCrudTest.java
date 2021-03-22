
package java.test.java.Entity.TestDB;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.test.java.Entity.ValidatorTest.GenericTest;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;

import org.junit.Test;

import model.entities.ClientUser;

/**
 *
 * @author Deyse
 */
public class ClientUserCrudTest extends GenericTest {
    @Test
    public void persistirClientUser() {
        logger.info("Executando persistirClientUser()");
        ClientUser cliente1 = criarClientUser();
        em.persist(cliente1);
        em.flush();
        assertNotNull(cliente1.getId());
        assertNotNull(cliente1.getCnpj());
        assertNotNull(cliente1.getName());
        assertNotNull(cliente1.getPassword());
    }
   
    @Test   
    public void atualizarClientUsere(){
        logger.info("Executando atualizarClientUsere()");
        String novoLogin = "danita";
        Long id = 7L;
        ClientUser cliente = em.find(ClientUser.class, id);
        em.flush();
        String jpql = "SELECT c FROM ClientUser c WHERE c.id = ?1";
        TypedQuery<ClientUser> query = em.createQuery(jpql, ClientUser.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);   
        cliente = query.getSingleResult();
    }
    
    @Test
    public void atualizarClientUserMerge() {
        logger.info("Executando atualizarClientUserMerge()");
        String novoEmail = "atualizado@email.com";
        
        Long id = 2L;
        ClientUser cliente = em.find(ClientUser.class, id);
        cliente.setCnpj("97.847.364/0001-43");
        cliente.setName("Deyse Carneiro");
        cliente.setPassword("Pass123*");
        cliente.setEmail(novoEmail);
        em.clear();
        em.merge(cliente);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        cliente = em.find(ClientUser.class, id, properties);
        assertEquals(novoEmail, cliente.getEmail());        
    }  
    
    @Test
    public void removerClientUser() {
      logger.info("Executando removerClientUser()");
      ClientUser cliente = em.find(ClientUser.class, 19L);
      em.remove(cliente);
    } 

  
   
    public ClientUser criarClientUser() {
        ClientUser cliente = new ClientUser();
        cliente.setId(19L);
        cliente.setCnpj("97.847.364/0001-43");
        cliente.setEmail("email@email.com");
        cliente.setName("Deyse Carneiro");
        cliente.setPassword("Pass123*");
        return cliente;
    }
    
}


