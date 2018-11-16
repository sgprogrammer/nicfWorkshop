/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop05;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.security.Key;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author issuser
 */

@ApplicationScoped
public class KeyManager {
    private Key key;
    
    @PostConstruct
    private void init() {
       //Initialize or load the key from file 
        System.out.println("KeyManager Class: Checking keys...");
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        System.out.println((key));
        System.out.println("KeyManager Class: after getting keys...");
        
     }

    public Key getKey() {
        return (key);
    }
}
