package net.aflexi.groovy.xmlrpc

import java.util.Map;

import net.aflexi.groovy.support.Collections;
import net.aflexi.groovy.support.Control;
import net.aflexi.groovy.support.ControlHolder;
import net.aflexi.groovy.support.Credential;
import net.aflexi.groovy.support.CredentialHolder;

import groovy.lang.Singleton;

@Singleton(lazy = true)
class Licenses extends XmlRpcCalls{
	
	def doCreateLicense(x, Credential c, Map<String, Object> struct){
		return x.license.create(c['email'], c['password'], struct)
	}
	
	def doGetLicenseByUser(x, Credential c, Integer userId){
		return x.license.get(c['email'], c['password'], ['user': userId])["results"][0]
	}
	
	// Static Methods ----------------------------------------------------------
	
	static def createLicense(Map<String, Object> struct){
		return getInstance().doCreateLicense(ControlHolder.get(), CredentialHolder.get(), struct)
	}
	
	static def getLicenseByUser(Integer userId){
		return getInstance().doGetLicenseByUser(ControlHolder.get(), CredentialHolder.get(), userId)
	}
}
