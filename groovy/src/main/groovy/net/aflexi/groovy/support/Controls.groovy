package net.aflexi.groovy.support

import groovy.lang.Delegate;
import groovy.lang.Mixin;
import groovy.net.xmlrpc.XMLRPCServerProxy;

import java.lang.ThreadLocal
import java.util.Map;

interface Control{
	
}

XMLRPCServerProxy.mixin(Control)
assert XMLRPCServerProxy instanceof Control

/**
 * Thread-based holder for Web Service controller.
 * 
 * @author yclian
 * @since 2.1
 * @version 2.1.20100223
 */
class ControlHolder{
	
	private static ThreadLocal controls = new ThreadLocal() 
	
	public ControlHolder(){
	}
	
	public static def get() {
		controls.get()
	}
	
	public static void set(c){
		controls.set(c)
	}
}
