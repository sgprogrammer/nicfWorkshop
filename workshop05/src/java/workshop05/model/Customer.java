/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop05.model;

/**
 *
 * @author issuser
 */
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class Customer {

    @Id @Column(name = "customer_id")
 
    private Integer customerId;
    private String name;
    private String phone;
    private String email;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getPhone() {
        return phone;

    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    public JsonObject toJson() {
        
        System.out.println("Customer Class: toJson");
        System.out.println("Customer Class: customerId: " + customerId);
        System.out.println("Customer Class: name: " + name);
        System.out.println("Customer Class: phone: " + phone);
        System.out.println("Customer Class: email: " + email);
        
        return (Json.createObjectBuilder()
                .add("customer_Id", customerId)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .build());
        
    }

}