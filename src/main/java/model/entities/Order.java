
package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Deyse
 */
@Entity
@Table(name = "TB_ORDER")
@DiscriminatorValue(value = "o")
@NamedQueries({ @NamedQuery(name = Order.ALL_ORDERS, query = "SELECT o FROM Order o"),
		@NamedQuery(name = Order.ORDER_BY_ID, query = "SELECT o FROM Order o WHERE o.id = ?1") })
public class Order extends Entidade implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4533649529217112074L;
	public static final String ALL_ORDERS = "All_Orders";
	public static final String ORDER_BY_ID = "Order_By_Id";

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@ManyToOne
	@JoinColumn(name = "userId")
	protected model.entities.UserSuper UserSuper;

	@NotBlank
	@Column(name = "destination")
	protected String destination;

	@NotBlank
	@Column(name = "origin")
	protected String origin;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "departureDate")
	protected Date departureDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "arrivalDate")
	protected Date arrivalDate;

	@NotBlank
	@Column(name = "agencyName")
	protected String agencyName;

	@NotNull
	@Column(name = "cost")
	protected double cost;

	@Column(name = "status")
	protected int status;
	
	@JoinColumn(name = "projectId", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Project.class, fetch = FetchType.LAZY)
    @JsonIgnore
    private Project project;

    @Column(name = "projectId", insertable = true, updatable = true)
    private Long projectId;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public model.entities.UserSuper getUserSuper() {
		return UserSuper;
	}

	public void setUserSuper(model.entities.UserSuper userSuper) {
		UserSuper = userSuper;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((UserSuper == null) ? 0 : UserSuper.hashCode());
		result = prime * result + ((agencyName == null) ? 0 : agencyName.hashCode());
		result = prime * result + ((arrivalDate == null) ? 0 : arrivalDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((departureDate == null) ? 0 : departureDate.hashCode());
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result + status;
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
		Order other = (Order) obj;
		if (UserSuper == null) {
			if (other.UserSuper != null)
				return false;
		} else if (!UserSuper.equals(other.UserSuper))
			return false;
		if (agencyName == null) {
			if (other.agencyName != null)
				return false;
		} else if (!agencyName.equals(other.agencyName))
			return false;
		if (arrivalDate == null) {
			if (other.arrivalDate != null)
				return false;
		} else if (!arrivalDate.equals(other.arrivalDate))
			return false;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (departureDate == null) {
			if (other.departureDate != null)
				return false;
		} else if (!departureDate.equals(other.departureDate))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", UserSuper=" + UserSuper + ", destination=" + destination + ", origin=" + origin
				+ ", departureDate=" + departureDate + ", arrivalDate=" + arrivalDate + ", agencyName=" + agencyName
				+ ", cost=" + cost + ", status=" + status + ", project=" + project + ", projectId=" + projectId + "]";
	}
    
    

}
