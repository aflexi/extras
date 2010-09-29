package net.aflexi.groovy.support

import groovy.lang.Delegate;

import java.lang.ThreadLocal
import java.util.Map;

/**
 * A Map that holds credential information.
 * 
 * @author yclian
 * @since 2.1
 * @version 2.1.20100221
 */
class Credential{
    
    @Delegate Map<String, Object> credential = [:]
}

/**
 * Thread-based {@code Credential} holder.
 * 
 * @author yclian
 * @since 2.1
 * @version 2.1.20100221
 */
class CredentialHolder{
    
    private static ThreadLocal<Map<String, Object>> credentials = new ThreadLocal<Map<String, Object>>() 
    
    public static def get() {
        credentials.get()
    }
    
    public static def set(Map<String, Object> cred){
        Credential c = new Credential()
        c.putAll cred
        credentials.set(c)
    }
}

class CredentialNotFoundException extends Exception{
    
    public CredentialNotFoundException(){
        super("Could not find credential")
    }
    
    public CredentialNotFoundException(Class clazz, String method){
        super("Could not find credential while invoking ${ clazz.getSimpleName() }.${ method }()".toString())
    }
}
