
package workshop02.business;

import java.util.Optional;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import workshop02.model.Customer;

@Stateless
public class CustomerBean {
    @PersistenceContext private EntityManager em;
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Optional<Customer> findByCustomerId(Integer custId){
        Customer c = em.find(Customer.class, custId);
        return (Optional.ofNullable(c));
    }
}
