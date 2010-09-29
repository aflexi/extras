package net.aflexi.groovy.support

class Validations{
    
    static def validateCredential(){
        if(CredentialHolder.get() == null){
            throw new CredentialNotFoundException()
        }
    }
    
    /**
     * Validate credential with class and method name information.
     */
    static def validateCredential(Class clazz, String method){
        if(CredentialHolder.get() == null){
            throw new CredentialNotFoundException(clazz, method)
        }
    }
    
    /**
     * Validate credential before invoking a method
     */
    static def validateCredential(Class clazz, Object obj, String method, Object args){
        if(CredentialHolder.get() == null){
            throw new CredentialNotFoundException(clazz, method)
        }
        clazz.getMetaClass().getMetaMethod(method, args).invoke(obj, args)
    }   
}
