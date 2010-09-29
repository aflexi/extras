package net.aflexi.groovy.xmlrpc

import java.util.Map;

import net.aflexi.groovy.support.Collections;
import net.aflexi.groovy.support.Control;
import net.aflexi.groovy.support.ControlHolder;
import net.aflexi.groovy.support.Credential;
import net.aflexi.groovy.support.CredentialHolder;

import groovy.lang.Singleton;

@Singleton(lazy = true)
class BillingInvoices extends XmlRpcCalls{
	
	def doGetActiveInvoicesByUser(x, Credential c, Integer userId){
		return x.billingInvoice.get(c['email'], c['password'], ['user': userId])["results"]
	}
	
	// Static Methods ----------------------------------------------------------
	
	static def getActiveInvoicesByUser(Integer userId){
		return getInstance().doGetActiveInvoicesByUser(ControlHolder.get(), CredentialHolder.get(), userId)
	}
}
