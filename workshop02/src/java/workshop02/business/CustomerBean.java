
package workshop02.business;
import workshop02.model.Customer;
import workshop02.model.DiscountCode;
import workshop02.business.CustomerException;

import java.util.Optional;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@Stateless
public class CustomerBean {

    @PersistenceContext private EntityManager em;
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Optional<Customer> findByCustomerId(Integer custId) {
        Customer c = em.find(Customer.class, custId);
        return (Optional.ofNullable(c));
    }

    public void addNewCustomer(Customer customer) throws CustomerException {
        System.out.println("Before getDiscount...");
        System.out.println(customer.getDiscountCode().getDiscountCode());
        System.out.println("After getDiscount...");
        DiscountCode discountCode = em.find(DiscountCode.class, 
                customer.getDiscountCode().getDiscountCode());
        System.out.println(discountCode);
        if (null == discountCode)
            throw new CustomerException("Discount code not found");

        //new
        customer.setDiscountCode(discountCode);
        em.persist(customer);
        //managed
    }
}