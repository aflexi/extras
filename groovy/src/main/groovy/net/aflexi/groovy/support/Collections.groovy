package net.aflexi.groovy.support;

import org.apache.commons.collections.CollectionUtils;


class Collections {
	
	/**
	 * NOTE [yclian 20100222] This method is added as there's strange case that Groovy's XML-RPC is reading an empty List as String.
	 * NOTE [yclian 20100224] Upgraded POM to use groovy-xmlrpc-0.6-SNAPSHOT-yclian, with patch for GROOVY-4077. Removed client calls to this method too.
	 */
	public static Object get(Object obj, Integer i){
		if(obj instanceof List){
			return get((List) obj, 0)
		} else{
			return null
		}
	}
	
	/**
	 * Get an element from the collection, safe from NPE and AIOOB.
	 */
	public static <V> V get(List<V> c, Integer i){
		
		if(CollectionUtils.isEmpty(c)){
			return null;
		}
		
		if(c.size >= i){
			return c[i];
		}
	}
}
