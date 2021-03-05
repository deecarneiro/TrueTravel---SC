
package model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

/**
 *
 * @author Deyse
 */
@Entity
@Table(name="order") 
@DiscriminatorValue(value = "o")
@PrimaryKeyJoinColumn(name="id_order", referencedColumnName = "id")
@NamedQueries(
        {
            @NamedQuery(
                    name = Client.ALL_CLIENTS,
                    query = "SELECT c FROM Client c"
            ),
             @NamedQuery(
                    name = Client.PASS_AND_LOGIN,
                    query = "SELECT c FROM Client c WHERE c.login = ?1 AND c.password = ?2 "
            )
        }
)
public class Order implements Serializable {
    
    public static final String ALL_CLIENTS = "All_Clients";
    public static final String PASS_AND_LOGIN= "Client_By_Id";


    
    
    @NotNull
    @CNPJ 
    @Column(name = "cnpj")
    private String cnpj;
    


    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

 
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder("Entity.Client[");
        sb.append(super.toString());
        sb.append(", ");
        sb.append(this.cnpj);
        sb.append("]");
        return sb.toString();
    }
    
}
