
package model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Deyse
 */
@Entity
@Table(name="order") 
@DiscriminatorValue(value = "o")
@NamedQueries(
        {
            @NamedQuery(
                    name = Order.ALL_ORDERS,
                    query = "SELECT o FROM Order o"
            ),
             @NamedQuery(
                    name = Order.ORDER_BY_ID,
                    query = "SELECT o FROM Order o WHERE o.id = ?1"
            )
        }
)
public class Order  extends Entidade implements Serializable {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = -4533649529217112074L;
	public static final String ALL_ORDERS = "All_Orders";
    public static final String ORDER_BY_ID= "Order_By_Id";


    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    
    @ManyToOne
    @JoinColumn(name = "UserSuperId")
    protected model.entities.UserSuper UserSuper;

	@NotBlank
    @Column(name="destination")
    protected String destination;
    
    @NotBlank
    @Column(name="origin")
    protected String origin;
    
    @NotBlank
    @Temporal(TemporalType.TIME)
    @Column(name="departureDate")
    protected Date departureDate;
    
    @NotBlank
    @Temporal(TemporalType.TIME)
    @Column(name="arrivalDate")
    protected Date arrivalDate;
    
    @NotBlank
    @Column(name="agencyName")
    protected String agencyName;
    
    @NotBlank
    @Column(name="cost")
    protected double cost;
    
    @Column(name="status")
    protected int status;
    
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public UserSuper getUserSuper() {
		return UserSuper;
	}

	public void setUserSuper(UserSuper UserSuper) {
		this.UserSuper = UserSuper;
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

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order)) {
            return false;
        }
        Order other = (Order) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "Order [id=" + id + ", UserSuper=" + UserSuper + ", destination=" + destination + ", origin=" + origin
				+ ", departureDate=" + departureDate + ", arrivalDate=" + arrivalDate + ", agencyName=" + agencyName
				+ ", cost=" + cost + ", status=" + status + "]";
	}
    
    
    
}
