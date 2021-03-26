package model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;

/**
 *
 * @author Deyse
 */
@Entity
@Table(name = "TB_CLIENT")
@DiscriminatorValue(value = "C")
@PrimaryKeyJoinColumn(name = "ID_USER", referencedColumnName = "ID")
@NamedQueries(
		{ 
		@NamedQuery(name = ClientUser.ALL_CLIENTS, query = "SELECT c FROM ClientUser c"),
		@NamedQuery(name = ClientUser.PASS_AND_LOGIN, query = "SELECT c FROM ClientUser c WHERE c.username = ?1 AND c.password = ?2 "), }

)
public class ClientUser extends UserSuper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ALL_CLIENTS = "All_Clients";
	public static final String PASS_AND_LOGIN = "Client_By_Id";

	public ClientUser(String cnpj) {
		super();
		this.cnpj = cnpj;
	}

	public ClientUser() {
		super();
	}

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
		if (!(object instanceof ClientUser)) {
			return false;
		}
		ClientUser other = (ClientUser) object;
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
