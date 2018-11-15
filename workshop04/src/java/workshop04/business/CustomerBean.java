/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop04.business;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import workshop04.model.Customer;

/**
 *
 * @author issuser
 */

@Stateless

public class CustomerBean {
    @Resource(lookup = "jdbc/sample")
    private DataSource sampleDS;
    
    public Optional<Customer> findByCustomerId(Integer custId)
            throws SQLException {
        
        try (Connection conn = sampleDS.getConnection()){
            System.out.println(">>> start CustomerBean... try connection..");
            //Perform query
            PreparedStatement ps= conn.prepareStatement("select * from customer where customer_id = ?");
            System.out.println(">>> after PreparedStatement");
            ps.setInt(1,custId);
            System.out.println(">>> after ps setInt");
            ResultSet rs = ps.executeQuery();
            System.out.println(">>> after resultSet");
            if (!rs.next()){
                //return an empty option
                System.out.println(">>> rs.next");
                return (Optional.empty());
            }
        
            Customer customer = new Customer();
            customer.setCustomerId(rs.getInt("customer_id"));
            customer.setName("name");
            customer.setAddressline1(rs.getString("addressline1"));
            customer.setAddressline2(rs.getString("addressline2"));
            customer.setCity(rs.getString("city"));
            customer.setState(rs.getString("state"));
            customer.setZip(rs.getString("zip"));
            customer.setPhone(rs.getString("phone"));
            customer.setFax(rs.getString("fax"));
            customer.setEmail(rs.getString("email"));
            System.out.println(">>> CustomerBean class: Before creditLimit ");
            customer.setCreditLimit(rs.getInt("credit_limit"));
            System.out.println(">>> CustomerBean class: After creditLimit ");
            customer.setDiscountCode(rs.getString("discount_code"));
            System.out.println(">>> Before return - CustomerBean class");    
            return (Optional.of(customer));
        }
    }
}
