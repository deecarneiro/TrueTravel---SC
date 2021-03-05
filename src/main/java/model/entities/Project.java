
package model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;



/**
 *
 * @author Deyse
 */
@Entity
@Table(name="project") 
@DiscriminatorValue(value = "p")
@PrimaryKeyJoinColumn(name="id_project", referencedColumnName = "id")
@NamedQueries(
        {
            @NamedQuery(
                    name = Project.ALL_PROJECTS,
                    query = "SELECT p FROM Project p"
            ),
             @NamedQuery(
                    name = Project.PROJECT_BY_ID,
                    query = "SELECT p FROM Project p WHERE p.id = ?"
            )
        }
)
public class Project  extends Entidade implements Serializable {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = -4533649529217112074L;
	public static final String ALL_PROJECTS = "All_Project";
    public static final String PROJECT_BY_ID= "Project_By_Id";


    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
 
    @NotBlank
    @Column(name="name")
    protected String name;
	
    @NotBlank
    @Column(name="descriptio")
    protected String description;

    @NotBlank
    @Column(name="budget")
    protected double budget;
    
    @NotBlank
    @Column(name="status")
    protected int status;
    
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getBudget() {
		return budget;
	}


	public void setBudget(double budget) {
		this.budget = budget;
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
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(budget);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + status;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (Double.doubleToLongBits(budget) != Double.doubleToLongBits(other.budget))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (status != other.status)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", description=" + description + ", budget=" + budget
				+ ", status=" + status + "]";
	}
	
	

}
