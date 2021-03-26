package model.entities;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
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
@Inheritance(strategy = InheritanceType.JOINED) //Estratégia de herança.
@DiscriminatorColumn(name = "disc_user", //Nome da coluna que vai discriminar subclasses.
        discriminatorType = DiscriminatorType.STRING, length = 1)
@Access(AccessType.FIELD)

@NamedQueries(
		{ 
		@NamedQuery(name = UserSuper.ALL_USERS, query = "SELECT us FROM UserSuper us"),
		@NamedQuery(name = UserSuper.USER_BY_ID, query = "SELECT us FROM UserSuper us WHERE us.id = ?1"),
		@NamedQuery(name = UserSuper.LOGIN, query = "SELECT us FROM UserSuper us WHERE us.username = ?1 AND us.password = ?2 "), }

)
public abstract class UserSuper extends Entidade implements Serializable {
    
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
    
    @NotNull
    @NotBlank
    @Size(min = 6, max = 10)
    @Column(name="username")
    private String username;
    
    @NotBlank
    @NotNull
    @Column(name="name")
    private String name;
    
    @NotNull
    @Email
    @Column(name="email")
    private String email;
    
    @NotNull
    @Pattern(regexp = "((?=.*\\p{Digit})(?=.*\\p{Lower})(?=.*\\p{Upper})(?=.*\\p{Punct}).{6,20})",  message = "{Entity.UserSuper.password}")
    @Column(name="password")
    private String password;

    @NotNull
    @Column(name="phone_number")
    private String phoneNumber;
    
    @NotNull
    @Column(name="externalId")
    private Long externalId;
    
    @NotNull
    @Column(name="created")
    @Temporal(TemporalType.DATE)
    private Date created;
    
    @NotNull
    @Column(name="lastLogin")
    @Temporal(TemporalType.DATE)
    private Date lastLogin;
    
    @NotBlank
    @Column(name="status")
    protected int status;

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		// TODO Auto-generated method stub
		return super.equals(object);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
    
}