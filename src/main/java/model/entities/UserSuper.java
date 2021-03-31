package model.entities;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Deyse
 */
@Entity
@Table(name = "TB_USER")
@DiscriminatorValue(value = "u")

@NamedQueries(
		{ 
		@NamedQuery(name = UserSuper.ALL_USERS, query = "SELECT us FROM UserSuper us"),
		@NamedQuery(name = UserSuper.USER_BY_ID, query = "SELECT us FROM UserSuper us WHERE us.id = ?1"),
		@NamedQuery(name = UserSuper.LOGIN, query = "SELECT us FROM UserSuper us WHERE us.username = ?1 AND us.password = ?2 "), }

)
public class UserSuper extends Entidade implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ALL_USERS = "All_Users";
	public static final String USER_BY_ID = "User_By_Id";
	public static final String LOGIN = "User_Login";
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    
    @NotBlank
    @Column(name="username")
    private String username;
    
    @NotBlank
    @Column(name="name")
    private String name;
    
    @NotBlank
    @Email
    @Column(name="email")
    private String email;
   
    @NotBlank
//    @Pattern(regexp = "((?=.*\\p{Digit})(?=.*\\p{Lower})(?=.*\\p{Upper})(?=.*\\p{Punct}).{6,20})",  message = "{Entity.UserSuper.password}")
    @Column(name="password")
    private String password;

    @NotBlank
    @Column(name="phoneNumber")
    private String phoneNumber;
    
    @NotNull
    @Column(name="externalId")
    private Long externalId;
    
    @NotNull
    @Column(name="created")
    @Temporal(TemporalType.DATE)
    private Date created;
    
    @Column(name="lastLogin")
    @Temporal(TemporalType.DATE)
    private Date lastLogin;
    
    @Column(name="token")
    protected String token;
    
    @NotBlank
    @Column(name="type")
    protected String type;
    
    @NotNull
    @Column(name="status")
    protected int status;
    
   
	public UserSuper() {
		super();
	}


	public UserSuper(Long id, String username, String name, String email, String password, String phoneNumber,
			Long externalId, Date created, Date lastLogin, String token, String type, int status) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.externalId = externalId;
		this.created = created;
		this.lastLogin = lastLogin;
		this.token = token;
		this.type = type;
		this.status = status;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public Long getExternalId() {
		return externalId;
	}


	public void setExternalId(Long externalId) {
		this.externalId = externalId;
	}


	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	public Date getLastLogin() {
		return lastLogin;
	}


	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((externalId == null) ? 0 : externalId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastLogin == null) ? 0 : lastLogin.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + status;
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserSuper other = (UserSuper) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (externalId == null) {
			if (other.externalId != null)
				return false;
		} else if (!externalId.equals(other.externalId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastLogin == null) {
			if (other.lastLogin != null)
				return false;
		} else if (!lastLogin.equals(other.lastLogin))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (status != other.status)
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "UserSuper [id=" + id + ", username=" + username + ", name=" + name + ", email=" + email + ", password="
				+ password + ", phoneNumber=" + phoneNumber + ", externalId=" + externalId + ", created=" + created
				+ ", lastLogin=" + lastLogin + ", token=" + token + ", type=" + type + ", status=" + status + "]";
	}
	
    
}