package net.aflexi.groovy.xmlrpc

import net.aflexi.groovy.support.Collections;
import net.aflexi.groovy.support.ControlHolder;
import net.aflexi.groovy.support.Credential;
import net.aflexi.groovy.support.CredentialHolder;

import groovy.lang.Singleton;

@Singleton(lazy = true)
class Users extends XmlRpcCalls{
    
    def doGetUserByEmail(x, Credential c, String email){
        return x.user.get(c['email'], c['password'], ['email': email])["results"][0]
    }
    
    def doCreateUser(x, Credential c, Map<String, Object> struct){
        return x.user.create(c['email'], c['password'], struct)
    }
    
    // Static Methods ----------------------------------------------------------
    
    static def createUser(Map<String, Object> struct){
        return getInstance().doCreateUser(ControlHolder.get(), CredentialHolder.get(), struct)
    }
    
    static def getUserByEmail(String email){
        return getInstance().doGetUserByEmail(ControlHolder.get(), CredentialHolder.get(), email);
    }
}
