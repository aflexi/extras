package net.aflexi.groovy.xmlrpc

import java.util.Map;

import net.aflexi.groovy.support.Collections;
import net.aflexi.groovy.support.ControlHolder;
import net.aflexi.groovy.support.Credential;
import net.aflexi.groovy.support.CredentialHolder;

import groovy.lang.Singleton;

@Singleton(lazy = true)
class LicenseTypes extends XmlRpcCalls{
	
	def doCreateLicenseType(x, Credential c, Map<String, Object> struct){
		return x.licenseType.create(c['email'], c['password'], struct)
	}
	
	// Static Methods ----------------------------------------------------------
	
	static def createLicenseType(Map<String, Object> struct){
		return getInstance().doCreateLicenseType(ControlHolder.get(), CredentialHolder.get(), struct)
	}
}
