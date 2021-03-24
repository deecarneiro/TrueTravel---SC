package model.entities;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Deyse
 */
@Entity
@Table(name="TB_MANAGER") 
@DiscriminatorValue(value = "E")
@PrimaryKeyJoinColumn(name="ID_USER", referencedColumnName = "ID")
@NamedQueries(
        {
            @NamedQuery(
                    name = Manager.ALL_MANAGERS,
                    query = "SELECT e FROM Manager e"
            ),
             @NamedQuery(
                    name = Manager.PASS_AND_LOGIN,
                    query = "SELECT e FROM Manager e WHERE e.username = ?1 AND e.password = ?2"
            )
        }
)
public class Manager extends UserSuper implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ALL_MANAGERS = "All_Managers";
    public static final String PASS_AND_LOGIN= "Manager_By_Pass";
    
    public Manager(String cpf) {
		super();
		this.cpf = cpf;
	}

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	@CPF
    @NotNull
    private String cpf;
    
    
    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Manager)) {
            return false;
        }
        Manager other = (Manager) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Manager[ id=" + id + " ]";
    }
    
}
