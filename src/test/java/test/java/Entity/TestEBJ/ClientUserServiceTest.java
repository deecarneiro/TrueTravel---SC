package java.test.java.Entity.TestEBJ;

import javax.naming.NamingException;
import model.entities.ClientUser;
import model.service.ClientService;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;


public class ClientUserServiceTest extends Teste{
    
    private ClientService clientServico;
   

    @Before
    public void setUp() throws NamingException {
        clientServico = (ClientService) container.getContext().lookup("java:global/classes/ClientService!model.service.ClientService");
    }

    @After
    public void tearDown() {
        clientServico = null;
    }

    
    //Consultas
  
    
    @Test 
    public void consultarToddos(){
       assertEquals(8, clientServico.consultarEntidades().size());
    }
   
    @Test
    public void consultar(){
        ClientUser cliente = clientServico.consultarEntidade("Admin123*", "admin1");
        assertNotNull(cliente);
    }
    
//    @Test
//    public void persistir(){
//        ClientUser client = new ClientUser();
//        client.setName("Nome");
//        client.setDescription("description");
//        clientServico.persistir(client);
//        assertNotNull(client);
//    }
//    
  
    
    
    //Persistir, Atualziar e Remover  
    
    
}
