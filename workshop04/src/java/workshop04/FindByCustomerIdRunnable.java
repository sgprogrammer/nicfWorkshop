/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop04;
import workshop04.model.Customer;
import workshop04.business.CustomerBean;
import java.sql.SQLException;
import java.util.Optional;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;


/**
 *
 * @author issuser
 */


public class FindByCustomerIdRunnable implements Runnable {
   

    private Integer custId;
    private CustomerBean customerBean;    
    private AsyncResponse asyncResp;

    public FindByCustomerIdRunnable(Integer cid,
            CustomerBean cb, AsyncResponse ar) {

        custId = cid;
        customerBean = cb;
        asyncResp = ar;
    }

    @Override
    public void run() {
        System.out.println(">>> Start FindByCustomerIdRunnable - running FindByCustomerIdRunnable");

        Optional<Customer> opt = null;
        try {
            System.out.println(">>> before customerBean...");
            opt = customerBean.findByCustomerId(custId);
            System.out.println(">>> after customerBean...");
        } catch (SQLException ex) {
            // { "error": "error message" }
            System.out.println(">>> SQLException Before Json...");
            JsonObject error = Json.createObjectBuilder()
                    .add("error", ex.getMessage())
                    .build();
            System.out.println(">>> After Json...");
            // 500 Server Error 
            asyncResp.resume(Response.serverError().entity(error).build());
            System.out.println(">>> After Resume...");
            return;
        }

        //Return 404 Not Found if the customer does not exists
        if (!opt.isPresent()) {
            System.out.println(">>> isPresent...");
            asyncResp.resume(Response.status(Response.Status.NOT_FOUND).build());
            System.out.println(">>> after isPresent resume...");
            return;
        }

        //Return the data as JSON
        System.out.println(">>> return the date to JSON...");
        asyncResp.resume(Response.ok(opt.get().toJson()).build());
        
        System.out.println(">>> resuming request");
    }
}
