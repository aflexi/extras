
package net.aflexi.groovy.xmlrpc;

import net.aflexi.groovy.support.Credential;
import net.aflexi.groovy.support.Validations;

import org.apache.commons.lang.NullArgumentException;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.runtime.InvokerHelper;

import groovy.lang.GroovyInterceptable;
import groovy.net.xmlrpc.XMLRPCServerProxy;

/**
 * Abstract class to provide functionalities such as initialization, validation, etc. for XML-RPC invocation.  
 * 
 * @author yclian
 * @since 2.1
 * @version 2.1.20100221
 */
abstract class XmlRpcCalls implements GroovyInterceptable{
	
	/**
	 * <ul>
	 * <li>do*() methods with {@code XMLRPCServerProxy} and Credential</li>
	 * </ul>
	 */
	Object invokeMethod(String name, Object args) {        
		Class thisClass = InvokerHelper.getMetaClass(this).getTheClass()
		
		if(isDoMethod(name, args)){
			validateController args
		}
		
		if(isXmlRpcCall(name, args)){
			validateCredential thisClass, name, args
		}        
		
		InvokerHelper.getMetaClass(this).invokeMethod this, name, args
	}
	
	private static void validateController(Object args){
		// All do*() methods must have a control
		if(args[0] == null){
			throw new NullArgumentException("xmlRpcController");
		}
	}
	
	private static void validateCredential(Class clazz, String method, Object args){
		
		// NOTE [yclian 20100224] There's an issue in GROOVY-4076 regarding having a mixed-in interface/class 
		// as method argument, and it's causing getMetaMethod() below to return null.
		// See: http://jira.codehaus.org/browse/GROOVY-4076
		
		// Look for Credential argument and validate if found
		CachedClass[] paramTypes = InvokerHelper.getMetaClass(clazz).getMetaMethod(method, args).getParameterTypes()
		for(CachedClass paramType in paramTypes){
			if(Credential.class.equals(paramType.theClass)){
				Validations.validateCredential clazz, method
				break;
			}
		}
	}
	
	private static boolean isDoMethod(String name, Object args){
		return name.indexOf("do") == 0 && args instanceof Object[] && ((Object[]) args).length > 0;
	}
	
	private static boolean isXmlRpcCall(String name, Object args){
		return isDoMethod(name, args) && args[0] instanceof XMLRPCServerProxy;
	}
}
