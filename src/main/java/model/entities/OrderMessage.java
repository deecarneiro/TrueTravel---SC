
package model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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


/**
 *
 * @author Deyse
 */
@Entity
@Table(name="TB_MESSAGE") 
@DiscriminatorValue(value = "m")
@NamedQueries(
        {
            @NamedQuery(
                    name = OrderMessage.ALL_MESSAGE_ORDER,
                    query = "SELECT m FROM OrderMessage m"
            ),
             @NamedQuery(
                    name = OrderMessage.ORDER_MESSAGE_BY_ID,
                    query = "SELECT m FROM OrderMessage m WHERE m.id = ?1"
            ),
             @NamedQuery(
            		 name = OrderMessage.ALL_MESSAGE_BY_ORDER,
            		 query = "SELECT m FROM OrderMessage m WHERE m.order = ?1")
        }
)
public class OrderMessage extends Entidade implements Serializable {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = -4533649529217112074L;
	public static final String ALL_MESSAGE_ORDER = "All_Message_OrderMessages";
    public static final String ORDER_MESSAGE_BY_ID= "OrderMessage_Message_By_Id";
    public static final String ALL_MESSAGE_BY_ORDER = "All_Messages_By_Order_Id";

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    
    @Column(name="message")
    protected String message;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    protected UserSuper user;

	@ManyToOne
	@JoinColumn(name = "orderId")
	protected Order order;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public UserSuper getUser() {
		return user;
	}


	public void setUser(UserSuper user) {
		this.user = user;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		OrderMessage other = (OrderMessage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "OrderMessage [id=" + id + ", message=" + message + ", user=" + user + ", order=" + order + "]";
	}


	
    
}
