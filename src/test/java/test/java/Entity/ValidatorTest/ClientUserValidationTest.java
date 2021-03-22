package java.test.java.Entity.ValidatorTest;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import model.entities.ClientUser;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Deyse
 */
public class ClientUserValidationTest extends GenericTest{

  
    @Test(expected = ConstraintViolationException.class)
    public void persistirClientUserInvalido() {
        ClientUser cliente = null;
        try {
            cliente = new ClientUser();
            cliente.setCnpj("258.171.482-34"); //CPF inválido
            cliente.setEmail("email_invalido@"); //E-mail inválido
            cliente.setPassword("papa");
            cliente.setName("joaninha");
            em.persist(cliente);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("class Entity.ClientUser.cnpj: CNPJ inválido"),
                                startsWith("class Entity.ClientUser.email: Não é um endereço de e-mail"),
                                startsWith("class Entity.ClientUser.login: tamanho deve estar entre 6 e 10"),
                                startsWith("class Entity.ClientUser.password: A senha deve possuir pelo menos um caractere de: pontuação, maiúscula, minúscula e número.")
                                )
                );
            }

            assertEquals(4, constraintViolations.size());
            assertNull(null);
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void atualizarClientUserInvalido() {
        TypedQuery<ClientUser> query = em.createQuery("SELECT v FROM ClientUser v WHERE v.cnpj like :cnpj", ClientUser.class);
        query.setParameter("cnpj", "94.444.303/0001-47");
        ClientUser cliente = query.getSingleResult();
        cliente.setEmail("email@");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {           
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("Não é um endereço de e-mail", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
