/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop05.business;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import workshop05.model.Customer;



@Stateless
public class CustomerBean {

    @PersistenceContext private EntityManager em;
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Optional<Customer> findByCustomerId(Integer custId) {

        Customer customer = em.find(Customer.class, custId);
 
        return (Optional.ofNullable(customer));

    }

}